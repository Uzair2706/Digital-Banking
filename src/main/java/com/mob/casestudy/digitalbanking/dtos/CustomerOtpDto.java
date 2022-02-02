package com.mob.casestudy.digitalbanking.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOtpDto {

    private String userName;
    private String templateId;

}
