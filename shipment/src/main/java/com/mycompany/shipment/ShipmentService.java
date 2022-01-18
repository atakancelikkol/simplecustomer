package com.mycompany.shipment;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.DeliverCallback;
@Service
@AllArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public void registerShipment(ShipmentRequest request) {
        request.products();
        Shipment shipment = Shipment.builder()
                .customerId(123)
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .products("product")
                .build();
        //todo check if email valid
        //todo check if email not taken
        //todo store customer in db     OK
        shipmentRepository.save(shipment);
    }

}
