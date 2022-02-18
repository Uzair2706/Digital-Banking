package com.mob.casestudy.digitalbanking.configurations;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class OtpConstant {

    @Value("${otp.origin}")
    public Integer origin;
    @Value("${otp.bound}")
    public Integer bound;
}
