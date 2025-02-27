package com.ecomerce.api.feature.category;

import com.ecomerce.api.domain.Category;
import com.ecomerce.api.domain.Product;
import com.ecomerce.api.feature.category.dto.CategoryRequest;
import com.ecomerce.api.feature.category.dto.CategoryResponse;
import com.ecomerce.api.feature.category.dto.CategoryUpdateRequest;
import com.ecomerce.api.feature.product.ProductRepository;
import com.ecomerce.api.feature.product.dto.ProductResponse;
import com.ecomerce.api.mapper.CategoryMapper;
import com.ecomerce.api.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public void createCategory(CategoryRequest categoryRequest) {

        if (categoryRepository.existsByAlias(categoryRequest.alias())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("category = %s already existed",
                    categoryRequest.alias()));
        }

        Category category = categoryMapper.fromRequest(categoryRequest);

        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse getCategoryByAlias(String alias) {

        Category category =
                categoryRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("category = %s has not been found", alias)));

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> getAllCategories(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<Category> categoryPage = categoryRepository.findAll(pageRequest);

        return categoryPage.map(categoryMapper::toCategoryResponse);
    }

    @Override
    public CategoryResponse updateCategoryByAlias(String alias, CategoryUpdateRequest categoryUpdateRequest) {

        Category category =
                categoryRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("category = %s has not been found", alias)));

        categoryMapper.updateCategoryFromRequest(category, categoryUpdateRequest);

        if (!Objects.equals(alias, categoryUpdateRequest.alias()) && categoryRepository.existsByAlias(categoryUpdateRequest.alias())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("category = %s already existed",
                    categoryUpdateRequest.alias()));
        }
        categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public void deleteCategoryByAlias(String alias) {

        Category category =
                categoryRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("category = %s has not been found", alias)));

        categoryRepository.delete(category);
    }

    @Override
    public Page<ProductResponse> getAllProductsByCategory(String categoryAlias,int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);

        Page<Product> productPage= productRepository.findAllByCategoryAlias(categoryAlias);

        return productPage.map(productMapper::toProductResponse);
    }
}
