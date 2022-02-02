package com.mob.casestudy.digitalbanking.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityImages {

    @Id
    @Column(length = 36)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(length = 50)
    private String securityImageName;

    @Column
    private String securityImageUrl;

    @OneToMany(mappedBy = "securityImages")
    private List<CustomerSecurityImages> customerSecurityImages = new ArrayList<>();

    public void addCustomerSecurityImages(CustomerSecurityImages customerSecurityImages) {
        this.customerSecurityImages.add(customerSecurityImages);
    }

    public void removeCustomerSecurityImages(CustomerSecurityImages customerSecurityImages) {
        this.customerSecurityImages.remove(customerSecurityImages);
    }

}
