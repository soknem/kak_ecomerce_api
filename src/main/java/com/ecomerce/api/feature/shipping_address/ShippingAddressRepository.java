package com.ecomerce.api.feature.shipping_address;

import com.ecomerce.api.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress,Long> {

   Optional<ShippingAddress> findByUuid(String uuid);
}
