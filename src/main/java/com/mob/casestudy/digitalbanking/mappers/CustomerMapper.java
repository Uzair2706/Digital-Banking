package com.mob.casestudy.digitalbanking.mappers;

import com.digitalbanking.openapi.model.CreateCustomerRequest;
import com.digitalbanking.openapi.model.GetCustomerResponse;
import com.digitalbanking.openapi.model.PatchCustomerRequest;
import com.mob.casestudy.digitalbanking.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    Customer updateCustomerFromCustomerDto(PatchCustomerRequest patchCustomerRequest, @MappingTarget Customer customer);

    Customer creatingCustomerFromCustomerDto(CreateCustomerRequest createCustomerRequest);

    GetCustomerResponse toDto(Customer customer);
}
