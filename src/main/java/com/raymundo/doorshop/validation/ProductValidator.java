package com.raymundo.doorshop.validation;

import com.raymundo.doorshop.dto.request.ProductId;
import com.raymundo.doorshop.entity.ProductEntity;
import com.raymundo.doorshop.exception.ProductNotFoundException;
import com.raymundo.doorshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ProductValidator implements Validator {

    private ProductRepository productRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ProductId.class);
    }

    @SneakyThrows
    @Override
    public void validate(Object target, Errors errors) {
        if (!supports(target.getClass())) return;
        ProductId productId = (ProductId) target;
        Optional<ProductEntity> optional = productRepository.findById(UUID.fromString(productId.id()));
        if (optional.isEmpty())
            throw new ProductNotFoundException(productId.id());
    }
}
