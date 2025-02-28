package com.ecomerce.api.mapper;

import com.ecomerce.api.domain.Cart;
import com.ecomerce.api.feature.cart.dto.CartRequest;
import com.ecomerce.api.feature.cart.dto.CartResponse;
import com.ecomerce.api.feature.cart.dto.CartUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CartMapper {

    Cart fromRequest(CartRequest cartRequest);

    CartResponse toResponse(Cart cart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCartFromRequest(@MappingTarget Cart cart, CartUpdateRequest cartUpdateRequest);
}
