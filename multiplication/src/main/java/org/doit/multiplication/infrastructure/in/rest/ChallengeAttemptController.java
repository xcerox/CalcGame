package org.doit.multiplication.infrastructure.in.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.multiplication.application.usecase.FindStatsForUserUseCase;
import org.doit.multiplication.application.usecase.VerifyAttemptUseCase;
import org.doit.multiplication.domain.models.ChallengeAttempt;
import org.doit.multiplication.infrastructure.in.rest.dto.VerifyChallengeAttemptRequest;
import org.doit.multiplication.infrastructure.in.rest.dto.ChallengeAttemptDTO;
import org.doit.multiplication.infrastructure.mapper.ChallengeAttemptMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attempts")
@RequiredArgsConstructor
public class ChallengeAttemptController {

    private final VerifyAttemptUseCase verifyAttemptUseCase;
    private final FindStatsForUserUseCase findStatsForUser;
    private final ChallengeAttemptMapper challengeAttemptMapper;

    @PostMapping
    ResponseEntity<ChallengeAttemptDTO> validateAttempt(@RequestBody @Valid VerifyChallengeAttemptRequest request) {
        ChallengeAttempt attempt = challengeAttemptMapper.toModel(request);
        verifyAttemptUseCase.verifyAttempt(attempt);
        return ResponseEntity.ok(challengeAttemptMapper.toDTO(attempt));
    }

    @GetMapping
    ResponseEntity<List<ChallengeAttemptDTO>> getStatistics(@RequestParam("alias") String alias) {
        List<ChallengeAttempt> attemps = findStatsForUser.findStatsForUser(alias);
        return ResponseEntity.ok(challengeAttemptMapper.toDTOList(attemps));
    }

}
