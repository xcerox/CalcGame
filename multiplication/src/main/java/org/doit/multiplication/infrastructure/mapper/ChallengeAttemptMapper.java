package org.doit.multiplication.infrastructure.mapper;

import lombok.AllArgsConstructor;
import org.doit.multiplication.domain.models.ChallengeAttempt;
import org.doit.multiplication.infrastructure.in.rest.dto.VerifyChallengeAttemptRequest;
import org.doit.multiplication.infrastructure.in.rest.dto.ChallengeAttemptDTO;
import org.doit.multiplication.infrastructure.out.amqp.dto.ChallengeSolvedEventDTO;
import org.doit.multiplication.infrastructure.out.persistence.entity.ChallengeAttemptJpaEntity;
import org.doit.multiplication.infrastructure.out.rest.dto.ChallengeSolvedDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ChallengeAttemptMapper {

    private ModelMapper modelMapper;

    public ChallengeAttempt toModel(VerifyChallengeAttemptRequest request) {
        return modelMapper.map(request, ChallengeAttempt.class);

    }

    public ChallengeAttempt toModel(ChallengeAttemptJpaEntity entity) {
        return modelMapper.map(entity, ChallengeAttempt.class);
    }

    public List<ChallengeAttempt> toModelList(List<ChallengeAttemptJpaEntity> entities) {
        return entities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public ChallengeAttemptJpaEntity toEntity(ChallengeAttempt model) {
        return modelMapper.map(model, ChallengeAttemptJpaEntity.class);
    }

    public ChallengeAttemptDTO toDTO(ChallengeAttempt domain) {
        return modelMapper.map(domain, ChallengeAttemptDTO.class);
    }

    public ChallengeSolvedDTO toSolvedDTO(ChallengeAttempt domain) {
        return modelMapper.map(domain, ChallengeSolvedDTO.class);
    }

    public List<ChallengeAttemptDTO> toDTOList(List<ChallengeAttempt> dtoList) {
        return dtoList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    public ChallengeSolvedEventDTO toEventSolvedDTO(ChallengeAttempt domain) {
        return modelMapper.map(domain, ChallengeSolvedEventDTO.class);
    }

}
