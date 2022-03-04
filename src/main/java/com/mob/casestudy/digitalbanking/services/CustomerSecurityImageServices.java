package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityImagesId;
import com.mob.casestudy.digitalbanking.mappers.CustomerSecurityImageMapperImpl;
import com.mob.casestudy.digitalbanking.repositories.CustomerSecurityImagesRepo;
import com.digitalbanking.openapi.model.CreateCustomerSecurityImageRequest;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import static com.mob.casestudy.digitalbanking.constants.Constants.*;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import com.mob.casestudy.digitalbanking.entities.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import javax.persistence.EntityManager;
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
    @Autowired
    CustomerSecurityImageMapperImpl customerSecurityImageMapper;

    @Transactional
    public ResponseEntity<Void> storeImages(String userName, CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest) {
        Customer customer = validationHelper.validateCustomer(userName, CUSTOMER_NOT_VALID);
        CustomerSecurityImages images = customer.getCustomerSecurityImages();
        deleteStoredImage(images);
        SecurityImages securityImages = validationHelper.validateImageId(createCustomerSecurityImageRequest.getSecurityImageId(), SECURITY_IMAGE_NOT_FOUND_CODE);
        validationHelper.validateCaption(createCustomerSecurityImageRequest.getSecurityImageCaption());
        CustomerSecurityImages customerSecurityImages = customerSecurityImageMapper.updateCustomerSecurityImageByCustomer(createCustomerSecurityImageRequest, customer, securityImages, new CustomerSecurityImagesId());
        customerSecurityImagesRepo.save(customerSecurityImages);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private void deleteStoredImage(CustomerSecurityImages customerSecurityImages) {
        if (customerSecurityImages != null) {
            customerSecurityImagesRepo.delete(customerSecurityImages);
            customerSecurityImagesRepo.flush();
        }
        entityManager.clear();
    }
}

