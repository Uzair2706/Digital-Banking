package com.mob.casestudy.digitalbanking.embeddables;

import lombok.*;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class CustomerOtpId implements Serializable {

    private String customerId;
    private UUID otpId;
    public CustomerOtpId() {
        otpId = UUID.randomUUID();
    }
}
