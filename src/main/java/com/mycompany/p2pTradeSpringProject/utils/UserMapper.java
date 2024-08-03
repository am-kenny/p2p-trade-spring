package com.mycompany.p2pTradeSpringProject.utils;

import com.mycompany.p2pTradeSpringProject.dto.RegistrationRequest;
import com.mycompany.p2pTradeSpringProject.dto.TradeUserDto;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;

public class UserMapper {

    /**
     * Converts TradeUserDto to User entity.
     *
     * @param dto the TradeUserDto object
     * @return the User entity
     */
    public static User toEntity(TradeUserDto dto) {
        if (dto == null) {
            return null;
        }
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .build();
    }

    /**
     * Converts User entity to TradeUserDto.
     *
     * @param entity the User entity
     * @return the TradeUserDto object
     */
    public static TradeUserDto toTradeUserDto(User entity) {
        if (entity == null) {
            return null;
        }
        return TradeUserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .build();
    }

    /**
     * Converts RegistrationRequest to User entity.
     *
     * @param dto the RegistrationRequest object
     * @return the User entity
     */
    public static User toEntity(RegistrationRequest dto) {
        if (dto == null) {
            return null;
        }
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }

    public static User toEntity(Integer id) { //TODO: Remove this method
        if (id == null) {
            return null;
        }
        return User.builder()
                .id(id)
                .build();
    }

}
