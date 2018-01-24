package com.devicehive.core.proxy.payload;

public class AuthenticateRequestPayload implements Payload {

    private String token;

    public AuthenticateRequestPayload(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "AuthenticateRequestPayload{" +
                "token='" + token + '\'' +
                '}';
    }
}
