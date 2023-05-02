package com.raymundo.doorshop.dto.request;

import com.raymundo.doorshop.dto.BaseDto;
import com.raymundo.doorshop.util.UUID;
import jakarta.validation.constraints.NotBlank;

public record ProductId(

        @NotBlank(message = "Id should not be empty")
        @UUID(message = "Id should be valid")
        String id
) implements BaseDto {
}
