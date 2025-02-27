package com.ecomerce.api.mapper;

import com.ecomerce.api.domain.Role;
import com.ecomerce.api.domain.User;
import com.ecomerce.api.feature.role.dto.RoleUpdateRequest;
import com.ecomerce.api.feature.user.dto.UserRequest;
import com.ecomerce.api.feature.user.dto.UserResponse;
import com.ecomerce.api.feature.user.dto.UserUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromRequest(UserRequest userRequest);

    UserResponse toUserResponse(User user);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserRequest(@MappingTarget User user, UserUpdateRequest userUpdateRequest);


}
