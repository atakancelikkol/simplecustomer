package com.mycompany.products;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProductsService {
    private final ProductsRepository productsRepository;

    public void registerProducts(ProductsRequest request) {
        Products products = Products.builder()
                .productName(request.productName)
                .productNumber(request.productNumber)
                .build();
        //todo check if email valid
        //todo check if email not taken
        //todo store customer in db     OK
        productsRepository.save(products);
    }

    public boolean isProductAvailable(Integer productId,Integer productNumber) {

        Products foundProduct = productsRepository.getById(productId);
        log.info("productNumber :{} stockProduct:{}  ", productNumber, foundProduct.getProductNumber());
        if (productNumber > foundProduct.getProductNumber()){
            return false;
        }
        else{
            Products products = new Products (productId,
                    foundProduct.getProductName(),
                    foundProduct.getProductNumber() - productNumber);
            log.info("products.productNumber :{}  ", products.getProductNumber());
            productsRepository.save(products);
            return true;
        }
    }
}
