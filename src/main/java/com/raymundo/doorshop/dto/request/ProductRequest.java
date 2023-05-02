package com.raymundo.doorshop.dto.request;

import com.raymundo.doorshop.dto.BaseDto;
import com.raymundo.doorshop.entity.ProductEntity;
import com.raymundo.doorshop.util.EntityConvertable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.net.URI;

public record ProductRequest(

        @NotBlank(message = "Name should not be empty")
        String name,

        @NotBlank(message = "Description should not be empty")
        String description,

        @NotBlank(message = "Color should not be empty")
        String color,

        @NotNull(message = "Price should not be empty")
        @PositiveOrZero(message = "Price should not be negative")
        Double price,

        //TODO make uploading an image to the server
        @NotBlank(message = "Image should not be empty")
        String image

) implements BaseDto, EntityConvertable<ProductEntity> {

    @Override
    public ProductEntity toEntity() {
        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setDescription(description);
        product.setColor(color);
        product.setPrice(price);
        product.setImage(URI.create(image));
        return product;
    }
}
