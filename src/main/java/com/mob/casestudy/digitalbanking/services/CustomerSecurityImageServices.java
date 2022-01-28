package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.dtos.CustomerSecurityImagesDto;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.mob.casestudy.digitalbanking.exceptions.MandatoryFieldNotValidatedException;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.repositories.CustomerSecurityImagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Service
public class CustomerSecurityImageServices {

    @Autowired
    ValidationHelper services;
    @Autowired
    CustomerSecurityImagesRepo customerSecurityImagesRepo;

    @Transactional
    public void storeImages(String userName,CustomerSecurityImagesDto customerSecurityImagesDto){

        if (customerSecurityImagesDto == null){
            throw new MandatoryFieldNotValidatedException();
        }

        Customer customer = services.validateCustomer(userName);
        CustomerSecurityImages images = customer.getCustomerSecurityImages();

        if(images!=null) {
            customerSecurityImagesRepo.delete(images);
        }

        CustomerSecurityImages customerSecurityImages = new CustomerSecurityImages();
        customerSecurityImages.setCustomer(customer);

        SecurityImages securityImages = services.validateImageId(customerSecurityImagesDto.getSecurityImageId());
        customerSecurityImages.setSecurityImages(securityImages);

        customerSecurityImages.setCreatedOn(LocalDateTime.now());
        customerSecurityImages.setSecurityImageCaption(customerSecurityImagesDto.getSecurityImageCaption());
        customerSecurityImagesRepo.save(customerSecurityImages);

    }



}
