package com.ecomerce.api.mapper;

import com.ecomerce.api.domain.Category;
import com.ecomerce.api.feature.category.dto.CategoryRequest;
import com.ecomerce.api.feature.category.dto.CategoryResponse;
import com.ecomerce.api.feature.category.dto.CategoryUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category fromRequest(CategoryRequest categoryRequest);

    CategoryResponse toCategoryResponse(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromRequest(@MappingTarget Category category, CategoryUpdateRequest categoryUpdateRequest);
}
