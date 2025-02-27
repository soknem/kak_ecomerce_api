package com.ecomerce.api.mapper;

import com.ecomerce.api.domain.Product;
import com.ecomerce.api.feature.product.dto.ProductRequest;
import com.ecomerce.api.feature.product.dto.ProductResponse;
import com.ecomerce.api.feature.product.dto.ProductUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product fromRequest(ProductRequest productRequest);

    ProductResponse toProductResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromRequest(@MappingTarget Product product, ProductUpdateRequest productUpdateRequest);
}
