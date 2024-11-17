package org.doit.multiplication.infrastructure.out.rest;

import lombok.extern.slf4j.Slf4j;
import org.doit.multiplication.application.port.out.GamificationServicePort;
import org.doit.multiplication.domain.models.ChallengeAttempt;
import org.doit.multiplication.infrastructure.mapper.ChallengeAttemptMapper;
import org.doit.multiplication.infrastructure.out.rest.dto.ChallengeSolvedDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@ConditionalOnProperty(value="service.gamification.type", havingValue = "rest", matchIfMissing = false)
public class GamificationServiceRestAdapter implements GamificationServicePort {

    private final RestTemplate restTemplate;
    private final String gamificationHostUrl;
    private ChallengeAttemptMapper challengeAttemptMapper;

    public GamificationServiceRestAdapter(RestTemplateBuilder builder,
                                          ChallengeAttemptMapper challengeAttemptMapper,
                                          @Value("${service.gamification.host}")
                                     String gamificationHostUrl){
        restTemplate = builder.build();
        this.challengeAttemptMapper = challengeAttemptMapper;
        this.gamificationHostUrl = gamificationHostUrl;
    }

    @Override
    public boolean sendAttempt(ChallengeAttempt attempt){
        try {
            ChallengeSolvedDTO dto = challengeAttemptMapper.toSolvedDTO(attempt);
            ResponseEntity<String> response = restTemplate.postForEntity(gamificationHostUrl + "/attempts", dto, String.class);
            log.info("Gamification service response: {}", response.getStatusCode());
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("There was a problem sending the attempt.", e);
            return false;
        }
    }

}
