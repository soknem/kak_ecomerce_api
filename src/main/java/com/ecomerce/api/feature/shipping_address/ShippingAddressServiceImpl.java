package com.ecomerce.api.feature.shipping_address;

import com.ecomerce.api.domain.ShippingAddress;
import com.ecomerce.api.domain.User;
import com.ecomerce.api.feature.shipping_address.dto.ShippingAddressRequest;
import com.ecomerce.api.feature.shipping_address.dto.ShippingAddressResponse;
import com.ecomerce.api.feature.shipping_address.dto.ShippingAddressUpdateRequest;
import com.ecomerce.api.feature.user.UserRepository;
import com.ecomerce.api.mapper.ShippingAddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;
    private final ShippingAddressMapper shippingAddressMapper;
    private final UserRepository userRepository;

    @Override
    public void createShippingAddress(ShippingAddressRequest shippingAddressRequest) {

        //validate user from dto
        User user =
                userRepository.findByUuid(shippingAddressRequest.userUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user = %s has not been found", shippingAddressRequest.userUuid())));

        //map dto to entity
        ShippingAddress shippingAddress = shippingAddressMapper.fromRequest(shippingAddressRequest);

        //set user to shipping address
        shippingAddress.setUser(user);
        shippingAddress.setUuid(UUID.randomUUID().toString());

        //save to database
        shippingAddressRepository.save(shippingAddress);
    }

    @Override
    public Page<ShippingAddressResponse> getAllShippingAddresses(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<ShippingAddress> shippingAddressPage = shippingAddressRepository.findAll(pageRequest);

        return shippingAddressPage.map(shippingAddressMapper::toShippingAddressResponse);
    }

    @Override
    public ShippingAddressResponse getShippingAddressByUuid(String uuid) {

        ShippingAddress shippingAddress =
                shippingAddressRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("shipping address = %s has not been found", uuid)));

        return shippingAddressMapper.toShippingAddressResponse(shippingAddress);
    }

    @Override
    public ShippingAddressResponse updateShippingAddressByUuid(String uuid, ShippingAddressUpdateRequest shippingAddressUpdateRequest) {

        ShippingAddress shippingAddress =
                shippingAddressRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("shipping address = %s has not been found", uuid)));

        shippingAddressMapper.updateShippingAddressFromRequest(shippingAddress,shippingAddressUpdateRequest);

        shippingAddressRepository.save(shippingAddress);

        return shippingAddressMapper.toShippingAddressResponse(shippingAddress);
    }

    @Override
    public void deleteShippingAddressByUuid(String uuid) {

        ShippingAddress shippingAddress =
                shippingAddressRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("shipping address = %s has not been found", uuid)));

        shippingAddressRepository.delete(shippingAddress);
    }
}
