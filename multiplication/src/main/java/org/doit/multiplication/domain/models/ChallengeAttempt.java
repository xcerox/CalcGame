package org.doit.multiplication.domain.models;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeAttempt {
    private Long id;
    private User user;
    private Integer factorA;
    private Integer factorB;
    private Integer guess;
    private Boolean isCorrect;
}
