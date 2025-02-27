package com.ecomerce.api.feature.category;

import com.ecomerce.api.feature.category.dto.CategoryRequest;
import com.ecomerce.api.feature.category.dto.CategoryResponse;
import com.ecomerce.api.feature.category.dto.CategoryUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * service that handle categories management
 *
 * @author pov soknem
 * @since 2025
 */
public interface CategoryService {

    /**
     * create category
     *
     * @param categoryRequest is the dto that contain all information to create category
     * @author pov soknem
     * @since 2025
     */
    void createCategory(CategoryRequest categoryRequest);

    /**
     * get category by alias
     *
     * @param alias is the alias of category to get
     * @return {@link CategoryResponse}
     * @author pov soknem
     * @since 2025
     */
    CategoryResponse getCategoryByAlias(String alias);

    /**
     * get all users with pagination
     *
     * @param pageNumber is the current page number to get
     * @param pageSize   is the size of page to get
     * @return {@link Page<CategoryResponse>}
     * @author pov soknem
     * @since 2025
     */
    Page<CategoryResponse> getAllCategories(int pageNumber, int pageSize);

    /**
     * update category by alias
     *
     * @param alias                 is the alias pf category to update
     * @param categoryUpdateRequest is the dto that contain all information of new data to update
     * @return {@link CategoryResponse}
     * @author pov soknem
     * @since 2025
     */

    CategoryResponse updateCategoryByAlias(String alias, CategoryUpdateRequest categoryUpdateRequest);

    /**
     * delete category by alias
     *
     * @param alias is the alias of category to update
     * @author pov soknem
     * @since 2025
     */
    void deleteCategoryByAlias(String alias);
}
