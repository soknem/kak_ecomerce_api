package com.ecomerce.api.feature.user;

import com.ecomerce.api.domain.Cart;
import com.ecomerce.api.domain.Role;
import com.ecomerce.api.domain.User;
import com.ecomerce.api.feature.cart.CartRepository;
import com.ecomerce.api.feature.cart.dto.CartResponse;
import com.ecomerce.api.feature.role.RoleRepository;
import com.ecomerce.api.feature.user.dto.UserRequest;
import com.ecomerce.api.feature.user.dto.UserResponse;
import com.ecomerce.api.feature.user.dto.UserUpdateRequest;
import com.ecomerce.api.mapper.CartMapper;
import com.ecomerce.api.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public void createUser(UserRequest userRequest) {

        //map user from dto
        User user = userMapper.fromRequest(userRequest);
        user.setUuid(UUID.randomUUID().toString());

        //validate role by role name in database
        Role role = roleRepository.findByRoleName(userRequest.roleName()).orElse(null);

        if (role == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("role = %s has not been found",
                    userRequest.roleName()));
        }

        //set role to  user
        user.setRole(role);

        //save to database
        userRepository.save(user);

    }

    @Override
    public Page<UserResponse> getAllUsers(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<User> userPage = userRepository.findAll(pageRequest);

        return userPage.map(userMapper::toUserResponse);
    }

    @Override
    public UserResponse getUserByUuid(String uuid) {

        User user =
                userRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("user = %s has not been found", uuid)));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUserByUuid(String uuid, UserUpdateRequest userUpdateRequest) {

        User user =
                userRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("user = %s has not been found", uuid)));

        userMapper.updateUserRequest(user, userUpdateRequest);

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUserByUuid(String uuid) {

        User user =
                userRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("user = %s has not been found", uuid)));

        userRepository.delete(user);

    }

    @Override
    public Page<CartResponse> getCartByUserUuid(String userUuid, int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);

        Page<Cart> cartPage = cartRepository.findByUserUuid(pageRequest,userUuid);

        return cartPage.map(cartMapper::toResponse);
    }

    @Override
    public String generateStrongPassword(int length) {


        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters.");
        }

        SecureRandom random = new SecureRandom();
        List<Character> passwordChars = new ArrayList<>();

        // Add at least one character of each required type
        passwordChars.add((char) (random.nextInt(26) + 'A')); // Uppercase letter
        passwordChars.add((char) (random.nextInt(26) + 'a')); // Lowercase letter
        passwordChars.add((char) (random.nextInt(10) + '0')); // Digit

        // Expanded set of special characters
        String specialChars = "@$!%*?&^#()-_=+[]{}|;:'\",.<>~";
        passwordChars.add(specialChars.charAt(random.nextInt(specialChars.length()))); // Special character

        // Fill the rest of the password length with random characters from the allowed set
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789" + specialChars;
        for (int i = 4; i < length; i++) {
            passwordChars.add(allowedChars.charAt(random.nextInt(allowedChars.length())));
        }

        // Shuffle the characters to ensure randomness
        Collections.shuffle(passwordChars, random);

        // Convert the list of characters to a string
        return passwordChars.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
