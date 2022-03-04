package com.mob.casestudy.digitalbanking.integrationtesting;

import com.digitalbanking.openapi.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mob.casestudy.digitalbanking.DigitalBankingApplication;
import com.mob.casestudy.digitalbanking.embeddables.CustomerOtpId;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import com.mob.casestudy.digitalbanking.exceptionhandlers.ExceptionResponse;
import com.mob.casestudy.digitalbanking.repositories.CustomerOtpRepo;
import com.mob.casestudy.digitalbanking.repositories.CustomerRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import static com.digitalbanking.openapi.model.PreferredLanguage.EN;
import static com.mob.casestudy.digitalbanking.constants.Constants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DigitalBankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@PropertySource(value = "classpath:application.yml")
class CustomerOtpServicesIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    CustomerOtpRepo customerOtpRepo;

    @Test
    void initiatingOtpForCustomer() throws Exception {

        InitiateOtpRequest initiateOtpRequest = new InitiateOtpRequest().userName("UzairKhan2706").templateId("REGISTRATION");

        String request = new ObjectMapper().writeValueAsString(initiateOtpRequest);
        this.mockMvc.perform(post("/customer-service/client-api/v1/otp/initiate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)).andExpect(status().isOk()).andReturn();
    }

    @Test
    void initiatingOtpForCustomer_withInvalidCustomer_shouldThrowAnException() throws Exception {

        String userName = "Michael";
        InitiateOtpRequest initiateOtpRequest = new InitiateOtpRequest().userName(userName);
        String request = new ObjectMapper().writeValueAsString(initiateOtpRequest);
        MvcResult mvcResult = this.mockMvc.perform(post("/customer-service/client-api/v1/otp/initiate").contentType(MediaType.APPLICATION_JSON)
                .content(request)).andExpect(status().isNotFound()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(CUSTOMER_WITH_INVALID_CODE, "The username '" + userName + "' is not registered with the system");
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void initiatingOtpForCustomer_withInvalidTemplateId_shouldThrowAnException() throws Exception {

        String userName = "UzairKhan2706";
        InitiateOtpRequest initiateOtpRequest = new InitiateOtpRequest().userName(userName).templateId("REG");
        String request = new ObjectMapper().writeValueAsString(initiateOtpRequest);
        MvcResult mvcResult = this.mockMvc.perform(post("/customer-service/client-api/v1/otp/initiate").contentType(MediaType.APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(TEMPLATE_ID_NOT_VALID, TEMPLATE_ID_NOT_VALID_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);

    }

    @BeforeEach
    void setUp() {

        Customer customer = Customer.builder().userName("UzairKhan2706").firstName("Uzair").lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(Status.ACTIVE)
                .preferredLanguage(EN).externalId("42069").createdBy("Me").createdOn(LocalDateTime.now()).updatedBy("Again Me").updatedOn(LocalDateTime.now()).build();

        customerRepo.save(customer);
        setOtp(customer);
    }

    @AfterEach
    void tearDown() {
        customerOtpRepo.deleteAll();
        customerRepo.deleteAll();
    }

    public void setOtp(Customer customer) {

        CustomerOtp customerOtp = CustomerOtp.builder().customerOtpId(new CustomerOtpId()).otpMessage("Otp Generated").otp("786930").otpRetries(0).expiryOn(LocalDateTime.now()).createdOn(LocalDateTime.now()).build();
        customerOtp.setCustomer(customer);
        customerOtpRepo.save(customerOtp);
    }

}
