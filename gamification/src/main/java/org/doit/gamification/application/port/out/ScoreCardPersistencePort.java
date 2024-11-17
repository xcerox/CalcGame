package org.doit.gamification.application.port.out;

import org.doit.gamification.domain.model.LeaderBoardRow;
import org.doit.gamification.domain.model.ScoreCard;
import org.doit.gamification.infrastructure.out.persistence.entity.ScoreCardJpaEntity;
import org.doit.gamification.infrastructure.out.persistence.entity.UserScoreCountJpaRow;

import java.util.List;
import java.util.Optional;

public interface ScoreCardPersistencePort {
    ScoreCard save(ScoreCard scoreCard);
    Optional<Integer> getTotalScoreForUser(Long userId);
    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(Long userId);
    List<LeaderBoardRow> findFirst10();
}
