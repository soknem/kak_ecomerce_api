package com.ecomerce.api.domain;

import com.ecomerce.api.config.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "carts")
public class Cart extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    private LocalDateTime addedAt;

    private Integer qty;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

}
