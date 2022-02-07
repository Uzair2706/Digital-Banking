package com.mob.casestudy.digitalbanking.embeddables;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CustomerSecurityQuestionsId implements Serializable {

    private String customerId;
    private UUID securityQuestionId;
}
