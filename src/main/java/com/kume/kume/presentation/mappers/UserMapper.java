package com.kume.kume.presentation.mappers;

import com.kume.kume.infraestructure.models.User;
import com.kume.kume.application.dto.user.CreateUserRequest;

public class UserMapper {
    public static CreateUserRequest toDto(User user) {
        if (user == null) {
            return null;
        }

        return CreateUserRequest.builder()
                .fullName(user.getFullName())
                .username(user.getUsername())
                .build();
    }
    public static User toEntity(CreateUserRequest userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        return user;
    }
}
