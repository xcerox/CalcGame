package org.doit.gamification.application.usecase;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.gamification.application.port.out.BadgeCardPersistencePort;
import org.doit.gamification.application.port.out.ScoreCardPersistencePort;
import org.doit.gamification.domain.model.*;
import org.doit.gamification.domain.processor.BadgeProcessor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class NewAttemptForUserUseCase {

    private final ScoreCardPersistencePort scoreCardPersistencePort;
    private final BadgeCardPersistencePort badgeCardPersistencePort;
    private final List<BadgeProcessor> badgeProcessors;

    @Transactional
    public GameResult newAttemptForUser(ChallengeSolved challenge) {
        log.info("entering newAttemptForUser: param => {} ", challenge);
        if (challenge.isCorrect()) {
            ScoreCard scoreCard = new ScoreCard(challenge.getUserId(), challenge.getAttemptId());

            scoreCardPersistencePort.save(scoreCard);
            log.info("User {} scored {} points for attempt id {}", challenge.getUserAlias(), scoreCard.getScore(), challenge.getAttemptId());
            List<BadgeCard> badgecards = processForBadges(challenge);

            List<BadgeType> Badges = badgecards.stream()
                    .map(BadgeCard::getType)
                    .map(BadgeType::getBadgeTypeByDescription)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            return new GameResult(scoreCard.getScore(), Badges);
        } else {
            log.info("Attempt id {} is not correct. User {} does not get score.", challenge.getAttemptId(), challenge.getUserAlias());
            return new GameResult(0, List.of());
        }

    }

    private List<BadgeCard> processForBadges(ChallengeSolved challenge) {
        Optional<Integer> optTotalScore = scoreCardPersistencePort.getTotalScoreForUser(challenge.getUserId());

        if(optTotalScore.isEmpty()) {
            return Collections.emptyList();
        }

        int totalScore = optTotalScore.get();
        List<ScoreCard> scoreCards = scoreCardPersistencePort.findByUserIdOrderByScoreTimestampDesc(challenge.getUserId());

        Set<BadgeType> alreadyGotBadges = badgeCardPersistencePort.findByUserIdOrderByBadgeTimestampDesc(challenge.getUserId())
                .stream()
                .map(BadgeCard::getType)
                .map(BadgeType::getBadgeTypeByDescription)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        List<BadgeCard> newBadgeCards = badgeProcessors.stream()
                .filter(bp -> !alreadyGotBadges.contains(bp.badgeType()))
                .map(bp -> bp.processForOptionalBadge(totalScore, scoreCards, challenge))
                .flatMap(Optional::stream)
                .map(badgeType -> new BadgeCard(challenge.getUserId(), badgeType.getDescription()))
                .collect(Collectors.toList());

        badgeCardPersistencePort.saveAll(newBadgeCards);
        return newBadgeCards;
    }
}
