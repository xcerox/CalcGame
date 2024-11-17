package org.doit.gamification.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.gamification.application.port.out.BadgeCardPersistencePort;
import org.doit.gamification.application.port.out.ScoreCardPersistencePort;
import org.doit.gamification.domain.model.BadgeCard;
import org.doit.gamification.domain.model.LeaderBoardRow;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class GetCurrentLeaderBoardUserCase {

    private final BadgeCardPersistencePort badgeCardPersistencePort;
    private final ScoreCardPersistencePort scoreCardPersistencePort;

    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        List<LeaderBoardRow> scoreOnly = scoreCardPersistencePort.findFirst10();

        return scoreOnly.stream().map(row -> {
            List<String> badges = badgeCardPersistencePort.findByUserIdOrderByBadgeTimestampDesc(row.getUserId())
                    .stream().map(BadgeCard::getType).toList();

            return row.withBadges(badges);
        }).toList();
    }
}
