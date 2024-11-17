package org.doit.multiplication.infrastructure.out.persistence.adapter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.multiplication.application.port.out.ChallengeAttemptPersistencePort;
import org.doit.multiplication.domain.models.ChallengeAttempt;
import org.doit.multiplication.infrastructure.mapper.ChallengeAttemptMapper;
import org.doit.multiplication.infrastructure.out.persistence.repository.ChallengeAttemptJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ChallengeAttemptPersistenceAdapter implements ChallengeAttemptPersistencePort {

    private ChallengeAttemptJpaRepository challengeAttemptJpaRepository;
    private ChallengeAttemptMapper challengeAttemptMapper;

    @Override
    public ChallengeAttempt save(ChallengeAttempt challengeAttempt) {
        return Optional.of(challengeAttempt)
                .map(challengeAttemptMapper::toEntity)
                .map(challengeAttemptJpaRepository::save)
                .map(challengeAttemptMapper::toModel)
                .get();
    }

    @Override
    public List<ChallengeAttempt> findLastAttemptsByUserAlias(String userAlias) {
        return Optional.of(userAlias)
                .map(challengeAttemptJpaRepository::findTop10ByUserAliasOrderByIdDesc)
                .map(challengeAttemptMapper::toModelList)
                .get();
    }
}
