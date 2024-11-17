package org.doit.gamification.domain.model;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCard {
    private Long id;
    private Long userId;
    private Long attemptId;
    private long scoreTimestamp;
    private int score;

    public ScoreCard(Long userId, Long attemptId) {
        this.userId = userId;
        this.attemptId = attemptId;
    }

}
