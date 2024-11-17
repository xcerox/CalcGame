package org.doit.multiplication.infrastructure.out.persistence.adapter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.multiplication.application.port.out.UserPersistencePort;
import org.doit.multiplication.domain.models.User;
import org.doit.multiplication.infrastructure.mapper.UserMapper;
import org.doit.multiplication.infrastructure.out.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private UserJpaRepository userJpaRepository;
    private UserMapper userMapper;

    @Override
    public User save(User user) {
        return Optional.of(user)
                .map(userMapper::toEntity)
                .map(userJpaRepository::save)
                .map(userMapper::toModel)
                .get();
    }

    @Override
    public Optional<User> findByAlias(String alias) {
        return userJpaRepository.findByAlias(alias).map(userMapper::toModel);
    }

    @Override
    public List<User> findAllByIdIn(List<Long> idList) {
        return userMapper.toModelList(userJpaRepository.findAllByIdIn(idList));
    }
}
