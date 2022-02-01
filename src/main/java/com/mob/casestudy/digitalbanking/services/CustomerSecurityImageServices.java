package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.dtos.CustomerSecurityImagesDto;
import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityImagesId;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.mob.casestudy.digitalbanking.exceptions.CustomerNotFoundException;
import com.mob.casestudy.digitalbanking.exceptions.MandatoryFieldNotValidatedException;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.repositories.CustomerSecurityImagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


@Service
public class CustomerSecurityImageServices {

    @Autowired
    ValidationHelper validationHelper;
    @Autowired
    CustomerSecurityImagesRepo customerSecurityImagesRepo;

    @Autowired
    Environment environment;

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void storeImages(String userName,CustomerSecurityImagesDto customerSecurityImagesDto){

        Customer customer = validationHelper.validateCustomer(userName);
        CustomerSecurityImages images = customer.getCustomerSecurityImages();
        extracted(images);
        SecurityImages securityImages = validationHelper.validateImageId(customerSecurityImagesDto.getSecurityImageId());

        CustomerSecurityImages customerSecurityImages = new CustomerSecurityImages();
        customerSecurityImages.setCustomer(customer);
        customerSecurityImages.setSecurityImages(securityImages);
        customerSecurityImages.setSecurityImageCaption(customerSecurityImagesDto.getSecurityImageCaption());
        customerSecurityImagesRepo.save(customerSecurityImages);
    }

    private void extracted(CustomerSecurityImages images) {
        if(images !=null) {
            customerSecurityImagesRepo.delete(images);
            entityManager.flush();
        }
        entityManager.clear();
    }


//    @Transactional
//    public void delete(String userName) {
//        Optional<CustomerSecurityImages> customerSecurityImages = customerSecurityImagesRepo.findByUserName(userName);
//        if (customerSecurityImages.isPresent()) {
//            customerSecurityImagesRepo.delete(customerSecurityImages.get());
//        }
//
//    }


    @Transactional
    public void create(CustomerSecurityImagesDto customerSecurityImagesDto, String userName) {

        Customer customer = validationHelper.validateCustomer(userName);
        extracted(customer);
        entityManager.clear();
        SecurityImages securityImages = validationHelper.validateImageId(customerSecurityImagesDto.getSecurityImageId());
        CustomerSecurityImages customerSecurityImages = getCustomerSecurityImages(customerSecurityImagesDto, customer, securityImages);
        customerSecurityImagesRepo.save(customerSecurityImages);
    }

    private void extracted(Customer customer) {
        CustomerSecurityImages images = customer.getCustomerSecurityImages();
        if (images!=null) {
            customerSecurityImagesRepo.delete(images);
            entityManager.flush();
        }
    }

    private CustomerSecurityImages getCustomerSecurityImages(CustomerSecurityImagesDto customerSecurityImagesDto, Customer customer, SecurityImages securityImages) {

        CustomerSecurityImages customerSecurityImages = new CustomerSecurityImages();
        customerSecurityImages.setCustomer(customer);
        customerSecurityImages.setSecurityImageCaption(customerSecurityImagesDto.getSecurityImageCaption());
        customerSecurityImages.setSecurityImages(securityImages);
        return customerSecurityImages;
    }

//    private CustomerSecurityImages getCustomerSecurityImages(CustomerSecurityImagesDto customerSecurityImagesDto, Customer customer, SecurityImages securityImages) {
////        CustomerSecurityImagesId customerSecurityImagesId = new CustomerSecurityImagesId(customer.getId(), customerSecurityImagesDto.getSecurityImageId());
//        return CustomerSecurityImages.builder()
//                .customerSecurityImagesId(new CustomerSecurityImagesId())
//                .createdOn(LocalDateTime.now())
//                .securityImageCaption(customerSecurityImagesDto.getSecurityImageCaption())
//                .customer(customer)
//                .securityImages(securityImages)
//                .build();
//    }


//    @Transactional
//    public void createImages(String userName,CustomerSecurityImagesDto customerSecurityImagesDto){
//
//        Customer customer = validationHelper.validateCustomer(userName);
//        validationHelper.validateImageId(customerSecurityImagesDto.getSecurityImageId());
//        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("update_customer_security_images")
//                .registerStoredProcedureParameter("c_id",String.class, ParameterMode.IN)
//                .registerStoredProcedureParameter("created_time",LocalDateTime.class, ParameterMode.IN)
//                .registerStoredProcedureParameter("caption",String.class, ParameterMode.IN)
//                .registerStoredProcedureParameter("c_id1",String.class, ParameterMode.IN)
//                .registerStoredProcedureParameter("i_id",String.class, ParameterMode.IN);
//
//        storedProcedureQuery.setParameter("c_id", customer.getId());
//        storedProcedureQuery.setParameter("created_time",LocalDateTime.now());
//        storedProcedureQuery.setParameter("caption",customerSecurityImagesDto.getSecurityImageCaption());
//        storedProcedureQuery.setParameter("c_id1",customer.getId());
//        storedProcedureQuery.setParameter("i_id",customerSecurityImagesDto.getSecurityImageId());
//        storedProcedureQuery.executeUpdate();
//    }

}

