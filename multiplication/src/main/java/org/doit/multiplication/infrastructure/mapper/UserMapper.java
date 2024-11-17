package org.doit.multiplication.infrastructure.mapper;

import lombok.AllArgsConstructor;
import org.doit.multiplication.domain.models.User;
import org.doit.multiplication.infrastructure.in.rest.dto.UserDTO;
import org.doit.multiplication.infrastructure.out.persistence.entity.UserJpaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private ModelMapper modelMapper;

    public UserJpaEntity toEntity(User user) {
        return modelMapper.map(user, UserJpaEntity.class);
    }

    public User toModel(UserJpaEntity userJpaEntity) {
        return modelMapper.map(userJpaEntity, User.class);
    }

    public List<User> toModelList(List<UserJpaEntity> list) {
        return list.stream().map(this::toModel).collect(Collectors.toList());
    }

    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> toDTOList(List<User> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
