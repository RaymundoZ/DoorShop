package com.raymundo.doorshop.dto.response;

import com.raymundo.doorshop.dto.BaseDto;
import com.raymundo.doorshop.util.Role;

public record UserResponse(
        String username,

        String email,

        Role role

) implements BaseDto {

}