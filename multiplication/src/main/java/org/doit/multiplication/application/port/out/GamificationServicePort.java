package org.doit.multiplication.application.port.out;

import org.doit.multiplication.domain.models.ChallengeAttempt;

public interface GamificationServicePort {
    boolean sendAttempt(ChallengeAttempt attempt);
}
