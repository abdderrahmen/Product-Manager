package com.releasin.productmanager.controllers.product;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
public class ProductController {
    private final HashMap<String, Product> productMap = new HashMap<>();

    @PostMapping(path = "/products")
    Product createProduct(@RequestBody ProductInfo productInfo) {
        Product product = new Product();
        String id = UUID.randomUUID().toString();
        product.setId(id);
        product.setCreatedAt(Date.from(Instant.now()));
        product.setName(productInfo.getName());
        productMap.put(id, product);
        return product;
    }

    @PutMapping(path = "/products/{id}")
    Product updateProduct(@PathVariable String id, @Validated @RequestBody UpdateProductInfo productInfo) {
        Product proDetail =  productMap.get(id);
        proDetail.setName(productInfo.getName());
        productMap.put(id, proDetail);
        return proDetail;
    }

    @GetMapping(path = "/products")
    List<Product> getProducts() {
        return productMap.values().stream().toList();
    }
    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity<Void> deleteProducts(@PathVariable String id) {
        productMap.remove(id);
        return ResponseEntity.noContent().build();
    }
}
