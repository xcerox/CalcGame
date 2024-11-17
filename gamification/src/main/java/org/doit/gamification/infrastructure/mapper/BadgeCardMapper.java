package org.doit.gamification.infrastructure.mapper;

import lombok.AllArgsConstructor;
import org.doit.gamification.domain.model.BadgeCard;
import org.doit.gamification.infrastructure.out.persistence.entity.BadgeCardJpaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BadgeCardMapper {

    private ModelMapper modelMapper;

    public BadgeCard toModel(BadgeCardJpaEntity entity) {
        return modelMapper.map(entity, BadgeCard.class);
    }

    public BadgeCardJpaEntity toEntity(BadgeCard model){
        return modelMapper.map(model, BadgeCardJpaEntity.class);
    }

    public List<BadgeCardJpaEntity> toEntityList(List<BadgeCard> modelList) {
        return modelList.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
