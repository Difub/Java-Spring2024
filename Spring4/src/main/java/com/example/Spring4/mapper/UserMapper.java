package com.example.Spring4.mapper;

import com.example.Spring4.dto.UserDTO;
import com.example.Spring4.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", ignore = true)
    UserDTO userToDto(User user);

    @Mapping(target = "roles", ignore = true)
    User dtoToUser(UserDTO userDTO);
}
