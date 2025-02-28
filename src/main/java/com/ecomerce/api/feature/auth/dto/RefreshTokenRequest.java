package com.ecomerce.api.feature.auth.dto;

import lombok.Builder;

@Builder
public record RefreshTokenRequest(
        String refreshToken
) {
}
