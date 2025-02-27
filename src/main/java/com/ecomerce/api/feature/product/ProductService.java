package com.ecomerce.api.feature.product;

import com.ecomerce.api.feature.product.dto.ProductRequest;
import com.ecomerce.api.feature.product.dto.ProductResponse;
import com.ecomerce.api.feature.product.dto.ProductUpdateRequest;
import org.springframework.data.domain.Page;

public interface ProductService {

    void createProduct(ProductRequest productRequest);

    ProductResponse getProductByUuid(String uuid);

    Page<ProductResponse> getAllProducts(int pageNumber, int pageSize);

    ProductResponse updateProductByUui(String uuid, ProductUpdateRequest productUpdateRequest);

    void deleteProductByUuid(String uuid);
}
