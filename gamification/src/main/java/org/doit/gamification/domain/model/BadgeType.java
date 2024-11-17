package org.doit.gamification.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum BadgeType {

    BRONZE("Bronze"),
    SILVER("Silver"),
    GOLD("Gold"),

    FIRST_WON("First time"),
    LUCKY_NUMBER("Lucky number");

    private final String description;

    public static Optional<BadgeType> getBadgeTypeByDescription(String description) {
        for (BadgeType type: BadgeType.values()) {
            if (type.getDescription().equalsIgnoreCase(description)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
