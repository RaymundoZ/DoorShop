package com.raymundo.doorshop.service;

import com.raymundo.doorshop.dto.product.response.ProductResponse;
import com.raymundo.doorshop.entity.FavouriteEntity;
import com.raymundo.doorshop.entity.ProductEntity;
import com.raymundo.doorshop.entity.UserEntity;
import com.raymundo.doorshop.repository.FavouriteRepository;
import com.raymundo.doorshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FavouriteService {

    private SecurityContextHolderStrategy securityContextHolderStrategy;
    private ProductRepository productRepository;
    private FavouriteRepository favouriteRepository;

    public ProductResponse performAction(UUID productId) {
        UserEntity user = (UserEntity) securityContextHolderStrategy.getContext().getAuthentication().getPrincipal();
        ProductEntity product = productRepository.findById(productId).get();
        Optional<FavouriteEntity> optional = favouriteRepository.findFirstByUserAndProduct(user, product);
        if (optional.isEmpty())
            return addProduct(user, product);
        else
            return removeProduct(optional.get(), product);
    }

    public List<ProductResponse> getAllProducts() {
        UserEntity user = (UserEntity) securityContextHolderStrategy.getContext().getAuthentication().getPrincipal();
        return favouriteRepository.findAllByUser(user).stream().map(f -> f.getProduct().toDto()).toList();
    }

    private ProductResponse addProduct(UserEntity user, ProductEntity product) {
        FavouriteEntity favourite = new FavouriteEntity();
        favourite.setUser(user);
        favourite.setProduct(product);
        favouriteRepository.save(favourite);
        return product.toDto();
    }

    private ProductResponse removeProduct(FavouriteEntity favourite, ProductEntity product) {
        favouriteRepository.delete(favourite);
        return product.toDto();
    }

}
