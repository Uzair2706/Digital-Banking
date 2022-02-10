package com.mob.casestudy.digitalbanking.services;

import com.digitalbanking.openapi.model.CreateCustomerSecurityImageRequest;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.repositories.CustomerSecurityImagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import static com.mob.casestudy.digitalbanking.constants.Constants.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Validated
public class CustomerSecurityImageServices {

    @Autowired
    ValidationHelper validationHelper;
    @Autowired
    CustomerSecurityImagesRepo customerSecurityImagesRepo;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public ResponseEntity<Void> storeImages(String userName, CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest) {
        Customer customer = validationHelper.validateCustomer(userName, CUSTOMER_NOT_VALID);
        CustomerSecurityImages images = customer.getCustomerSecurityImages();
        deleteStoredImage(images);
        SecurityImages securityImages = validationHelper.validateImageId(createCustomerSecurityImageRequest.getSecurityImageId(), SECURITY_IMAGE_NOT_FOUND_CODE);
        validationHelper.validateCaption(createCustomerSecurityImageRequest.getSecurityImageCaption());
        customerSecurityImagesRepo.save(getCustomerSecurityImages(createCustomerSecurityImageRequest, customer, securityImages));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private CustomerSecurityImages getCustomerSecurityImages(CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest, Customer customer, SecurityImages securityImages) {
        CustomerSecurityImages customerSecurityImages = new CustomerSecurityImages();
        customerSecurityImages.setCustomer(customer);
        customerSecurityImages.setSecurityImages(securityImages);
        customerSecurityImages.setSecurityImageCaption(createCustomerSecurityImageRequest.getSecurityImageCaption());
        return customerSecurityImages;
    }

    private void deleteStoredImage(CustomerSecurityImages images) {
        if (images != null) {
            customerSecurityImagesRepo.delete(images);
            customerSecurityImagesRepo.flush();
        }
        entityManager.clear();
    }
}

