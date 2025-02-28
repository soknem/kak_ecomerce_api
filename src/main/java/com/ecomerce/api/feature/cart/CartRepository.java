package com.ecomerce.api.feature.cart;

import com.ecomerce.api.domain.Cart;
import com.ecomerce.api.feature.cart.dto.CartResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findByUuid(String uuid);

    Page<Cart> findByUserUuid(Pageable pageable, String userUuid);

}
