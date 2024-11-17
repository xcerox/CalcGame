package org.doit.multiplication.application.usecase;

import org.doit.multiplication.domain.models.Challenge;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GenerateRandomChallengeUseCase {
    private final static int MINIMUM_FACTOR = 11;
    private final static int MAXIMUM_FACTOR = 100;

    private final Random random;

    public GenerateRandomChallengeUseCase() {
        this.random = new Random();
    }

    public GenerateRandomChallengeUseCase(Random random) {
        this.random = random;
    }

    private Integer next() {
        return random.nextInt(MAXIMUM_FACTOR - MINIMUM_FACTOR) + MINIMUM_FACTOR;
    }

    public Challenge randomChallenge() {
        return new Challenge(next(), next());
    }
}
