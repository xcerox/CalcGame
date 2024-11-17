package org.doit.gamification.infrastructure.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaderBoardRowDTO {
    Long userId;
    Long totalScore;
    List<String> badges;
}
