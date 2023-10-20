package com.ThreeTree.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Entity(name = "Orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_id_sequence",
            sequenceName = "order_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_id_sequence"
    )
    private Long orderId;
    private LocalDate orderDate;
    private BigDecimal orderTotal;


    @ElementCollection
    private Map<Product, Integer> productsQuantities;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Person person;
}
