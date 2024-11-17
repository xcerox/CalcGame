package org.doit.gamification.infrastructure.out.persistence.adapter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.gamification.application.port.out.BadgeCardPersistencePort;
import org.doit.gamification.domain.model.BadgeCard;
import org.doit.gamification.infrastructure.mapper.BadgeCardMapper;
import org.doit.gamification.infrastructure.out.persistence.repository.BadgeCardJpaRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class BadgeCardPersistenceAdapter implements BadgeCardPersistencePort {

    private BadgeCardJpaRespository badgeCardJpaRespository;
    private BadgeCardMapper badgeCardMapper;

    @Override
    public List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(Long userId) {
        return List.of();
    }

    @Override
    public void saveAll(List<BadgeCard> badgeCards) {
        badgeCardJpaRespository.saveAll(badgeCardMapper.toEntityList(badgeCards));
    }
}
