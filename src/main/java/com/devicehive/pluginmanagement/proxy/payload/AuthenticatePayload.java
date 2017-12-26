package com.devicehive.pluginmanagement.proxy.payload;

public class AuthenticatePayload implements Payload {

    private String token;

    public AuthenticatePayload(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "AuthenticatePayload{" +
                "token='" + token + '\'' +
                '}';
    }
}
