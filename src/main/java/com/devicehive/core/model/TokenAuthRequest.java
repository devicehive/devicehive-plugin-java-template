package com.devicehive.core.model;

public class TokenAuthRequest {

    private String refreshToken;

    public TokenAuthRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
