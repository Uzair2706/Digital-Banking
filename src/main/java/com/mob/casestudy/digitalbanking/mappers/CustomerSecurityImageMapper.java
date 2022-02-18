package com.mob.casestudy.digitalbanking.mappers;

import com.digitalbanking.openapi.model.CreateCustomerSecurityImageRequest;
import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityImagesId;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerSecurityImageMapper {

    @Mapping(source = "createCustomerSecurityImageRequest.securityImageCaption" ,target = "securityImageCaption")
    @Mapping(source = "customer" ,target = "customer")
    @Mapping(source = "securityImages" ,target = "securityImages")
    @Mapping(source = "customerSecurityImagesId", target = "customerSecurityImagesId")
    CustomerSecurityImages updateCustomerSecurityImageByCustomer(CreateCustomerSecurityImageRequest createCustomerSecurityImageRequest, Customer customer, SecurityImages securityImages, CustomerSecurityImagesId customerSecurityImagesId);
}
