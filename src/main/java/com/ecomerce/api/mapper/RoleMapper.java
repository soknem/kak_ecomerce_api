package com.ecomerce.api.mapper;

import com.ecomerce.api.domain.Role;
import com.ecomerce.api.feature.role.dto.RoleRequest;
import com.ecomerce.api.feature.role.dto.RoleResponse;
import com.ecomerce.api.feature.role.dto.RoleUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role fromRequest(RoleRequest roleRequest);

    RoleResponse toResponse(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoleRequest(@MappingTarget Role role, RoleUpdateRequest roleUpdateRequest);


}
