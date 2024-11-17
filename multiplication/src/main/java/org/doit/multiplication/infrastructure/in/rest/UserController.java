package org.doit.multiplication.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doit.multiplication.application.port.out.UserPersistencePort;
import org.doit.multiplication.infrastructure.in.rest.dto.UserDTO;
import org.doit.multiplication.infrastructure.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserPersistencePort userPersistencePort;
    private final UserMapper userMapper;

    @GetMapping("/{idList}")
    public List<UserDTO> getUsersByIdList(@PathVariable List<Long> idList) {
        return userMapper.toDTOList(userPersistencePort.findAllByIdIn(idList));
    }
}
