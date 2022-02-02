package com.mob.casestudy.digitalbanking.entities;

import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityImagesId;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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


}
