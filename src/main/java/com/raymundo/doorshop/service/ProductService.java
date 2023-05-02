package com.raymundo.doorshop.service;

import com.raymundo.doorshop.dto.response.ProductResponse;
import com.raymundo.doorshop.entity.ProductEntity;
import com.raymundo.doorshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<ProductResponse> getProducts() {
        return productRepository.findAll().stream().map(ProductEntity::toDto).toList();
    }

    public List<ProductResponse> getProducts(String name) {
        return productRepository.findAllByNameIsContainingIgnoreCase(name).stream().map(ProductEntity::toDto).toList();
    }


    public ProductResponse addProduct(ProductEntity product) {
        productRepository.save(product);
        return product.toDto();
    }

    public ProductResponse removeProduct(String id) {
        ProductEntity product = productRepository.findById(UUID.fromString(id)).get();
        productRepository.delete(product);
        return product.toDto();
    }

}
