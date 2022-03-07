package com.mob.casestudy.digitalbanking.integrationtesting;

import com.digitalbanking.openapi.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mob.casestudy.digitalbanking.DigitalBankingApplication;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.exceptionhandlers.ExceptionResponse;
import com.mob.casestudy.digitalbanking.repositories.CustomerRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
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
import java.util.List;

import static com.digitalbanking.openapi.model.PreferredLanguage.EN;
import static com.digitalbanking.openapi.model.Status.ACTIVE;
import static com.mob.casestudy.digitalbanking.constants.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DigitalBankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@PropertySource(value = "classpath:application.yml")
class CustomerServicesIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CustomerRepo customerRepo;


    @Test
    void creatingCustomerForApplication() throws Exception {

        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest().userName("uzair").firstName("uzair")
                .lastName("Khan").phoneNumber("9555770958").email("uzairkhan@gmail.com").preferredLanguage(EN);


        String request = new ObjectMapper().writeValueAsString(createCustomerRequest);
        MvcResult mvcResult = this.mockMvc.perform(post("/customer-service/client-api/v1/customers")
                .contentType(APPLICATION_JSON)
                .content(request)).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        Customer customer = customerRepo.findByUserName("uzair").orElse(new Customer());
        CreateCustomerResponse createCustomerResponse = new CreateCustomerResponse();
        createCustomerResponse.setId(customer.getId());
        String expected = new ObjectMapper().writeValueAsString(createCustomerResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void creatingCustomerForApplication_withMandatoryFieldsNotValidated_shouldThrowAnException() throws Exception {

        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest().userName(null).firstName("uzair")
                .lastName("Khan").phoneNumber("9555770958").email("uzairkhan@gmail.com").preferredLanguage(EN);

        String request = new ObjectMapper().writeValueAsString(createCustomerRequest);
        MvcResult mvcResult = this.mockMvc.perform(post("/customer-service/client-api/v1/customers").contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(CUS_MAND_VALID_CODE, CUS_MAND_VALID_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void creatingCustomerForApplication_withPhoneRegexNotMatching_shouldThrowAnException() throws Exception {

        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest().userName("uzair").firstName("uzair")
                .lastName("Khan").phoneNumber("22740189").email("uzairkhan@gmail.com").preferredLanguage(EN);

        String request = new ObjectMapper().writeValueAsString(createCustomerRequest);
        MvcResult mvcResult = this.mockMvc.perform(post("/customer-service/client-api/v1/customers").contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(PHONE_NO_INVALID_CODE, PHONE_NO_INVALID_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void creatingCustomerForApplication_withEmailRegexNotMatching_shouldThrowAnException() throws Exception {

        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest().userName("uzair").firstName("uzair")
                .lastName("Khan").phoneNumber("22740189").email("unmarshalling.com").preferredLanguage(EN);

        String request = new ObjectMapper().writeValueAsString(createCustomerRequest);
        MvcResult mvcResult = this.mockMvc.perform(post("/customer-service/client-api/v1/customers").contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(EMAIL_INVALID_CODE, EMAIL_INVALID_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void creatingCustomerForApplication_withUserNameRegexNotMatching_shouldThrowAnException() throws Exception {

        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest().userName("aaa").firstName("uzair")
                .lastName("Khan").phoneNumber("9555770958").email("uzairkhan@gmail.com").preferredLanguage(EN);

        String request = new ObjectMapper().writeValueAsString(createCustomerRequest);
        MvcResult mvcResult = this.mockMvc.perform(post("/customer-service/client-api/v1/customers").contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(USERNAME_INVALID_CODE, USERNAME_INVALID_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void creatingCustomerForApplication_withNonUniqueUserName_shouldThrowAnException() throws Exception {

        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest().userName("UzairKhan2706").firstName("uzair")
                .lastName("Khan").phoneNumber("9555770958").email("uzairkhan@gmail.com").preferredLanguage(EN);

        String request = new ObjectMapper().writeValueAsString(createCustomerRequest);
        MvcResult mvcResult = this.mockMvc.perform(post("/customer-service/client-api/v1/customers").contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(NON_UNIQUE_USERNAME_CODE, NON_UNIQUE_USERNAME_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updatingCustomerForApplication() throws Exception {

        PatchCustomerRequest patchCustomerRequest = new PatchCustomerRequest().firstName("Uzair")
                .lastName("Khan").phoneNumber("9555770958").email("uzairkhan@gmail.com").preferredLanguage(EN).status(ACTIVE);

        String request = new ObjectMapper().writeValueAsString(patchCustomerRequest);
        this.mockMvc.perform(patch("/customer-service/client-api/v1/customers/UzairKhan2706")
                .contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isNoContent()).andReturn();
    }

    @Test
    void updatingCustomerForApplication_withMandatoryFieldsNotValidated_shouldThrowAnException() throws Exception {

        PatchCustomerRequest patchCustomerRequest = new PatchCustomerRequest().firstName("Uzair")
                .lastName("Khan").phoneNumber("9555770958").email("uzairkhan@gmail.com").preferredLanguage(EN).status(ACTIVE);

        String request = new ObjectMapper().writeValueAsString(patchCustomerRequest);
        MvcResult mvcResult = this.mockMvc.perform(patch("/customer-service/client-api/v1/customers/").contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isNotFound()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(UPD_MAND_CODE, UPD_MAND_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updatingCustomerForApplication_withInvalidPhoneNumber_shouldThrowAnException() throws Exception {

        PatchCustomerRequest patchCustomerRequest = new PatchCustomerRequest().firstName("Uzair")
                .lastName("Khan").phoneNumber("22740189").email("uzairkhan@gmail.com").preferredLanguage(EN).status(ACTIVE);

        String request = new ObjectMapper().writeValueAsString(patchCustomerRequest);
        MvcResult mvcResult = this.mockMvc.perform(patch("/customer-service/client-api/v1/customers/UzairKhan2706").contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(UPD_PHN_CODE, UPD_PHN_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updatingCustomerForApplication_withInvalidEmail_shouldThrowAnException() throws Exception {

        PatchCustomerRequest patchCustomerRequest = new PatchCustomerRequest().firstName("Uzair")
                .lastName("Khan").phoneNumber("7226803020").email("unmarshal.com").preferredLanguage(EN).status(ACTIVE);

        String request = new ObjectMapper().writeValueAsString(patchCustomerRequest);
        MvcResult mvcResult = this.mockMvc.perform(patch("/customer-service/client-api/v1/customers/UzairKhan2706").contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(UPD_EML_CODE, UPD_EML_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updatingCustomerForApplication_withInvalidUserName_shouldThrowAnException() throws Exception {

        String userName = "UzairKhan27069";
        ExceptionResponse exceptionResponse = new ExceptionResponse(UPD_CUS_CODE, "The username '" + userName + "' is not registered with the system");
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        mockMvc.perform(patch("/customer-service/client-api/v1/customers/UzairKhan27069")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andExpect(content().json(expected));
    }

    @Test
    void getCustomer_withCorrectIdOrUserName_shouldReturnACustomer() throws Exception {
        String id = "";
        String userName = "UzairKhan2706";
        List<Customer> byUserNameOrId = customerRepo.findByUserNameOrId(userName, id);
        Customer customer = byUserNameOrId.get(0);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        GetCustomerResponse getCustomerResponse = new GetCustomerResponse().id(customer.getId()).userName(userName).firstName("Uzair")
                .lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(ACTIVE)
                .preferredLanguage(EN).externalId(customer.getExternalId())
                .createdOn(customer.getCreatedOn()).updatedOn(customer.getUpdatedOn());

        String expected = objectMapper.writeValueAsString(getCustomerResponse);
        this.mockMvc.perform(get("/customer-service/client-api/v1/customers?id=" + customer.getId()))
                .andExpect(content().string(expected))
                .andExpect(status().isOk());
    }

    @Test
    void getCustomer_withNoIdOrUserName_shouldThrowAnException() throws Exception {
        String id = "";
        String userName = "UzairKhan2706";
        List<Customer> byUserNameOrId = customerRepo.findByUserNameOrId(userName, id);
        Customer customer = byUserNameOrId.get(0);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        GetCustomerResponse getCustomerResponse = new GetCustomerResponse().id(null).userName(null).firstName("Uzair")
                .lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(ACTIVE)
                .preferredLanguage(EN).externalId(customer.getExternalId())
                .createdOn(customer.getCreatedOn()).updatedOn(customer.getUpdatedOn());

        String request = objectMapper.writeValueAsString(getCustomerResponse);

        MvcResult mvcResult = this.mockMvc.perform(get("/customer-service/client-api/v1/customers?id=")
                .contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(GET_CUS_MAND_CODE, GET_CUS_MAND_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getCustomer_withInvalidIdOrUserName_shouldThrowAnException() throws Exception {
        String id = "f6778e9c-bba4-42de-8e0f-cd0d413af351";
        String userName = "UzairKhan2706";
        List<Customer> byUserNameOrId = customerRepo.findByUserNameOrId(userName, id);
        Customer customer = byUserNameOrId.get(0);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        GetCustomerResponse getCustomerResponse = new GetCustomerResponse().id(null).userName(null).firstName("Uzair")
                .lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(ACTIVE)
                .preferredLanguage(EN).externalId(customer.getExternalId())
                .createdOn(customer.getCreatedOn()).updatedOn(customer.getUpdatedOn());

        String request = objectMapper.writeValueAsString(getCustomerResponse);

        MvcResult mvcResult = this.mockMvc.perform(get("/customer-service/client-api/v1/customers?id=null&user_name=null")
                .contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isNotFound()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(GET_CUS_NFD_CODE, "Customer is not found with either of the requested parameter");
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @BeforeEach
    void setUp() {

        Customer customer = Customer.builder().userName("UzairKhan2706").firstName("Uzair").lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(ACTIVE)
                .preferredLanguage(EN).externalId("42069").createdBy("Me").createdOn(LocalDateTime.now()).updatedBy("Again Me").updatedOn(LocalDateTime.now()).build();

        Customer customer2 = Customer.builder().userName("Siddu26").firstName("Siddharth").lastName("Unknown").phoneNumber("9458907652").email("siddhu26@gmail.com").status(Status.PENDING)
                .preferredLanguage(PreferredLanguage.DE).externalId("31032").createdBy("Still").createdOn(LocalDateTime.now()).updatedBy("Unknown").updatedOn(LocalDateTime.now()).build();

        customerRepo.save(customer);
        customerRepo.save(customer2);

    }

    @AfterEach
    void tearDown() {
        customerRepo.deleteAll();
    }

}
