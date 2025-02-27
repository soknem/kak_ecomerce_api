package com.ecomerce.api.feature.shipping_address;

import com.ecomerce.api.feature.shipping_address.dto.ShippingAddressRequest;
import com.ecomerce.api.feature.shipping_address.dto.ShippingAddressResponse;
import com.ecomerce.api.feature.shipping_address.dto.ShippingAddressUpdateRequest;
import org.springframework.data.domain.Page;

public interface ShippingAddressService {

    void createShippingAddress(ShippingAddressRequest shippingAddressRequest);

    Page<ShippingAddressResponse> getAllShippingAddresses(int pageNumber, int pageSize);

    ShippingAddressResponse getShippingAddressByUuid(String uuid);

    ShippingAddressResponse updateShippingAddressByUuid(String uuid,
                                                        ShippingAddressUpdateRequest shippingAddressUpdateRequest);

    void deleteShippingAddressByUuid(String uuid);
}
