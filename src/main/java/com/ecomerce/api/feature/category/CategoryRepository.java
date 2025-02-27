package com.ecomerce.api.feature.category;

import com.ecomerce.api.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByAlias(String alias);

    Optional<Category> findByAlias(String alias);
}
