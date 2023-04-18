package com.raymundo.doorshop.dto.response;

import com.raymundo.doorshop.dto.BaseDto;

import java.net.URI;

public record ProductResponse(
        String description,
        String color,
        Double price,
        URI image

) implements BaseDto {
}
