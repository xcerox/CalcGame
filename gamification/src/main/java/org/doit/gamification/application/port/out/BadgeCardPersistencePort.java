package org.doit.gamification.application.port.out;

import org.doit.gamification.domain.model.BadgeCard;

import java.util.List;

public interface BadgeCardPersistencePort {
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(Long userId);
    void saveAll(List<BadgeCard> badgeCards);
}
