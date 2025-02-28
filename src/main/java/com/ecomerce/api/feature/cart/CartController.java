package com.ecomerce.api.feature.cart;

import com.ecomerce.api.feature.cart.dto.CartRequest;
import com.ecomerce.api.feature.cart.dto.CartResponse;
import com.ecomerce.api.feature.cart.dto.CartUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCart(@Valid @RequestBody CartRequest cartRequest){

        cartService.createCart(cartRequest);
    }

    @GetMapping
    public Page<CartResponse> getAllCarts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ){

        return cartService.getAllCarts(pageNumber,pageSize);
    }

    @GetMapping("/{uuid}")
    public CartResponse getCartByUuid(@PathVariable String uuid){

        return cartService.getCartByUuid(uuid);
    }

    @PatchMapping("/{uuid}")
    public CartResponse updateCartByUuid(@PathVariable String uuid,
                                         @Valid @RequestBody CartUpdateRequest cartUpdateRequest){

        return cartService.updateCartByUuid(uuid,cartUpdateRequest);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUuid(@PathVariable String uuid){

        cartService.deleteCartByUuid(uuid);
    }
}
