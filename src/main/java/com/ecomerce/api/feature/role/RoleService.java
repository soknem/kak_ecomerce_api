package com.ecomerce.api.feature.role;

import com.ecomerce.api.feature.role.dto.RoleRequest;
import com.ecomerce.api.feature.role.dto.RoleResponse;
import com.ecomerce.api.feature.role.dto.RoleUpdateRequest;
import org.springframework.data.domain.Page;

public interface RoleService {

    void createRole(RoleRequest roleRequest);

    RoleResponse getRoleRoleName(String roleName);

    Page<RoleResponse> getAllRoles(int pageNumber,int pageSize);

    RoleResponse updateRoleByRoleName(String roleName, RoleUpdateRequest roleUpdateRequest);

    void deleteRoleByRoleName(String roleName);
}
