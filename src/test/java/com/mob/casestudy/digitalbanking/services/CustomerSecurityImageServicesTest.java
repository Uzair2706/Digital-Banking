package com.mob.casestudy.digitalbanking.services;

import com.digitalbanking.openapi.model.CreateCustomerSecurityImageRequest;
import com.digitalbanking.openapi.model.PreferredLanguage;
import com.digitalbanking.openapi.model.Status;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.repositories.CustomerSecurityImagesRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static com.mob.casestudy.digitalbanking.constants.Constants.*;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CustomerSecurityImageServicesTest {

    @InjectMocks
    CustomerSecurityImageServices customerSecurityImageServices;
    @Mock
    ValidationHelper validationHelper;
    @Mock
    CustomerSecurityImagesRepo customerSecurityImagesRepo;
    @Mock
    EntityManager entityManager;

    @Test
    void storingImages_forCustomer_withValidInput() {

        String userName = "UzairKhan2706";
        UUID id = UUID.randomUUID();
        Customer customer = Customer.builder().userName("UzairKhan2706").firstName("Uzair").lastName("Khan").phoneNumber("7226803020").email("uzairkhan27@gmail.com").status(Status.ACTIVE)
                .preferredLanguage(PreferredLanguage.EN).externalId("42069").createdBy("Me").createdOn(LocalDateTime.now()).updatedBy("Again Me").updatedOn(LocalDateTime.now()).build();

        CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest = new CreateCustomerSecurityImageRequest().securityImageId(id.toString()).securityImageCaption("POR FAVOR");
        SecurityImages images = SecurityImages.builder().securityImageName("Pagani").securityImageUrl("pagani/here").build();
        Mockito.when(validationHelper.validateCustomer(userName, CUSTOMER_NOT_VALID)).thenReturn(customer);
        Mockito.when(validationHelper.validateImageId(createCustomerSecurityImageRequest.getSecurityImageId(),SECURITY_IMAGE_NOT_FOUND_CODE)).thenReturn(images);
        customerSecurityImageServices.storeImages(userName, createCustomerSecurityImageRequest);
        Mockito.verify(validationHelper).validateCustomer(userName, CUSTOMER_NOT_VALID);
        Mockito.verify(validationHelper).validateImageId(createCustomerSecurityImageRequest.getSecurityImageId(),SECURITY_IMAGE_NOT_FOUND_CODE);
        Mockito.verify(customerSecurityImagesRepo).save(Mockito.any());
    }
}