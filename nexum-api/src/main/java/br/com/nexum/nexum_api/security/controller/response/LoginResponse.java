package br.com.nexum.nexum_api.security.controller.response;

public record LoginResponse(String accessToken, Long expiresIn) {
}
