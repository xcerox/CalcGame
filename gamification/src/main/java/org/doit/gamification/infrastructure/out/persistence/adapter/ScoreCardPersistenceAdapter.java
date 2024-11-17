package org.doit.gamification.infrastructure.out.persistence.adapter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.gamification.application.port.out.ScoreCardPersistencePort;
import org.doit.gamification.domain.model.LeaderBoardRow;
import org.doit.gamification.domain.model.ScoreCard;
import org.doit.gamification.infrastructure.mapper.ScoreCardMapper;
import org.doit.gamification.infrastructure.out.persistence.repository.ScoreCardJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ScoreCardPersistenceAdapter implements ScoreCardPersistencePort {

    private ScoreCardJpaRepository scoreCardJpaRepository;
    private ScoreCardMapper scoreCardMapper;

    @Override
    public ScoreCard save(ScoreCard scoreCard) {
        return Optional.of(scoreCard)
                .map(scoreCardMapper::toEntity)
                .map(scoreCardJpaRepository::save)
                .map(scoreCardMapper::toModel)
                .get();
    }

    @Override
    public Optional<Integer> getTotalScoreForUser(Long userId) {
        return scoreCardJpaRepository.getTotalScoreForUser(userId);
    }

    @Override
    public List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(Long userId) {
        return scoreCardJpaRepository.findByUserIdOrderByScoreTimestampDesc(userId)
                .map(scoreCardMapper::toModels).get();
    }

    @Override
    public List<LeaderBoardRow> findFirst10() {
        return scoreCardJpaRepository.findFirst10().map(scoreCardMapper::toModelsRow).get();
    }
}
