package com.raymundo.doorshop.dto.auth.request;

import com.raymundo.doorshop.dto.BaseDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotEmpty(message = "Username should not be empty")
        @Size(max = 15, message = "Username size should be less than 16")
        String username,

        @NotEmpty(message = "Password should not be empty")
        String password

) implements BaseDto {
}
