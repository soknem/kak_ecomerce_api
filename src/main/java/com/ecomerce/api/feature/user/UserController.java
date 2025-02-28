package com.ecomerce.api.feature.user;

import com.ecomerce.api.feature.cart.dto.CartResponse;
import com.ecomerce.api.feature.user.dto.UserRequest;
import com.ecomerce.api.feature.user.dto.UserResponse;
import com.ecomerce.api.feature.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody UserRequest userRequest) {

        userService.createUser(userRequest);
    }

    @GetMapping("/{uuid}")
    public UserResponse getUserByUuid(@PathVariable String uuid) {

        return userService.getUserByUuid(uuid);
    }

    @GetMapping
    public Page<UserResponse> getAllUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {
        return userService.getAllUsers(pageNumber, pageSize);
    }

    @PatchMapping("/{uuid}")
    public UserResponse udpateUserByUuid(@PathVariable String uuid,
                                         @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUserByUuid(uuid, userUpdateRequest);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByUuid(@PathVariable String uuid) {

        userService.deleteUserByUuid(uuid);
    }

    @GetMapping("/{uuid}/carts")
    public Page<CartResponse> getAllCartsByUserUuid(
            @PathVariable String uuid,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ){

        return userService.getCartByUserUuid(uuid,pageNumber,pageSize);
    }
}
