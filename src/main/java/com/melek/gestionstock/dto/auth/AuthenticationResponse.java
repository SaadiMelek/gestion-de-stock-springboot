package com.melek.gestionstock.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class AuthenticationResponse {
    private String accessToken;
}
