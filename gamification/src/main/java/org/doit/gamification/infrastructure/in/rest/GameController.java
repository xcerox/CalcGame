package org.doit.gamification.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.doit.gamification.application.usecase.NewAttemptForUserUseCase;
import org.doit.gamification.infrastructure.in.rest.dto.ChallengeSolvedDTO;
import org.doit.gamification.infrastructure.mapper.InMessageMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attempts")
@RequiredArgsConstructor
public class GameController {
    private final NewAttemptForUserUseCase newAttemptForUserUseCase;
    private final InMessageMapper restMessageMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    void postResult(@RequestBody ChallengeSolvedDTO dto) {
        newAttemptForUserUseCase.newAttemptForUser(restMessageMapper.toModel(dto));
    }
}
