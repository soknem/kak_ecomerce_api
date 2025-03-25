package com.ecomerce.api.security;

import com.ecomerce.api.feature.auth.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;


@Component
public class TokenGenerator {


    private final JwtEncoder jwtAccessTokenEncoder;
    private final JwtEncoder jwtRefreshTokenEncoder;


    // inject the jwtAccessTokenEncoder and jwtRefreshTokenEncoder
    public TokenGenerator(
            JwtEncoder jwtAccessTokenEncoder,
            @Qualifier("jwtRefreshTokenEncoder") JwtEncoder jwtRefreshTokenEncoder
    ) {
        this.jwtRefreshTokenEncoder = jwtRefreshTokenEncoder;
        this.jwtAccessTokenEncoder = jwtAccessTokenEncoder;
    }


    //Access token expires after 3 days
    private String createAccessToken(Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Instant now = Instant.now();

        String authority = userDetails.getRole();

        //  we can also create scope for the token from the userDetails object here !
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.SECONDS))
                .subject(userDetails.getUsername())
                .issuer("restful-api")
                .claim("authorities",authority)
                .build();
        return jwtAccessTokenEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    // expire after 7 days
    private String createRefreshToken(Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Instant now = Instant.now();
        String authority = userDetails.getRole();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.SECONDS))
                .subject(userDetails.getUsername())
                .issuer("restful-api")
                .claim("authorities",authority)
                .build();

        return jwtRefreshTokenEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    }


    // token rotation !
    public AuthResponse generateTokens(Authentication authentication) {

        // check if the principal is an instance of CustomUserDetails
        if (!(authentication.getPrincipal() instanceof CustomUserDetails customUserDetails)) {
            throw new BadCredentialsException("Provided Token is not valid");
        }

// check if the credentials is an instance of Jwt
        String refreshToken;

        if (authentication.getCredentials() instanceof Jwt jwt) {

            Instant now = Instant.now();
            Instant expireAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now, expireAt);

            long daysUtilsExpired = duration.toDays();

            // Duration.between(Instant.now(), jwt.getExpiresAt()).toDays() < 7
            if (daysUtilsExpired < 7) {

                refreshToken = createRefreshToken(authentication);

            } else {

                refreshToken = jwt.getTokenValue();

            }

        } else {

            refreshToken = createRefreshToken(authentication);

        }
        return AuthResponse.builder()

                .refreshToken(refreshToken)
                .accessToken(createAccessToken(authentication))
                .build();

    }


}
