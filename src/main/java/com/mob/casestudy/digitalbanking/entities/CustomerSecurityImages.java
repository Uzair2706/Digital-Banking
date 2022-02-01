package com.mob.casestudy.digitalbanking.entities;

import com.mob.casestudy.digitalbanking.dtos.CustomerSecurityImagesDto;
import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityImagesId;
import lombok.AllArgsConstructor;

import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
public class CustomerSecurityImages {

    @EmbeddedId
    private CustomerSecurityImagesId customerSecurityImagesId = new CustomerSecurityImagesId();

    @Column(length = 50)
    private String securityImageCaption;

    @Column(length = 50)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @OneToOne
    @MapsId("customerId")
    private Customer customer;

    @ManyToOne
    @MapsId("securityImageId")
    private SecurityImages securityImages;

    public CustomerSecurityImages() {
    }

    public CustomerSecurityImages(CustomerSecurityImagesId customerSecurityImagesId, String securityImageCaption, LocalDateTime createdOn) {
        this.customerSecurityImagesId = customerSecurityImagesId;
        this.securityImageCaption = securityImageCaption;
        this.createdOn = createdOn;
    }

    public CustomerSecurityImages(String securityImageCaption, LocalDateTime createdOn) {
        this.customerSecurityImagesId = new CustomerSecurityImagesId();
        this.securityImageCaption = securityImageCaption;
        this.createdOn = createdOn;
    }

    public CustomerSecurityImagesId getCustomerSecurityImagesId() {
        return customerSecurityImagesId;
    }

    public void setCustomerSecurityImagesId(CustomerSecurityImagesId customerSecurityImagesId) {
        this.customerSecurityImagesId = customerSecurityImagesId;
    }

    public String getSecurityImageCaption() {
        return securityImageCaption;
    }

    public void setSecurityImageCaption(String securityImageCaption) {
        this.securityImageCaption = securityImageCaption;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SecurityImages getSecurityImages() {
        return securityImages;
    }

    public void setSecurityImages(SecurityImages securityImages) {
        this.securityImages = securityImages;
    }

    public CustomerSecurityImagesDto toDto(){
        return new CustomerSecurityImagesDto(securityImages.getId(),securityImageCaption);
    }



}
