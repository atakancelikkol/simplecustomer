package com.mycompany.shipment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController

@AllArgsConstructor
public class ShipmentController {
    @Autowired
    private final ShipmentService shipmentService;

    @PostMapping("api/v1/shipment")
    public void registerShipment(@RequestBody ShipmentRequest shipmentRequest) {
        log.info("new shipment request {}", shipmentRequest);
        shipmentService.registerShipment(shipmentRequest);
    }
}
