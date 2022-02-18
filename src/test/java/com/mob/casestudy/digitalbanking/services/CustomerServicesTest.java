package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.configurations.AgeConstant;
import com.mob.casestudy.digitalbanking.configurations.RegexValues;
import com.mob.casestudy.digitalbanking.dtos.AgeResponseDto;
import com.mob.casestudy.digitalbanking.mappers.CustomerMapperImpl;
import com.mob.casestudy.digitalbanking.repositories.CustomerRepo;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import com.mob.casestudy.digitalbanking.entities.Customer;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import com.digitalbanking.openapi.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CustomerServicesTest {

    @InjectMocks
    CustomerServices customerServices;
    @Mock
    ValidationHelper validationHelper;
    @Mock
    CustomerRepo customerRepo;
    @Mock
    CustomerMapperImpl customerMapper;
    @Mock
    RegexValues regexValues;
    @Mock
    AgeConstant ageConstant;
    @Mock
    RestTemplate restTemplate;

    @Test
    void creatingCustomerForApplication() {

        String uri = "https://api.agify.io/?name=";
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        AgeResponseDto ageResponseDto = new AgeResponseDto("aaaaa","43","3456");
        Customer customer = new Customer().withId(UUID.randomUUID().toString()).withUserName("UzairKhan2706").withAge("43");
        String userName = customer.getUserName();
        Mockito.when(customerRepo.save(Mockito.any())).thenReturn(customer);
        Mockito.when(customerMapper.creatingCustomerFromCustomerDto(createCustomerRequest)).thenReturn(customer);
        Mockito.when(restTemplate.getForObject(uri + userName, AgeResponseDto.class)).thenReturn(ageResponseDto);
        Mockito.when(ageConstant.getUri()).thenReturn(uri);
        ResponseEntity<CreateCustomerResponse> actual = customerServices.creatingCustomerForApplication(createCustomerRequest);
        Mockito.verify(validationHelper).validations(createCustomerRequest);
        Mockito.verify(validationHelper).verifyingUsernameFromDatabase(createCustomerRequest);
        Mockito.verify(customerRepo).save(Mockito.any());
        ResponseEntity<CreateCustomerResponse> expected = ResponseEntity.ok().body(new CreateCustomerResponse().id(customer.getId()));
        Mockito.verify(customerMapper).creatingCustomerFromCustomerDto(createCustomerRequest);
        Mockito.verify(restTemplate).getForObject(uri + userName,AgeResponseDto.class);
        Mockito.verify(ageConstant).getUri();
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void updateCustomer() {
        String userName = "UzairKhan2706";
        String code = "CUS-UPDATE-NFD-001";
        Customer customer = new Customer();
        PatchCustomerRequest patchCustomerRequest = new PatchCustomerRequest();
        patchCustomerRequest.firstName("Uzair").lastName("Khan").phoneNumber(Mockito.any())
                        .email(Mockito.any()).preferredLanguage(PreferredLanguage.EN).status(Status.ACTIVE);
        Mockito.when(validationHelper.validateCustomer(userName,code)).thenReturn(customer);
        Mockito.when(regexValues.getPhoneRegex()).thenReturn("^[0-9]{10}$");
        Mockito.when(regexValues.getEmailRegex()).thenReturn("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        customerServices.updateCustomer(userName,patchCustomerRequest);
        Mockito.verify(validationHelper).validateCustomer(userName,code);
        Mockito.verify(customerMapper).updateCustomerFromCustomerDto(patchCustomerRequest,customer);

    }
}