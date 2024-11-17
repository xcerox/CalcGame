package org.doit.gamification.infrastructure.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BADGE_CARD")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadgeCardJpaEntity {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    @EqualsAndHashCode.Exclude
    private Long badgeTimestamp;
    private String type;

    public BadgeCardJpaEntity(Long userId, String badgeType) {
        this(null, userId, System.currentTimeMillis(), badgeType);
    }

}
