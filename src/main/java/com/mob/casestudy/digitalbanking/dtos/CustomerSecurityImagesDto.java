package com.mob.casestudy.digitalbanking.dtos;

import com.mob.casestudy.digitalbanking.constants.Constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CustomerSecurityImagesDto {

    @NotNull(message = Constants.FIELD_NOT_FOUND_DESCRIPTION)
    private String securityImageId;

    @NotNull(message = Constants.CAPTION_NOT_NULL_DESCRIPTION)
    @NotEmpty(message = Constants.CAPTION_NOT_EMPTY_DESCRIPTION)
    @Size(min = 3,message = Constants.CAPTION_SIZE_NOT_VALID_DESCRIPTION)
    private String securityImageCaption ;

    public CustomerSecurityImagesDto(String securityImageId, String securityImageCaption) {
        this.securityImageId = securityImageId;
        this.securityImageCaption = securityImageCaption;
    }

    public CustomerSecurityImagesDto() {
    }

    public String getSecurityImageId() {
        return securityImageId;
    }

    public String getSecurityImageCaption() {
        return securityImageCaption;
    }

}
