package org.doit.multiplication.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.multiplication.application.port.out.UserPersistencePort;
import org.doit.multiplication.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FindUsersByIdUseCase {

    private final UserPersistencePort userPersistencePort;

    List<User> findAllByIdIn(List<Long> idList) {
        return userPersistencePort.findAllByIdIn(idList);
    }
}
