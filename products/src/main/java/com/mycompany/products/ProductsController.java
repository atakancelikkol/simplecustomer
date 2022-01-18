package com.mycompany.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
//@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductsController {
    @Autowired
    private final ProductsService productsService;

    @PostMapping("api/v1/products")
    public void registerProducts(@RequestBody ProductsRequest productsRequest) {
        log.info("new products request {}", productsRequest);
        productsService.registerProducts(productsRequest);
    }

    @GetMapping("api/v1/products/{productId}/{productNumber}")
    public Boolean isAvailable (
            @PathVariable("productId") Integer productId,
            @PathVariable("productNumber") Integer productNumber) {
        boolean isProductAvailable = productsService.
                isProductAvailable(productId, productNumber);
        log.info("product check request for product {}", productId);

        return isProductAvailable;
    }
}
