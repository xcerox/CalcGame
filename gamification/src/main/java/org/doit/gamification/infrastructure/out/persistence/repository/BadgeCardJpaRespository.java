package org.doit.gamification.infrastructure.out.persistence.repository;

import org.doit.gamification.infrastructure.out.persistence.entity.BadgeCardJpaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeCardJpaRespository extends CrudRepository<BadgeCardJpaEntity, Long> {
    List<BadgeCardJpaEntity> findByUserIdOrderByBadgeTimestampDesc(Long userId);
}
