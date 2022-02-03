package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.constants.Constants;
import com.mob.casestudy.digitalbanking.dtos.CreateCustomerSecurityImageRequest;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.repositories.CustomerSecurityImagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class CustomerSecurityImageServices {

    @Autowired
    ValidationHelper validationHelper;
    @Autowired
    CustomerSecurityImagesRepo customerSecurityImagesRepo;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void storeImages(String userName, CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest){

        Customer customer = validationHelper.validateCustomer(userName,Constants.CUSTOMER_NOT_VALID);
        CustomerSecurityImages images = customer.getCustomerSecurityImages();
        deleteStoredImage(images);
        SecurityImages securityImages = validationHelper.validateImageId(createCustomerSecurityImageRequest.getSecurityImageId());

        CustomerSecurityImages customerSecurityImages = new CustomerSecurityImages();
        customerSecurityImages.setCustomer(customer);
        customerSecurityImages.setSecurityImages(securityImages);
        customerSecurityImages.setSecurityImageCaption(createCustomerSecurityImageRequest.getSecurityImageCaption());
        customerSecurityImagesRepo.save(customerSecurityImages);
    }

    private void deleteStoredImage(CustomerSecurityImages images) {
        if(images !=null) {
            customerSecurityImagesRepo.delete(images);
            entityManager.flush();
        }
        entityManager.clear();
    }
}

