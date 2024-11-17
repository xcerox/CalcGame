package org.doit.multiplication.infrastructure.out.amqp;

import lombok.extern.slf4j.Slf4j;
import org.doit.multiplication.application.port.out.GamificationServicePort;
import org.doit.multiplication.domain.models.ChallengeAttempt;
import org.doit.multiplication.infrastructure.mapper.ChallengeAttemptMapper;
import org.doit.multiplication.infrastructure.out.amqp.dto.ChallengeSolvedEventDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(value="service.gamification.type", havingValue = "rabbitMQ", matchIfMissing = true)
public class GamificationServiceRabbitMQAdapter implements GamificationServicePort {

    private final AmqpTemplate amqpTemplate;
    private final String challengesTopicExchange;;
    private ChallengeAttemptMapper challengeAttemptMapper;

    public GamificationServiceRabbitMQAdapter(AmqpTemplate amqpTemplate,
                                              ChallengeAttemptMapper challengeAttemptMapper,
                                              @Value("${amqp.exchange.attempts}") String challengesTopicExchange) {
        this.amqpTemplate = amqpTemplate;
        this.challengeAttemptMapper = challengeAttemptMapper;
        this.challengesTopicExchange = challengesTopicExchange;
    }

    @Override
    public boolean sendAttempt(ChallengeAttempt attempt) {
        ChallengeSolvedEventDTO event = challengeAttemptMapper.toEventSolvedDTO(attempt);
        String routingKey = buildRoutingKey(event);
        log.info("routing key {}", routingKey);
        amqpTemplate.convertAndSend(challengesTopicExchange, routingKey, event);
        return true;
    }

    private String buildRoutingKey(ChallengeSolvedEventDTO event) {
        return "attempt." + (Boolean.TRUE.equals(event.getIsCorrect()) ? "correct": "wrong");
    }
}
