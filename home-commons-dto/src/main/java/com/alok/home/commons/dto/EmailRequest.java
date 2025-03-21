package com.alok.home.commons.dto;

public record EmailRequest(
        String to,
        String subject,
        String body
) {
}
