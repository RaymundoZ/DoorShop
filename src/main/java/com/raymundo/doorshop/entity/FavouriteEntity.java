package com.raymundo.doorshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "_favourite")
@Data
public class FavouriteEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    private ProductEntity product;

    @ManyToOne
    private UserEntity user;

}