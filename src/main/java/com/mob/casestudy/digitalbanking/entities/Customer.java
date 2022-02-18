package com.mob.casestudy.digitalbanking.entities;

import com.digitalbanking.openapi.model.PreferredLanguage;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import com.digitalbanking.openapi.model.Status;
import java.time.LocalDateTime;
import javax.persistence.*;
import java.util.List;
import lombok.*;

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

    @Column(length = 3)
    private String age;

    @Column(length = 50)
    private String email;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

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
