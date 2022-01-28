package com.mob.casestudy.digitalbanking.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class SecurityImages {

    @Id
    @Column(length = 36)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(length = 50)
    private String securityImageName;

    @Column(length = 255)
    private String securityImageUrl;

    @OneToMany(mappedBy = "securityImages")
    private List<CustomerSecurityImages> customerSecurityImages = new ArrayList<>();

    public SecurityImages() {
    }

    public SecurityImages(String securityImageName, String securityImageUrl) {
        this.securityImageName = securityImageName;
        this.securityImageUrl = securityImageUrl;
    }

    public SecurityImages(UUID id, String securityImageName, String securityImageUrl) {
        this.id = id;
        this.securityImageName = securityImageName;
        this.securityImageUrl = securityImageUrl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSecurityImageName() {
        return securityImageName;
    }

    public void setSecurityImageName(String securityImageName) {
        this.securityImageName = securityImageName;
    }

    public String getSecurityImageUrl() {
        return securityImageUrl;
    }

    public void setSecurityImageUrl(String securityImageUrl) {
        this.securityImageUrl = securityImageUrl;
    }

    public List<CustomerSecurityImages> getCustomerSecurityImages() {
        return customerSecurityImages;
    }

    public void addCustomerSecurityImages(CustomerSecurityImages customerSecurityImages) {
        this.customerSecurityImages.add(customerSecurityImages);
    }

    public void removeCustomerSecurityImages(CustomerSecurityImages customerSecurityImages) {
        this.customerSecurityImages.remove(customerSecurityImages);
    }
}
