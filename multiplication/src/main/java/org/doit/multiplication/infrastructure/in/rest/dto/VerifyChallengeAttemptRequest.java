package org.doit.multiplication.infrastructure.in.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyChallengeAttemptRequest {
     @Min(1) @Max(99)
     private Integer factorA;
     @Min(1) @Max(99)
     private Integer factorB;
     @NotBlank
     private String userAlias;
     @Positive(message = "{positive.validation.error}")
     private Integer guess;
}