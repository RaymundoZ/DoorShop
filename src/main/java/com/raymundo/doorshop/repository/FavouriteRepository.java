package com.raymundo.doorshop.repository;

import com.raymundo.doorshop.entity.FavouriteEntity;
import com.raymundo.doorshop.entity.ProductEntity;
import com.raymundo.doorshop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FavouriteRepository extends JpaRepository<FavouriteEntity, UUID> {

    List<FavouriteEntity> findAllByUser(UserEntity user);

    Optional<FavouriteEntity> findFirstByUserAndProduct(UserEntity user, ProductEntity product);
}