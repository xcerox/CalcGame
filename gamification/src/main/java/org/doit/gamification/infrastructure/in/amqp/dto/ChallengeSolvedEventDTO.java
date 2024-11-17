package org.doit.gamification.infrastructure.in.amqp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeSolvedEventDTO {
    private Long attemptId;
    private Boolean isCorrect;
    private Integer factorA;
    private Integer factorB;
    private Long userId;
    private String userAlias;
}
