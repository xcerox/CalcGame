package org.doit.gamification.infrastructure.out.persistence.repository;

import org.doit.gamification.domain.model.LeaderBoardRow;
import org.doit.gamification.infrastructure.out.persistence.entity.ScoreCardJpaEntity;
import org.doit.gamification.infrastructure.out.persistence.entity.UserScoreCountJpaRow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScoreCardJpaRepository extends CrudRepository<ScoreCardJpaEntity, Long> {

    @Query("SELECT SUM(s.score) FROM ScoreCardJpaEntity s WHERE s.userId = :userId GROUP BY s.userId")
    Optional<Integer> getTotalScoreForUser(@Param("userId") Long userId);

    Optional<List<ScoreCardJpaEntity>> findByUserIdOrderByScoreTimestampDesc(Long userId);

    @Query("SELECT s.userId AS userId, SUM(s.score) AS totalScore FROM ScoreCardJpaEntity s GROUP BY s.userId ORDER BY SUM(s.score) DESC")
    Optional<List<UserScoreCountJpaRow>> findFirst10();
}
