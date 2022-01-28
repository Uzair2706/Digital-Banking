package com.mob.casestudy.digitalbanking.entities;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder()
public class Customer {

    @Id
    @Column(length = 36)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(length = 30)
    private String userName;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 10)
    private String phoneNumber;

    @Column(length = 50)
    private String email;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private CustomerStatus status = CustomerStatus.PENDING;

    @Column(length = 2)
    @Enumerated(EnumType.STRING)
    private CustomerPreferredLanguage preferredLanguage;

    @Column(length = 50)
    private String externalId;

    @Column(length = 50)
    private String createdBy;

    private LocalDateTime createdOn;

    @Column(length = 50)
    private String updatedBy;

    private LocalDateTime updatedOn;

    public enum CustomerStatus {
        PENDING, ACTIVE, INACTIVE
    }

    public enum CustomerPreferredLanguage {
        EN , FR , DE
    }

    @OneToMany(mappedBy = "customer")
    private List<CustomerSecurityQuestions> customerSecurityQuestions;

    @OneToOne(mappedBy = "customer")
    private CustomerSecurityImages customerSecurityImages;

    @OneToOne(mappedBy = "customer")
    private CustomerOtp customerOtp;

    public List<CustomerSecurityQuestions> getCustomerSecurityQuestions() {
        return customerSecurityQuestions;
    }

    public void addCustomerSecurityQuestions(CustomerSecurityQuestions customerSecurityQuestions) {
        this.customerSecurityQuestions.add(customerSecurityQuestions);
    }

    public void removeCustomerSecurityQuestions(CustomerSecurityQuestions customerSecurityQuestions) {
        this.customerSecurityQuestions.remove(customerSecurityQuestions);
    }

    public CustomerSecurityImages getCustomerSecurityImages() {
        return customerSecurityImages;
    }

    public void setCustomerSecurityImages(CustomerSecurityImages customerSecurityImages) {
        this.customerSecurityImages = customerSecurityImages;
    }

    public CustomerOtp getCustomerOtp() {
        return customerOtp;
    }

    public void setCustomerOtp(CustomerOtp customerOtp) {
        this.customerOtp = customerOtp;
    }

}
