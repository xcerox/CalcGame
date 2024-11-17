package org.doit.gamification.domain.model;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class LeaderBoardRow {
    Long userId;
    Long totalScore;
    @With
    List<String> badges;

    public LeaderBoardRow(Long userId, Long totalScore){
        this.userId = userId;
        this.totalScore = totalScore;
        this.badges = List.of();
    }
}
