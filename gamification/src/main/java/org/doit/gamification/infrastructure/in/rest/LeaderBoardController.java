package org.doit.gamification.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.doit.gamification.application.usecase.GetCurrentLeaderBoardUserCase;
import org.doit.gamification.infrastructure.in.rest.dto.LeaderBoardRowDTO;
import org.doit.gamification.infrastructure.mapper.InMessageMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaders")
@RequiredArgsConstructor
public class LeaderBoardController {
    private final GetCurrentLeaderBoardUserCase getCurrentLeaderBoardUserCase;
    private final InMessageMapper restMessageMapper;

    @GetMapping
    public List<LeaderBoardRowDTO> getLeaderBoard() {
        return restMessageMapper.toDTOList(getCurrentLeaderBoardUserCase.getCurrentLeaderBoard());
    }
}
