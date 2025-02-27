package com.ecomerce.api.mapper;

import com.ecomerce.api.domain.ShippingAddress;
import com.ecomerce.api.feature.shipping_address.dto.ShippingAddressRequest;
import com.ecomerce.api.feature.shipping_address.dto.ShippingAddressResponse;
import com.ecomerce.api.feature.shipping_address.dto.ShippingAddressUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")
public interface ShippingAddressMapper {

    ShippingAddress fromRequest(ShippingAddressRequest shippingAddressRequest);

    ShippingAddressResponse toShippingAddressResponse(ShippingAddress shippingAddress);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateShippingAddressFromRequest(@MappingTarget ShippingAddress shippingAddress,
                                          ShippingAddressUpdateRequest shippingAddressUpdateRequest);

}
