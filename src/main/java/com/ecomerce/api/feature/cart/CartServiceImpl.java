package com.ecomerce.api.feature.cart;

import com.ecomerce.api.domain.Cart;
import com.ecomerce.api.domain.Product;
import com.ecomerce.api.domain.User;
import com.ecomerce.api.feature.cart.dto.CartRequest;
import com.ecomerce.api.feature.cart.dto.CartResponse;
import com.ecomerce.api.feature.cart.dto.CartUpdateRequest;
import com.ecomerce.api.feature.product.ProductRepository;
import com.ecomerce.api.feature.user.UserRepository;
import com.ecomerce.api.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void createCart(CartRequest cartRequest) {

        //find user in database
        User user =
                userRepository.findByUuid(cartRequest.userUuid()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("user = %s has not been found",cartRequest.userUuid())));

        //find product in database
        Product product =
                productRepository.findByUuid(cartRequest.productUuid()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("product = %s has not been found",cartRequest.productUuid())));

        Cart cart = cartMapper.fromRequest(cartRequest);

        cart.setUuid(UUID.randomUUID().toString());
        cart.setUser(user);
        cart.setProduct(product);
        cart.setAddedAt(LocalDateTime.now());

        cartRepository.save(cart);
    }

    @Override
    public Page<CartResponse> getAllCarts(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);

        Page<Cart> cartPage = cartRepository.findAll(pageRequest);

        return cartPage.map(cartMapper::toResponse);
    }

    @Override
    public CartResponse getCartByUuid(String uuid) {

        Cart cart = cartRepository.findByUuid(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND
                ,String.format("cart = %s has not been found",uuid)));

        return cartMapper.toResponse(cart);
    }

    @Override
    public CartResponse updateCartByUuid(String uuid, CartUpdateRequest cartUpdateRequest) {

        Cart cart = cartRepository.findByUuid(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND
                ,String.format("cart = %s has not been found",uuid)));

        cartMapper.updateCartFromRequest(cart,cartUpdateRequest);

        cartRepository.save(cart);

        return cartMapper.toResponse(cart);
    }

    @Override
    public void deleteCartByUuid(String uuid) {

        Cart cart = cartRepository.findByUuid(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND
                ,String.format("cart = %s has not been found",uuid)));

        cartRepository.delete(cart);
    }
}
