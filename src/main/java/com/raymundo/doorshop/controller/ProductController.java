package com.raymundo.doorshop.controller;

import com.raymundo.doorshop.dto.request.ProductId;
import com.raymundo.doorshop.dto.request.ProductParamName;
import com.raymundo.doorshop.dto.request.ProductRequest;
import com.raymundo.doorshop.dto.response.ProductResponse;
import com.raymundo.doorshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping(value = "/find/name")
    public ResponseEntity<List<ProductResponse>> getProducts(@Valid @RequestBody ProductParamName name, BindingResult bindingResult) {
        return ResponseEntity.ok(productService.getProducts(name.name()));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult) {
        return ResponseEntity.ok(productService.addProduct(productRequest.toEntity()));
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<ProductResponse> removeProduct(@Valid @RequestBody ProductId productId, BindingResult bindingResult) {
        return ResponseEntity.ok(productService.removeProduct(productId.id()));
    }

}
