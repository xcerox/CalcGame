package org.doit.multiplication.domain.models;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String alias;

    public User(String alias) {
        this.alias = alias;
    }
}
