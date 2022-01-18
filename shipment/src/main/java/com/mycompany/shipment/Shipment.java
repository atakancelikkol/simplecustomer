package com.mycompany.shipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shipment {
    @Id
    @SequenceGenerator(
            name = "shipment_id_sequence",
            sequenceName = "shipment_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shipment_id_sequence"
    )

    private Integer id;
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    public String products;
}


