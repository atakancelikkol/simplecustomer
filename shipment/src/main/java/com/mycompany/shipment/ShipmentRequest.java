package com.mycompany.shipment;

import java.util.List;

public record
ShipmentRequest(
        Integer customerId,
        String firstName,
        String lastName,
        String email,
        String products){
}