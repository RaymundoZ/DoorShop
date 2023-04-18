package com.raymundo.doorshop.entity;

import com.raymundo.doorshop.dto.response.ProductResponse;
import com.raymundo.doorshop.util.DtoConvertable;
import jakarta.persistence.*;
import lombok.Data;

import java.net.URI;
import java.util.UUID;

@Entity
@Table(name = "_product")
@Data
public class ProductEntity implements BaseEntity, DtoConvertable<ProductResponse> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private URI image;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCartEntity shoppingCart;

    @Override
    public ProductResponse toDto() {
        return new ProductResponse(description, color, price, image);
    }
}