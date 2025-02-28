package com.ecomerce.api.feature.auth;

import com.ecomerce.api.feature.auth.dto.*;
import com.ecomerce.api.feature.user.UserService;
import com.ecomerce.api.feature.user.dto.UserRequest;
import com.ecomerce.api.feature.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthServiceImpl authService;
    private final UserService userService;

    @PostMapping("/login")
//    @CrossOrigin(origins = "*")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }

    @PutMapping("/passwords")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword (@Valid @RequestBody AuthRequestResetPassword authRequestResetPassword) {
        authService.changePassword(authRequestResetPassword);
    }


    @PutMapping("/passwords/reset")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public Map<String,String> resetPassword(@Valid @RequestBody RequestPasswordByUsernameOrEmail authRequestResetPassword) {
          return authService.resetPassword(authRequestResetPassword);
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
    }
}
