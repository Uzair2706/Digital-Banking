package com.mob.casestudy.digitalbanking.entities;

import com.mob.casestudy.digitalbanking.embeddables.CustomerSecurityQuestionsId;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerSecurityQuestions {

    @EmbeddedId
    private CustomerSecurityQuestionsId customerSecurityQuestionsId = new CustomerSecurityQuestionsId();

    @Column(length = 50)
    private String securityQuestionAnswer;

    @Column(length = 50)
    private LocalDateTime createdOn;

    @ManyToOne
    @MapsId("customerId")
    private Customer customer;

    @ManyToOne
    @MapsId("securityQuestionId")
    private SecurityQuestions securityQuestions;

}
