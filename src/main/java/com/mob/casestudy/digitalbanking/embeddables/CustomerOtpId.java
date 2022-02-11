package com.mob.casestudy.digitalbanking.embeddables;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;
import lombok.*;

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
