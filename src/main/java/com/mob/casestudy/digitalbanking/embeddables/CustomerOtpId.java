package com.mob.casestudy.digitalbanking.embeddables;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class CustomerOtpId implements Serializable {

    private UUID customerId;

    private UUID otpId;

    public CustomerOtpId() {
        otpId = UUID.randomUUID();
    }

    public CustomerOtpId(UUID customerId, UUID otpId) {
        this.customerId = customerId;
        this.otpId = otpId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getOtpId() {
        return otpId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOtpId that = (CustomerOtpId) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(otpId, that.otpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, otpId);
    }
}
