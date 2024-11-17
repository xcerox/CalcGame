package org.doit.multiplication.application.port.out;

import org.doit.multiplication.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {
    User save(User user);
    Optional<User> findByAlias(String alias);
    List<User> findAllByIdIn(List<Long> idList);
}
