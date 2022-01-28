package com.mob.casestudy.digitalbanking.resources;

import com.mob.casestudy.digitalbanking.dtos.CustomerSecurityImagesDto;
import com.mob.casestudy.digitalbanking.dtos.GetSecurityQuestionsResponse;
import com.mob.casestudy.digitalbanking.services.CustomerSecurityImageServices;
import com.mob.casestudy.digitalbanking.services.CustomerSecurityQuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RequestMapping("/customer-service/")
@RestController
public class CustomerSecurityController {

    //TODO: Rename the controller
    //TODO: Try to inject lombok in project
    //TODO: Remove @Transactional annotation

    @Autowired
    CustomerSecurityQuestionServices customerSecurityQuestionServices;
    @Autowired
    CustomerSecurityImageServices customerSecurityImageServices;

    @GetMapping("/client-api/v1/customers/{userName}")
    public ResponseEntity<Object> retrieveQuestionsByUserName(@PathVariable String userName){
        GetSecurityQuestionsResponse getSecurityQuestionsResponse = customerSecurityQuestionServices.retrieveQuestions(userName);
        return ResponseEntity.ok().body(getSecurityQuestionsResponse);
    }

    @PutMapping("/client-api/v1/customers/{userName}")
    public ResponseEntity<Object> storeCustomerSecurityImagesByUserName(@PathVariable String userName, @Valid @RequestBody CustomerSecurityImagesDto customerSecurityImagesDto){

        customerSecurityImageServices.storeImages(userName,customerSecurityImagesDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
