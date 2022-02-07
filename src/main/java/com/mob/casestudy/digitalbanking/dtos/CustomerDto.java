package com.mob.casestudy.digitalbanking.dtos;

import lombok.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,29}$")
    String userName;
    String firstName;
    String lastName;
    @Pattern(regexp = "[0-9]{10}")
    String phoneNumber;
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    String email;
    @Size(min = 2, max = 2, message = "Allowed Preferred Language formats are EN,FR,DE")
    String preferredLanguage;
}
