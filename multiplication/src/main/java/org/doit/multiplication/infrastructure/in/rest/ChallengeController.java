package org.doit.multiplication.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.multiplication.application.usecase.GenerateRandomChallengeUseCase;
import org.doit.multiplication.domain.models.Challenge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final GenerateRandomChallengeUseCase generateRandomChallengeUseCase;

    @GetMapping("/random")
    Challenge getRandomChallenge(){
        Challenge challenge = generateRandomChallengeUseCase.randomChallenge();
        log.info("Generating a random challenge: {}", challenge);
        return challenge;
    }
}
