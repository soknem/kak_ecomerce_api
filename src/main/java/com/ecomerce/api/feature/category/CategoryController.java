package com.ecomerce.api.feature.category;

import com.ecomerce.api.feature.category.dto.CategoryRequest;
import com.ecomerce.api.feature.category.dto.CategoryResponse;
import com.ecomerce.api.feature.category.dto.CategoryUpdateRequest;
import com.ecomerce.api.feature.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        categoryService.createCategory(categoryRequest);
    }

    @GetMapping
    public Page<CategoryResponse> getAllCategories(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {
        return categoryService.getAllCategories(pageNumber, pageSize);
    }

    @GetMapping("/{alias}")
    public CategoryResponse getCategoryByAlias(@PathVariable String alias) {

        return categoryService.getCategoryByAlias(alias);
    }

    @PatchMapping("/{alias}")
    public CategoryResponse updateCategoryByAlias(@PathVariable String alias,
                                                  @Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {

        return categoryService.updateCategoryByAlias(alias, categoryUpdateRequest);
    }

    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryByAlias(@PathVariable String alias) {

        categoryService.deleteCategoryByAlias(alias);
    }

    @GetMapping("/{alias}/product")
    public Page<ProductResponse> getAllProductByCategory(
            @PathVariable String alias,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ){
        return categoryService.getAllProductsByCategory(alias,pageNumber,pageSize);
    }
}
