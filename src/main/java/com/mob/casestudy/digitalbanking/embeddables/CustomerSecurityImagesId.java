package com.mob.casestudy.digitalbanking.embeddables;

import lombok.*;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSecurityImagesId implements Serializable {

    private String customerId;
    private String securityImageId;
}
