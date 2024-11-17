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
@Table(name = "SCORE_CARD")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCardJpaEntity {
    public static final int DEFAULT_SCORE = 10;

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long attemptId;
    @EqualsAndHashCode.Exclude
    private long scoreTimestamp;
    private int score;

    public ScoreCardJpaEntity(final Long userId, final Long attempId) {
        this( null, userId, attempId, System.currentTimeMillis(), DEFAULT_SCORE);
    }

}
