package org.doit.multiplication.infrastructure.out.persistence.repository;


import org.doit.multiplication.infrastructure.out.persistence.entity.UserJpaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends CrudRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByAlias(final String alias);
    List<UserJpaEntity> findAllByIdIn(final List<Long> ids);
}
