//package com.mob.casestudy.digitalbanking.services;
//
//import com.mob.casestudy.digitalbanking.dtos.CustomerSecurityImagesDto;
//import com.mob.casestudy.digitalbanking.entities.Customer;
//import com.mob.casestudy.digitalbanking.entities.SecurityImages;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class CustomerSecurityImageServicesTest {
//
//    @InjectMocks
//    CustomerSecurityImageServices customerSecurityImageServices;
//
//    @Mock
//    ValidationServices validationServices;
//
//    @Test
//    void storeImages() {
//
//        String userName = "UzairKhan2706";
//
//        UUID id = UUID.randomUUID();
//
//        Customer customer = new Customer("UzairKhan2706", "Uzair", "Khan", "7226803020", "uzairkhan27@gmail.com",
//                Customer.CustomerStatus.ACTIVE, Customer.CustomerPreferredLanguage.EN, "42069", "Me", LocalDateTime.now(), "Again Me", LocalDateTime.now());
//
//        CustomerSecurityImagesDto customerSecurityImagesDto = new CustomerSecurityImagesDto(id,"POR FAVOR");
//
//        SecurityImages images = new SecurityImages("Pagani","pagani/here");
//
//        Mockito.when(validationServices.validateCustomer(userName)).thenReturn(customer);
//
//        Mockito.when(validationServices.validateImageId(id)).thenReturn(images);
//
//        Mockito.verify(validationServices).validateCustomer(userName);
//        Mockito.verify(validationServices).validateImageId(id);
//
////        Mockito.verify(customerSecurityImagesRepo).save(Mockito.any());
//
//
//    }
//}