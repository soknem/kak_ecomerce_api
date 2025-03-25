package com.ecomerce.api.domain;

import com.ecomerce.api.config.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "orders")
public class Order extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    private  Double totalAmount;

    private LocalDate orderDate;

    private OrderStatus orderStatus;

    @ManyToOne
    private User user;

}
