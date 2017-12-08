package com.devicehive.model;

public class PluginInfo {

    private String name;
    private String description;
    private String healthCheckUrl;

    public PluginInfo(String name, String description, String healthCheckUrl) {
        this.name = name;
        this.description = description;
        this.healthCheckUrl = healthCheckUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHealthCheckUrl() {
        return healthCheckUrl;
    }
}
