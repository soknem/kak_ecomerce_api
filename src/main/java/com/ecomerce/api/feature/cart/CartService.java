package com.ecomerce.api.feature.cart;

import com.ecomerce.api.feature.cart.dto.CartRequest;
import com.ecomerce.api.feature.cart.dto.CartResponse;
import com.ecomerce.api.feature.cart.dto.CartUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * service that handle cart management
 *
 * @author pov soknem
 * @since 2025
 */
public interface CartService {

    /**
     * create new cart
     *
     * @param cartRequest is the dto for create new cart
     * @author pov soknem
     * @since 2025
     */
    void createCart(CartRequest cartRequest);

    Page<CartResponse> getAllCarts(int pageNumber, int pageSize);

    CartResponse getCartByUuid(String uuid);

    CartResponse updateCartByUuid(String uuid, CartUpdateRequest cartUpdateRequest);

    void deleteCartByUuid(String uuid);

}
