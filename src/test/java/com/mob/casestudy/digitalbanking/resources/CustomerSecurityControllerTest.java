package com.mob.casestudy.digitalbanking.resources;

import com.mob.casestudy.digitalbanking.dtos.CreateCustomerSecurityImageRequest;
import com.mob.casestudy.digitalbanking.services.CustomerSecurityImageServices;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CustomerSecurityControllerTest {

    @InjectMocks
    CustomerSecurityController customerSecurityController;
    @Mock
    CustomerSecurityImageServices customerSecurityImageServices;

    @Test
    void storeCustomerSecurityImagesByUserName() {
        String userName = "UzairKhan2706";
        CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest = new CreateCustomerSecurityImageRequest();
        customerSecurityImageServices.storeImages(userName, createCustomerSecurityImageRequest);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.CREATED).build();
        ResponseEntity<Object> actual = customerSecurityController.storeCustomerSecurityImagesByUserName(userName, createCustomerSecurityImageRequest);
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}