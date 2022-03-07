package com.mob.casestudy.digitalbanking.integrationtesting;

import com.digitalbanking.openapi.model.CreateCustomerSecurityImageRequest;
import com.digitalbanking.openapi.model.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mob.casestudy.digitalbanking.DigitalBankingApplication;
import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityImagesId;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.mob.casestudy.digitalbanking.exceptionhandlers.ExceptionResponse;
import com.mob.casestudy.digitalbanking.repositories.CustomerRepo;
import com.mob.casestudy.digitalbanking.repositories.CustomerSecurityImagesRepo;
import com.mob.casestudy.digitalbanking.repositories.SecurityImagesRepo;
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
import java.util.ArrayList;
import java.util.List;

import static com.digitalbanking.openapi.model.PreferredLanguage.EN;
import static com.mob.casestudy.digitalbanking.constants.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DigitalBankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@PropertySource(value = "classpath:application.yml")
class CustomerImageServicesIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    CustomerSecurityImagesRepo customerSecurityImagesRepo;
    @Autowired
    SecurityImagesRepo securityImagesRepo;

    @Test
    void storeImages() throws Exception {

        List<SecurityImages> all = securityImagesRepo.findAll();
        SecurityImages securityImages = all.get(0);

        CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest = new CreateCustomerSecurityImageRequest()
                .securityImageId(securityImages.getId())
                .securityImageCaption("Ford");

        String request = new ObjectMapper().writeValueAsString(createCustomerSecurityImageRequest);
        this.mockMvc.perform(put("/customer-service/client-api/v1/customers/UzairKhan2706/securityImages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)).andExpect(status().isCreated()).andReturn();
    }

    @Test
    void storingImage_withMandatoryImagesNotValidated_shouldThrowAnException() throws Exception {

        CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest = new CreateCustomerSecurityImageRequest()
                .securityImageId(null)
                .securityImageCaption("Ford");

        String request = new ObjectMapper().writeValueAsString(createCustomerSecurityImageRequest);
        MvcResult mvcResult = this.mockMvc.perform(put("/customer-service/client-api/v1/customers/UzairKhan2706/securityImages")
                .contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(FIELD_NOT_FOUND_CODE,FIELD_NOT_FOUND_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void storingImage_withInvalidImageId_shouldThrowAnException() throws Exception {

        CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest = new CreateCustomerSecurityImageRequest()
                .securityImageId("1b9f5ef0-48a1-4962-9087-bfe56cb35269")
                .securityImageCaption("Ford");

        String request = new ObjectMapper().writeValueAsString(createCustomerSecurityImageRequest);
        MvcResult mvcResult = this.mockMvc.perform(put("/customer-service/client-api/v1/customers/UzairKhan2706/securityImages")
                .contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isNotFound()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(SECURITY_IMAGE_NOT_FOUND_CODE,"Security Image Id not validated");
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void storingImage_withNullCaption_shouldThrowAnException() throws Exception {

        List<SecurityImages> all = securityImagesRepo.findAll();
        SecurityImages securityImages = all.get(0);

        CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest = new CreateCustomerSecurityImageRequest()
                .securityImageId(securityImages.getId())
                .securityImageCaption(null);

        String request = new ObjectMapper().writeValueAsString(createCustomerSecurityImageRequest);
        MvcResult mvcResult = this.mockMvc.perform(put("/customer-service/client-api/v1/customers/UzairKhan2706/securityImages")
                .contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(CAPTION_NOT_VALID, CAPTION_NOT_NULL_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void storingImage_witheEmptyCaption_shouldThrowAnException() throws Exception {

        List<SecurityImages> all = securityImagesRepo.findAll();
        SecurityImages securityImages = all.get(0);

        CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest = new CreateCustomerSecurityImageRequest()
                .securityImageId(securityImages.getId())
                .securityImageCaption("");

        String request = new ObjectMapper().writeValueAsString(createCustomerSecurityImageRequest);
        MvcResult mvcResult = this.mockMvc.perform(put("/customer-service/client-api/v1/customers/UzairKhan2706/securityImages")
                .contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(CAPTION_NOT_VALID, CAPTION_NOT_EMPTY_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void storingImage_withCaptionLessThan3Characters_shouldThrowAnException() throws Exception {

        List<SecurityImages> all = securityImagesRepo.findAll();
        SecurityImages securityImages = all.get(0);

        CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest = new CreateCustomerSecurityImageRequest()
                .securityImageId(securityImages.getId())
                .securityImageCaption("UK");

        String request = new ObjectMapper().writeValueAsString(createCustomerSecurityImageRequest);
        MvcResult mvcResult = this.mockMvc.perform(put("/customer-service/client-api/v1/customers/UzairKhan2706/securityImages")
                .contentType(APPLICATION_JSON)
                .content(request)).andExpect(status().isBadRequest()).andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = new ExceptionResponse(CAPTION_NOT_VALID, CAPTION_SIZE_NOT_VALID_DESCRIPTION);
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void storingImage_withInvalidCustomer_shouldThrowAnException() throws Exception {

        String userName = "UzairKhan27069";
        ExceptionResponse exceptionResponse = new ExceptionResponse(CUSTOMER_NOT_VALID, "The username '" + userName + "' is not registered with the system");
        String expected = new ObjectMapper().writeValueAsString(exceptionResponse);
        mockMvc.perform(put("/customer-service/client-api/v1/customers/UzairKhan27069/securityImages")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andExpect(content().json(expected));
    }

    @BeforeEach
    void setUp() {

        Customer customer = Customer.builder().userName("UzairKhan2706").firstName("Uzair").lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(Status.ACTIVE)
                .preferredLanguage(EN).externalId("42069").createdBy("Me").createdOn(LocalDateTime.now()).updatedBy("Again Me").updatedOn(LocalDateTime.now()).build();

        customerRepo.save(customer);
        addImages(customer);
    }

    @AfterEach
    void tearDown() {
        customerSecurityImagesRepo.deleteAll();
        customerRepo.deleteAll();
        securityImagesRepo.deleteAll();
    }


    public void addImages(Customer customer) {

        List<SecurityImages> list = new ArrayList<>();
        SecurityImages images = SecurityImages.builder().securityImageName("Shelby").securityImageUrl("www.shelby_motors.com").build();
        SecurityImages images1 = SecurityImages.builder().securityImageName("GT").securityImageUrl("www.Gt_works.com").build();
        list.add(images);
        list.add(images1);
        securityImagesRepo.save(images);
        securityImagesRepo.save(images1);
        setImagesForCustomer(customer,list);
    }

    public void setImagesForCustomer(Customer customer, List<SecurityImages> securityImages) {

        CustomerSecurityImages customerSecurityImages =  CustomerSecurityImages.builder().customerSecurityImagesId(new CustomerSecurityImagesId()).securityImageCaption("Ford").createdOn(LocalDateTime.now()).build();
        customerSecurityImages.setCustomer(customer);
        customerSecurityImages.setSecurityImages(securityImages.get(0));
        customerSecurityImagesRepo.save(customerSecurityImages);
    }

}
