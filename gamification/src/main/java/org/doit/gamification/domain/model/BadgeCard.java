package org.doit.gamification.domain.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BadgeCard {
    private Long id;
    private Long userId;
    private Long badgeTimestamp;
    private String type;


    public BadgeCard(Long userId, String type) {
        this.userId = userId;
        this.type = type;
    }
}
