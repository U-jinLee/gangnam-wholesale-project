package com.gangnam.wholesale.presentation.customer.product;

import com.gangnam.wholesale.application.product.ProductService;
import com.gangnam.wholesale.common.ApiMappingAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(ApiMappingAttributes.PRODUCT_API)
@RestController
public class ProductApiController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getProducts() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") long id) {
        return ResponseEntity.ok(null);
    }
}