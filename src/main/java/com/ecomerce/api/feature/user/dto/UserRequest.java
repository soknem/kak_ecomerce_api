package com.ecomerce.api.feature.user.dto;

import com.ecomerce.api.domain.Role;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest (

        @NotBlank(message = "username is require")
         String username,

        @NotBlank(message = "password is require")
         String password,

        @NotBlank(message = "email is require")
         String email,

        @NotBlank(message = "phone is require")
         String phone,

         String avatar,

         String address,

        @NotBlank(message = "roleName is require")
        String roleName
){
}
