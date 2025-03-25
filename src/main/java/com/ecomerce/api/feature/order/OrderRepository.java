package com.ecomerce.api.feature.order;

import com.ecomerce.api.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findByUuid(String uuid);

}
