package com.mob.casestudy.digitalbanking.services;

import com.mob.casestudy.digitalbanking.exceptions.BadRequestExceptions;
import com.mob.casestudy.digitalbanking.exceptions.NotFoundExceptions;
import static com.mob.casestudy.digitalbanking.constants.Constants.*;
import com.mob.casestudy.digitalbanking.configurations.RegexValues;
import com.mob.casestudy.digitalbanking.configurations.AgeConstant;
import com.mob.casestudy.digitalbanking.mappers.CustomerMapperImpl;
import com.mob.casestudy.digitalbanking.repositories.CustomerRepo;
import com.mob.casestudy.digitalbanking.helpers.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.mob.casestudy.digitalbanking.dtos.AgeResponseDto;
import com.mob.casestudy.digitalbanking.entities.Customer;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.digitalbanking.openapi.model.*;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class CustomerServices {

    @Autowired
    ValidationHelper validationHelper;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    RegexValues regexValues;
    @Autowired
    CustomerMapperImpl customerMapper;
    @Autowired
    AgeConstant ageConstant;

    RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public ResponseEntity<CreateCustomerResponse> creatingCustomerForApplication(CreateCustomerRequest createCustomerRequest) {
        validationHelper.validations(createCustomerRequest);
        validationHelper.verifyingUsernameFromDatabase(createCustomerRequest);
        Customer customer = customerMapper.creatingCustomerFromCustomerDto(createCustomerRequest);
        String age = getMethod(customer);
        customer.setAge(age);
        return ResponseEntity.ok().body(new CreateCustomerResponse().id(customerRepo.save(customer).getId()));
    }

    @Transactional
    public ResponseEntity<Void> updateCustomer(String username, PatchCustomerRequest patchCustomerRequest) {
        Customer customer = validationHelper.validateCustomer(username, UPD_CUS_CODE);
        validateAndUpdate(patchCustomerRequest);
        customerRepo.save(customerMapper.updateCustomerFromCustomerDto(patchCustomerRequest, customer));
        return ResponseEntity.noContent().build();
    }

    private void validateAndUpdate(PatchCustomerRequest patchCustomerRequest) {
        String firstName = nullOrEmpty(patchCustomerRequest.getFirstName());
        String lastname = nullOrEmpty(patchCustomerRequest.getLastName());
        String phoneNumber = regexMatching(nullOrEmpty(patchCustomerRequest.getPhoneNumber()), regexValues.getPhoneRegex(), UPD_PHN_CODE, UPD_PHN_DESCRIPTION);
        String email = regexMatching(nullOrEmpty(patchCustomerRequest.getEmail()), regexValues.getEmailRegex(), UPD_EML_CODE, UPD_EML_DESCRIPTION);
        patchCustomerRequest.firstName(firstName).lastName(lastname).phoneNumber(phoneNumber).email(email);
    }

    private String regexMatching(String value, String pattern, String code, String description) {
        if (Objects.nonNull(value) && !value.matches(pattern))
            throw new BadRequestExceptions(code, description);
        return value;
    }

    private String nullOrEmpty(String patchCustomerRequest) {
        if (patchCustomerRequest == null || patchCustomerRequest.isEmpty()) {
            return null;
        }
        return patchCustomerRequest;
    }

    private String getMethod(Customer customer) {
        AgeResponseDto ageResponseDto = restTemplate.getForObject(ageConstant.getUri() + customer.getUserName(), AgeResponseDto.class);
        return Objects.requireNonNull(ageResponseDto).getAge();
    }


    public ResponseEntity<GetCustomerResponse> getCustomers(String id, String userName) {
        Customer customer = getCustomerByIdOrUserName(id, userName);
        return ResponseEntity.ok(customerMapper.toDto(customer));
    }

    private Customer getCustomerByIdOrUserName(String id, String userName) {
        if ((id == null || id.isEmpty()) && (userName == null || userName.isEmpty())) {
            throw new BadRequestExceptions(GET_CUS_MAND_CODE, GET_CUS_MAND_DESCRIPTION);
        } else if (id != null && userName != null) {
            return validateCustomerFromTheList(id, userName);
        }
        if (id != null && !id.trim().isEmpty())
            return validateCustomerById(id);
        else
            return validationHelper.validateCustomer(userName, GET_CUS_NFD_CODE);
    }

    private Customer validateCustomerFromTheList(String id, String userName) {
        List<Customer> customerList = customerRepo.findByUserNameOrId(userName, id);
        if (customerList.isEmpty()) {
            throw new NotFoundExceptions(GET_CUS_NFD_CODE, GET_CUS_NFD_DESCRIPTION);
        }
        for (Customer customer : customerList) {
            if (customer.getId().equalsIgnoreCase(id))
                return customer;
        }
        return customerList.get(0);
    }

    private Customer validateCustomerById(String id) {
        Optional<Customer> customerRepoById = customerRepo.findById(id);
        if (customerRepoById.isEmpty()) {
            throw new NotFoundExceptions(GET_CUS_NFD_CODE, "The customer with id '" + id + "' is not registered with the system");
        }
        return customerRepoById.get();
    }
}

