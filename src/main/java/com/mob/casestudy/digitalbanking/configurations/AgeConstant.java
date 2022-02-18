package com.mob.casestudy.digitalbanking.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AgeConstant {

    @Value("${customer.age}")
    public String uri;
}