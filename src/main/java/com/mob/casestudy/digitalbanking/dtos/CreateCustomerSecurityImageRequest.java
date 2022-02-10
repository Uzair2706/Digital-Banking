package com.mob.casestudy.digitalbanking.dtos;

import lombok.*;
import javax.validation.constraints.*;
import static com.mob.casestudy.digitalbanking.constants.Constants.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerSecurityImageRequest {

    @NotNull(message = FIELD_NOT_FOUND_DESCRIPTION)
    private String securityImageId;

    @NotNull(message = CAPTION_NOT_NULL_DESCRIPTION)
    @NotEmpty(message = CAPTION_NOT_EMPTY_DESCRIPTION)
    @Size(min = 3, message = CAPTION_SIZE_NOT_VALID_DESCRIPTION)
    private String securityImageCaption;
}
