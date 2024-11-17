package org.doit.multiplication.infrastructure.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChallengeAttemptDTO {
    private Integer factorA;
    private Integer factorB;
    private String userAlias;
    private Integer guess;
    private Boolean isCorrect;
}