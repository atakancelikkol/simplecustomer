package com.mycompany.order;

public record
OrdersRequest(
        Integer customerId,
        String paymentMethod,
        Integer products,
        Integer productsNumber){
}