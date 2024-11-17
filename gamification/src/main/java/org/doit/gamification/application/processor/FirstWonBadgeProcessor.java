package org.doit.gamification.application.processor;

import org.doit.gamification.domain.model.BadgeType;
import org.doit.gamification.domain.model.ChallengeSolved;
import org.doit.gamification.domain.model.ScoreCard;
import org.doit.gamification.domain.processor.BadgeProcessor;

import java.util.List;
import java.util.Optional;

public class FirstWonBadgeProcessor implements BadgeProcessor {
    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolved challenge) {
        return scoreCardList.size() == 1 ?  Optional.of(BadgeType.FIRST_WON) : Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.FIRST_WON;
    }
}
