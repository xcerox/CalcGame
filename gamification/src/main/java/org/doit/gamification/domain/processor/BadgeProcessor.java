package org.doit.gamification.domain.processor;

import org.doit.gamification.domain.model.BadgeType;
import org.doit.gamification.domain.model.ChallengeSolved;
import org.doit.gamification.domain.model.ScoreCard;

import java.util.List;
import java.util.Optional;

public interface BadgeProcessor {

    Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolved challenge);
    BadgeType badgeType();
}
