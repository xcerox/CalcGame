package org.doit.multiplication.domain.models;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Challenge {
    private int factorA;
    private int factorB;
}
