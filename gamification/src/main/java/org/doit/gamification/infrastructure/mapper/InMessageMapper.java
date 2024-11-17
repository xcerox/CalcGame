package org.doit.gamification.infrastructure.mapper;

import lombok.AllArgsConstructor;
import org.doit.gamification.domain.model.ChallengeSolved;
import org.doit.gamification.domain.model.LeaderBoardRow;
import org.doit.gamification.infrastructure.in.amqp.dto.ChallengeSolvedEventDTO;
import org.doit.gamification.infrastructure.in.rest.dto.ChallengeSolvedDTO;
import org.doit.gamification.infrastructure.in.rest.dto.LeaderBoardRowDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class InMessageMapper {

    private ModelMapper modelMapper;

    public ChallengeSolvedDTO toDTO(ChallengeSolved model) {
        return modelMapper.map(model, ChallengeSolvedDTO.class);
    }

    public ChallengeSolved toModel(ChallengeSolvedDTO dto) {
        return modelMapper.map(dto, ChallengeSolved.class);
    }

    public LeaderBoardRowDTO toDTO(LeaderBoardRow model) {
        return modelMapper.map(model, LeaderBoardRowDTO.class);
    }

    public List<LeaderBoardRowDTO> toDTOList(List<LeaderBoardRow> models) {
        return models.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ChallengeSolved toModel(ChallengeSolvedEventDTO dto) {
        return modelMapper.map(dto, ChallengeSolved.class);
    }

}
