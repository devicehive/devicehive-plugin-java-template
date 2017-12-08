package com.devicehive.model;

public class PluginRegistrationResponse {

    private String accessToken;
    private String refreshToken;
    private String proxyEndpoint;

    public PluginRegistrationResponse() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getProxyEndpoint() {
        return proxyEndpoint;
    }

    public void setProxyEndpoint(String proxyEndpoint) {
        this.proxyEndpoint = proxyEndpoint;
    }
}
