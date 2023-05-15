package com.raymundo.doorshop.dto.product.response;

import com.raymundo.doorshop.dto.BaseDto;

import java.net.URI;

public record ProductResponse(
        String name,
        String description,
        String color,
        Double price,
        URI image

) implements BaseDto {
}
