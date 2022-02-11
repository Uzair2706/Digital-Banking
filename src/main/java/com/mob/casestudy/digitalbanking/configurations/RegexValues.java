package com.mob.casestudy.digitalbanking.configurations;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class RegexValues {

    @Value("${regex.email}")
    public String emailRegex;
    @Value("${regex.phone}")
    public String phoneRegex;
    @Value("${regex.username}")
    public String userRegex;
}
