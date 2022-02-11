package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.repositories.CustomerRepo;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.digitalbanking.openapi.model.CreateCustomerResponse;
import com.digitalbanking.openapi.model.CreateCustomerRequest;
import com.mob.casestudy.digitalbanking.entities.Customer;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CustomerServicesTest {

    @InjectMocks
    CustomerServices customerServices;
    @Mock
    ValidationHelper validationHelper;
    @Mock
    CustomerRepo customerRepo;

    @Test
    void creatingCustomerForApplication() {

        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        Customer customer = Customer.builder().id(UUID.randomUUID().toString()).build();
        Mockito.when(customerRepo.save(Mockito.any())).thenReturn(customer);
        ResponseEntity<CreateCustomerResponse> actual = customerServices.creatingCustomerForApplication(createCustomerRequest);
        Mockito.verify(validationHelper).validations(createCustomerRequest);
        Mockito.verify(validationHelper).verifyingUsernameFromDatabase(createCustomerRequest);
        Mockito.verify(customerRepo).save(Mockito.any());
        ResponseEntity<CreateCustomerResponse> expected = ResponseEntity.ok().body(new CreateCustomerResponse().id(customer.getId()));
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}