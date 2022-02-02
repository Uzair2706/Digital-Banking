package com.mob.casestudy.digitalbanking.entities;

import com.mob.casestudy.digitalbanking.embeddables.CustomerOtpId;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerOtp {

    @EmbeddedId
    private CustomerOtpId customerOtpId = new CustomerOtpId();

    @Column(length = 160)
    private String otpMessage;

    @Column(length = 6)
    private String otp;

    @Column(scale = 1)
    private Integer otpRetries;

    private LocalDateTime expiryOn;

    private LocalDateTime createdOn;

    @OneToOne
    @MapsId("customerId")
    private Customer customer;

}
