package com.ecomerce.api.feature.product;

import com.ecomerce.api.domain.Category;
import com.ecomerce.api.domain.Product;
import com.ecomerce.api.feature.category.CategoryRepository;
import com.ecomerce.api.feature.product.dto.ProductRequest;
import com.ecomerce.api.feature.product.dto.ProductResponse;
import com.ecomerce.api.feature.product.dto.ProductUpdateRequest;
import com.ecomerce.api.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CategoryRepository categoryRepository;

    @Override
    public void createProduct(ProductRequest productRequest) {

        Category category =
                categoryRepository.findByAlias(productRequest.categoryAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("category = %s has not been found", productRequest.categoryAlias())));

        Product product = productMapper.fromRequest(productRequest);

        product.setUuid(UUID.randomUUID().toString());

        product.setCategory(category);

        productRepository.save(product);
    }

    @Override
    public ProductResponse getProductByUuid(String uuid) {

        Product product =
                productRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("product = %s has not been found", uuid)));
        return productMapper.toProductResponse(product);
    }

    @Override
    public Page<ProductResponse> getAllProducts(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<Product> productPage = productRepository.findAll(pageRequest);

        return productPage.map(productMapper::toProductResponse);
    }

    @Override
    public ProductResponse updateProductByUui(String uuid, ProductUpdateRequest productUpdateRequest) {

        Product product =
                productRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("product = %s has not been found", uuid)));

        productMapper.updateProductFromRequest(product, productUpdateRequest);

        productRepository.save(product);

        return productMapper.toProductResponse(product);
    }

    @Override
    public void deleteProductByUuid(String uuid) {

        Product product =
                productRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("product = %s has not been found", uuid)));

        productRepository.delete(product);
    }
}
