package com.mob.casestudy.digitalbanking.embeddables;

import lombok.Builder;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Builder
public class CustomerSecurityImagesId implements Serializable {

    private String customerId;

    private String securityImageId;

    public CustomerSecurityImagesId() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSecurityImageId() {
        return securityImageId;
    }

    public void setSecurityImageId(String securityImageId) {
        this.securityImageId = securityImageId;
    }

    public CustomerSecurityImagesId(String customerId, String securityImageId) {
        this.customerId = customerId;
        this.securityImageId = securityImageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSecurityImagesId that = (CustomerSecurityImagesId) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(securityImageId, that.securityImageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, securityImageId);
    }
}
