package org.doit.gamification.infrastructure.mapper;

import lombok.AllArgsConstructor;
import org.doit.gamification.domain.model.LeaderBoardRow;
import org.doit.gamification.domain.model.ScoreCard;
import org.doit.gamification.infrastructure.out.persistence.entity.ScoreCardJpaEntity;
import org.doit.gamification.infrastructure.out.persistence.entity.UserScoreCountJpaRow;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ScoreCardMapper {

    private ModelMapper modelMapper;

    public ScoreCard toModel(ScoreCardJpaEntity entity) {
        return modelMapper.map(entity, ScoreCard.class);
    }

    public ScoreCardJpaEntity toEntity(ScoreCard model) {
        return modelMapper.map(model, ScoreCardJpaEntity.class);
    }

    public List<ScoreCard> toModels(List<ScoreCardJpaEntity> entities) {
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    public LeaderBoardRow toModel(UserScoreCountJpaRow row) {
        return null;
                //modelMapper.map(row, LeaderBoardRow.class);
    }

    public List<LeaderBoardRow> toModelsRow(List<UserScoreCountJpaRow> entities) {
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }
}
