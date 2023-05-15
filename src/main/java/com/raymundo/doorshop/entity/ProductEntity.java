package com.raymundo.doorshop.entity;

import com.raymundo.doorshop.dto.product.response.ProductResponse;
import com.raymundo.doorshop.util.DtoConvertable;
import jakarta.persistence.*;
import lombok.Data;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "_product")
@Data
public class ProductEntity implements BaseEntity, DtoConvertable<ProductResponse> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private URI image;

    @OneToMany(mappedBy = "product")
    private List<FavouriteEntity> favourites;

    @Override
    public ProductResponse toDto() {
        return new ProductResponse(name, description, color, price, image);
    }
}