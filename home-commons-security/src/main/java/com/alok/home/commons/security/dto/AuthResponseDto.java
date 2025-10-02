package com.alok.home.commons.security.dto;


public record AuthResponseDto(
        String accessToken,
        String tokenType,
        String scope,
        String expiresIn,
        String issuer,
        String refreshToken
) {
}
