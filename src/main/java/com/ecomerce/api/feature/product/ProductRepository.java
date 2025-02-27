package com.ecomerce.api.feature.product;

import com.ecomerce.api.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByUuid(String uuid);

    Page<Product> findAllByCategoryAlias(Pageable pageable, String categoryAlias);
}
