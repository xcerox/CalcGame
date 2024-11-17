package org.doit.multiplication.infrastructure.out.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity()
@Table(name = "CHALLENGE_ATTEMPT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeAttemptJpaEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserJpaEntity user;
    private Integer factorA;
    private Integer factorB;
    private Integer guess;
    private Boolean isCorrect;

}
