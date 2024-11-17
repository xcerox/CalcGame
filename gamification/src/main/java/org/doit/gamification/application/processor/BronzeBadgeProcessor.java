package org.doit.gamification.application.processor;

import org.doit.gamification.domain.model.BadgeType;
import org.doit.gamification.domain.model.ChallengeSolved;
import org.doit.gamification.domain.model.ScoreCard;
import org.doit.gamification.domain.processor.BadgeProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BronzeBadgeProcessor implements BadgeProcessor {

    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolved challenge) {
        return currentScore > 50 ? Optional.of(BadgeType.BRONZE) : Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.BRONZE;
    }
}
