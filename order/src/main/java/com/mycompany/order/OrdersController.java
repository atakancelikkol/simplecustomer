package com.mycompany.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController

@AllArgsConstructor
public class OrdersController {
    @Autowired
    private final OrdersService ordersService;

    @PostMapping("api/v1/orders")
    public void registerOrders(@RequestBody OrdersRequest ordersRequest) {
        log.info("new orders request {}", ordersRequest);
        ordersService.registerOrders(ordersRequest);
    }
}
