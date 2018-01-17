package com.devicehive.core.model;

public class PluginRegistration {

    private String accessToken;
    private String refreshToken;
    private String proxyEndpoint;
    private String topicName;

    public PluginRegistration(String accessToken, String refreshToken, String proxyEndpoint, String topicName) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.proxyEndpoint = proxyEndpoint;
        this.topicName = topicName;
    }

    public PluginRegistration() {
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

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
