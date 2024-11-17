package org.doit.gamification.infrastructure.in.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.gamification.application.usecase.NewAttemptForUserUseCase;
import org.doit.gamification.infrastructure.in.amqp.dto.ChallengeSolvedEventDTO;
import org.doit.gamification.infrastructure.mapper.InMessageMapper;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameEventHandler {

    private final NewAttemptForUserUseCase newAttemptForUserUseCase;
    private final InMessageMapper inMessageMapper;

    @RabbitListener(queues = "${amqp.queue.gamification}")
    void handleMultiplicationSolved(ChallengeSolvedEventDTO event) {
        log.info("Challenge Solved Event received: {}", event.getAttemptId());
        try {
            newAttemptForUserUseCase.newAttemptForUser(inMessageMapper.toModel(event));
        } catch (final Exception e) {
            log.error("Error when trying to process ChallengeSolvedEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
