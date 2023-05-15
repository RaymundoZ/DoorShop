package com.raymundo.doorshop.dto.product.request;

import com.raymundo.doorshop.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;

public record ProductParamName(

        @NotBlank(message = "Name should not be empty")
        String name
) implements BaseDto {
}
