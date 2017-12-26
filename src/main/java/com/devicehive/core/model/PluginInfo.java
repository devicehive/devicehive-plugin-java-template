package com.devicehive.core.model;

public class PluginInfo {

    private String name;
    private String description;

    public PluginInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
