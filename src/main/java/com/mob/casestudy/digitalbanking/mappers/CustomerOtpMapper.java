package com.mob.casestudy.digitalbanking.mappers;

import com.digitalbanking.openapi.model.InitiateOtpRequest;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface CustomerOtpMapper {

    @Mapping(source = "otp",target = "otp")
    @Mapping(source = "customer",target = "customer")
    @Mapping(source = "otpMessage",target = "otpMessage")
    @Mapping(target = "createdOn" ,expression = "java(LocalDateTime.now())")
    @Mapping(target = "expiryOn" ,expression = "java(LocalDateTime.now().plusMinutes(5))")
    CustomerOtp initiatingOtpForCustomer(InitiateOtpRequest initiateOtpRequest, LocalDateTime localDateTime, String otp, Customer customer,
                                         String otpMessage, @MappingTarget CustomerOtp customerOtp);
}
