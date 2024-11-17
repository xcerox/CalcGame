package org.doit.gamification.domain.model;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class GameResult {
    Integer score;
    List<BadgeType> badges;
}
