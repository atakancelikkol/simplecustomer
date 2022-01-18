package com.mycompany.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @SequenceGenerator(
            name = "orders_id_sequence",
            sequenceName = "orders_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orders_id_sequence"
    )

    private Integer id;
    private Integer customerId;
    private String paymentMethod;
    public Integer products;
    public Integer productsNumber;
    public Boolean isOrderSuccessful;
}


