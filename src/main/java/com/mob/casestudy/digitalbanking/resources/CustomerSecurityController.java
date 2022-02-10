package com.mob.casestudy.digitalbanking.resources;

import com.mob.casestudy.digitalbanking.dtos.CreateCustomerSecurityImageRequest;
import com.mob.casestudy.digitalbanking.services.CustomerSecurityImageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RequestMapping("/customer-service/")
@RestController
public class CustomerSecurityController {

    //TODO: Try to inject lombok in project
    @Autowired
    CustomerSecurityImageServices customerSecurityImageServices;

    @PutMapping("/client-api/v1/customers/{userName}")
    public ResponseEntity<Object> storeCustomerSecurityImagesByUserName(@PathVariable String userName, @Valid @RequestBody CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest) {
        customerSecurityImageServices.storeImages(userName, createCustomerSecurityImageRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
