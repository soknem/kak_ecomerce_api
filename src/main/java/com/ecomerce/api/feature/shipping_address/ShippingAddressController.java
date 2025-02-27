package com.ecomerce.api.feature.shipping_address;

import com.ecomerce.api.feature.shipping_address.dto.ShippingAddressRequest;
import com.ecomerce.api.feature.shipping_address.dto.ShippingAddressResponse;
import com.ecomerce.api.feature.shipping_address.dto.ShippingAddressUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipping-addresses")
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createShippingAddress(@Valid @RequestBody ShippingAddressRequest shippingAddressRequest) {

        shippingAddressService.createShippingAddress(shippingAddressRequest);
    }

    @GetMapping
    public Page<ShippingAddressResponse> getAllShippingAddresses(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return shippingAddressService.getAllShippingAddresses(pageNumber, pageSize);
    }

    @GetMapping("/{uuid}")
    public ShippingAddressResponse getShippingAddressByUuid(@PathVariable String uuid) {
        return shippingAddressService.getShippingAddressByUuid(uuid);
    }

    @PatchMapping("/{uuid}")
    public ShippingAddressResponse updateShippingAddressByUuid(@PathVariable String uuid,
                                                               @RequestBody ShippingAddressUpdateRequest shippingAddressUpdateRequest) {
        return shippingAddressService.updateShippingAddressByUuid(uuid, shippingAddressUpdateRequest);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShippingAddressByUuid(@PathVariable String uuid){

        shippingAddressService.deleteShippingAddressByUuid(uuid);
    }
}
