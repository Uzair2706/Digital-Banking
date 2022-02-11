package com.mob.casestudy.digitalbanking.embeddables;

import javax.persistence.Embeddable;
import java.io.Serializable;
import lombok.*;

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
