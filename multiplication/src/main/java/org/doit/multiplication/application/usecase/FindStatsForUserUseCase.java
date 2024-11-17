package org.doit.multiplication.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.multiplication.application.port.out.ChallengeAttemptPersistencePort;
import org.doit.multiplication.domain.models.ChallengeAttempt;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FindStatsForUserUseCase {

    private ChallengeAttemptPersistencePort challengeAttemptPersistencePort;

    public List<ChallengeAttempt> findStatsForUser(String userAlias) {
        return challengeAttemptPersistencePort.findLastAttemptsByUserAlias(userAlias);
    }
}
