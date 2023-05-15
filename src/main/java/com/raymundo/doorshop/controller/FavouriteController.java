package com.raymundo.doorshop.controller;

import com.raymundo.doorshop.dto.product.request.ProductId;
import com.raymundo.doorshop.dto.product.response.ProductResponse;
import com.raymundo.doorshop.service.FavouriteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/favourite")
@AllArgsConstructor
public class FavouriteController {

    private FavouriteService favouriteService;

    @PostMapping(value = "/add")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductId productId, BindingResult bindingResult) {
        return ResponseEntity.ok(favouriteService.performAction(UUID.fromString(productId.id())));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(favouriteService.getAllProducts());
    }

}
