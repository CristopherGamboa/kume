package com.kume.kume.presentation.mappers;

import com.kume.kume.application.dto.RoleDto;
import com.kume.kume.infraestructure.models.Role;

public class RoleMapper {
    public static RoleDto toDto(Role role) {
        if (role == null) {
            return null;
        }
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static Role toEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }
}
