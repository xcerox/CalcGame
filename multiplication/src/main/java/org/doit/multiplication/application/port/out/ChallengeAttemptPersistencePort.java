package org.doit.multiplication.application.port.out;

import org.doit.multiplication.domain.models.ChallengeAttempt;

import java.util.List;

public interface ChallengeAttemptPersistencePort {
    ChallengeAttempt save(ChallengeAttempt challengeAttempt);
    List<ChallengeAttempt> findLastAttemptsByUserAlias(String userAlias);
}
