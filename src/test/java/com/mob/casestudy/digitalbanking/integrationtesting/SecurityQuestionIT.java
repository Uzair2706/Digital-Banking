package com.mob.casestudy.digitalbanking.integrationtesting;

import com.digitalbanking.openapi.model.GetSecurityQuestionsResponse;
import com.digitalbanking.openapi.model.PreferredLanguage;
import com.digitalbanking.openapi.model.SecurityQuestion;
import com.digitalbanking.openapi.model.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mob.casestudy.digitalbanking.DigitalBankingApplication;
import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityQuestionsId;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityQuestions;
import com.mob.casestudy.digitalbanking.entities.SecurityQuestions;
import com.mob.casestudy.digitalbanking.exceptionhandlers.ExceptionResponse;
import com.mob.casestudy.digitalbanking.repositories.CustomerRepo;
import com.mob.casestudy.digitalbanking.repositories.CustomerSecurityQuestionsRepo;
import com.mob.casestudy.digitalbanking.repositories.SecurityQuestionsRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

import static com.mob.casestudy.digitalbanking.constants.Constants.*;
import static com.mob.casestudy.digitalbanking.constants.Constants.SEC_QUESTION_NOT_FOUND_DESCRIPTION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DigitalBankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@PropertySource(value = "classpath:application.yml")
class SecurityQuestionIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    SecurityQuestionsRepo securityQuestionsRepo;
    @Autowired
    CustomerSecurityQuestionsRepo customerSecurityQuestionsRepo;

    @Test
    @Transactional
    void testRetrieveQuestions() throws Exception {

        List<SecurityQuestions> securityQuestionsList = securityQuestionsRepo.findAll();
        List<SecurityQuestion> securityQuestionDtoList = new ArrayList<>();

        securityQuestionsList.forEach(csq ->
        {
            SecurityQuestion securityQuestion = new SecurityQuestion()
                    .securityQuestionId(csq.getId().toString())
                    .securityQuestionText(csq.getSecurityQuestionText());
            securityQuestionDtoList.add(securityQuestion);
        });

        GetSecurityQuestionsResponse getSecurityQuestionsResponse = new GetSecurityQuestionsResponse().securityQuestions(securityQuestionDtoList);
        String expected = new ObjectMapper().writeValueAsString(getSecurityQuestionsResponse);
        this.mockMvc.perform(get("/customer-service/client-api/v1/securityQuestions"))
                .andExpect(content().string(expected))
                .andExpect(status().isOk());
    }

    @Test
    void testRetrieveQuestions_withNoSecurityQuestions_shouldThrowAnError() throws Exception {

        customerSecurityQuestionsRepo.deleteAll();
        securityQuestionsRepo.deleteAll();
        ExceptionResponse exceptionResponse = new ExceptionResponse(SEC_QUESTION_NOT_FOUND_CODE,SEC_QUESTION_NOT_FOUND_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        mockMvc.perform(get("/customer-service/client-api/v1/securityQuestions")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andExpect(content().json(expected));
    }

    @BeforeEach
    void setUp() {
        Customer customer = Customer.builder().userName("UzairKhan2706").firstName("Uzair").lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(Status.ACTIVE)
                .preferredLanguage(PreferredLanguage.EN).externalId("42069").createdBy("Me").createdOn(LocalDateTime.now()).updatedBy("Again Me").updatedOn(LocalDateTime.now()).build();

        Customer customer2 = Customer.builder().userName("Siddu26").firstName("Siddharth").lastName("Unknown").phoneNumber("9458907652").email("siddhu26@gmail.com").status(Status.PENDING)
                .preferredLanguage(PreferredLanguage.DE).externalId("31032").createdBy("Still").createdOn(LocalDateTime.now()).updatedBy("Unknown").updatedOn(LocalDateTime.now()).build();

        customerRepo.save(customer);
        customerRepo.save(customer2);
        addQuestions(customer);

    }

    @AfterEach
    void tearDown() {
        customerSecurityQuestionsRepo.deleteAll();
        customerRepo.deleteAll();
        securityQuestionsRepo.deleteAll();
    }

    public void addQuestions(Customer customer) {

        List<SecurityQuestions> list = new ArrayList<>();

        SecurityQuestions questions = SecurityQuestions.builder().securityQuestionText("What's your Favourite Car?").build();
        SecurityQuestions questions1 = SecurityQuestions.builder().securityQuestionText("Place that you would've loved to visit").build();

        list.add(questions);
        list.add(questions1);
        securityQuestionsRepo.save(questions);
        securityQuestionsRepo.save(questions1);
        setQuestionsForCustomer(customer, list);
    }


    public void setQuestionsForCustomer(Customer customer, List<SecurityQuestions> questions) {

        CustomerSecurityQuestions customerSecurityQuestions = CustomerSecurityQuestions.builder().customerSecurityQuestionsId(new CustomerSecurityQuestionsId()).securityQuestionAnswer("Porsche 911").createdOn(LocalDateTime.now()).build();
        CustomerSecurityQuestions customerSecurityQuestions1 = CustomerSecurityQuestions.builder().customerSecurityQuestionsId(new CustomerSecurityQuestionsId()).securityQuestionAnswer("Mystic Falls").createdOn(LocalDateTime.now()).build();

        customerSecurityQuestions.setCustomer(customer);
        customerSecurityQuestions.setSecurityQuestions(questions.get(0));
        customerSecurityQuestionsRepo.save(customerSecurityQuestions);

        System.out.println("customer ->" + customerSecurityQuestions.getCustomer());

        customerSecurityQuestions1.setCustomer(customer);
        customerSecurityQuestions1.setSecurityQuestions(questions.get(1));
        customerSecurityQuestionsRepo.save(customerSecurityQuestions1);
    }

}
