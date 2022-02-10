package com.mob.casestudy.digitalbanking.entities;

import com.digitalbanking.openapi.model.PreferredLanguage;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@With
public class Customer {

    @Id
    @Column(length = 36)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

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
    private CustomerStatus status = CustomerStatus.ACTIVE;

    @Column(length = 2)
    @Enumerated(EnumType.STRING)
    private PreferredLanguage preferredLanguage;

    @Column(length = 50)
    private String externalId;

    @Column(length = 50)
    private String createdBy;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(length = 50)
    private String updatedBy;

    @CreationTimestamp
    private LocalDateTime updatedOn;

    public enum CustomerStatus {
        PENDING, ACTIVE, INACTIVE
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

}
