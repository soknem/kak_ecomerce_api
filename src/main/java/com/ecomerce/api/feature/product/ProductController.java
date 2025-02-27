package com.ecomerce.api.feature.product;

import com.ecomerce.api.feature.product.dto.ProductRequest;
import com.ecomerce.api.feature.product.dto.ProductResponse;
import com.ecomerce.api.feature.product.dto.ProductUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody ProductRequest productRequest) {

        productService.createProduct(productRequest);
    }

    @GetMapping
    public Page<ProductResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return productService.getAllProducts(pageNumber, pageSize);
    }

    @GetMapping("/{uuid}")
    public ProductResponse getProductByUuid(@PathVariable String uuid) {

        return productService.getProductByUuid(uuid);
    }

    @PatchMapping("/{uuid}")
    public ProductResponse updateProductByUuid(@PathVariable String uuid,
                                               @Valid @RequestBody ProductUpdateRequest productUpdateRequest) {

        return productService.updateProductByUui(uuid, productUpdateRequest);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductByUuid(@PathVariable String uuid) {

        productService.deleteProductByUuid(uuid);
    }

}
