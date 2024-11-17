package org.doit.multiplication.infrastructure.out.persistence.repository;

import org.doit.multiplication.infrastructure.out.persistence.entity.ChallengeAttemptJpaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChallengeAttemptJpaRepository extends CrudRepository<ChallengeAttemptJpaEntity, Long> {

    List<ChallengeAttemptJpaEntity> findTop10ByUserAliasOrderByIdDesc(String userAlias);

    @Query("SELECT a FROM ChallengeAttemptJpaEntity a WHERE a.user.alias = :userAlias ORDER BY a.id DESC")
    List<ChallengeAttemptJpaEntity> findLastAttemptsByUserAlias(@Param("userAlias") String userAlias);
}
