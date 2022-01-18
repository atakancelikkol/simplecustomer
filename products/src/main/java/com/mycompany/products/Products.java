package com.mycompany.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@Entity
public class Products {
    public Products(Integer id, String productName, Integer productNumber) {
        this.id = id;
        this.productName = productName;
        this.productNumber = productNumber;
    }
    @Id
    @SequenceGenerator(
            name = "products_id_sequence",
            sequenceName = "products_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "products_id_sequence"
    )

    private Integer id;
    private String productName;
    private Integer productNumber;

}


