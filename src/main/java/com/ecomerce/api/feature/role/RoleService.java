package com.ecomerce.api.feature.role;

import com.ecomerce.api.feature.role.dto.RoleRequest;
import com.ecomerce.api.feature.role.dto.RoleResponse;
import com.ecomerce.api.feature.role.dto.RoleUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * service for handle roles management
 *
 * @author pov soknem
 * @since 2025
 */
public interface RoleService {

    /**
     * create  new role
     *
     * @param roleRequest is the dto contain all information to create new role
     * @author pov soknem
     * @since 2025
     */
    void createRole(RoleRequest roleRequest);

    /**
     * get role by roleName
     *
     * @param roleName is the role name to get role
     * @return {@link RoleResponse}
     * @author pov soknem
     * @since 2025
     */
    RoleResponse getRoleRoleName(String roleName);

    /**
     * get all roles as pagination
     *
     * @param pageNumber is the number of current page to get
     * @param pageSize   is the size of page to get
     * @return {@link Page<RoleResponse>}
     * @author pov soknem
     * @since 2025
     */
    Page<RoleResponse> getAllRoles(int pageNumber, int pageSize);

    /**
     * update role with new data by role name
     *
     * @param roleName          is the role name to update
     * @param roleUpdateRequest is the dto contain new data to update
     * @return {@link RoleResponse}
     * @author pov soknem
     * @since 2025
     */
    RoleResponse updateRoleByRoleName(String roleName, RoleUpdateRequest roleUpdateRequest);

    /**
     * delete role by name
     *
     * @param roleName is the role name to delete
     * @author pov soknem
     * @since 2025
     */
    void deleteRoleByRoleName(String roleName);
}
