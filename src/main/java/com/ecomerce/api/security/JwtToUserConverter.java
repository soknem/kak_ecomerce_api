package com.ecomerce.api.security;

import com.ecomerce.api.domain.User;
import com.ecomerce.api.feature.user.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;


@Getter
@Setter
@RequiredArgsConstructor
@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    private final UserRepository userRepository;

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        // get the user from the database using the email or username
        User user = userRepository.findByEmailOrUsername(source.getSubject(), source.getSubject())
                .orElseThrow(() -> new BadCredentialsException("Invalid Token!!! "));

        // create a custom user details object
        CustomUserDetails customUserDetails = new CustomUserDetails();
        // set the user object to the custom user details object
        customUserDetails.setUser(user);

        return new UsernamePasswordAuthenticationToken(
                customUserDetails,
                ""
                , customUserDetails.getAuthorities()
        );
    }
}
