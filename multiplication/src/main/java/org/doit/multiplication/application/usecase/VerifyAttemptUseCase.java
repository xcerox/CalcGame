package org.doit.multiplication.application.usecase;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.multiplication.application.port.out.ChallengeAttemptPersistencePort;
import org.doit.multiplication.application.port.out.GamificationServicePort;
import org.doit.multiplication.application.port.out.UserPersistencePort;
import org.doit.multiplication.domain.models.ChallengeAttempt;
import org.doit.multiplication.domain.models.User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class VerifyAttemptUseCase {

    private UserPersistencePort userPersistencePort;
    private ChallengeAttemptPersistencePort challengeAttemptPersistencePort;
    private GamificationServicePort gamificationServiceClientPort;

    private Integer performOperation(ChallengeAttempt attempt) {
        return attempt.getFactorA() * attempt.getFactorB();
    }

    @Transactional
    public void verifyAttempt(ChallengeAttempt challengeAttempt) {

        User user = userPersistencePort.findByAlias(challengeAttempt.getUser().getAlias()).orElseGet(() -> {
            log.info("Creating new user with alias {}", challengeAttempt.getUser().getAlias());
            return userPersistencePort.save(challengeAttempt.getUser());
        });

        boolean isCorrect = challengeAttempt.getGuess().equals(performOperation(challengeAttempt));
        log.info("isCorrect {}", isCorrect);
        challengeAttempt.setIsCorrect(isCorrect);
        challengeAttempt.setUser(user);

        ChallengeAttempt  storedAttempt = challengeAttemptPersistencePort.save(challengeAttempt);
        log.info("storing attempt {}", storedAttempt);

        boolean status = gamificationServiceClientPort.sendAttempt(storedAttempt);
        log.info("Gamification service response: {}", status);
    }
}
