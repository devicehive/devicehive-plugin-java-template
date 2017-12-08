package com.devicehive.model;

public class PluginHealth {

    private PluginInfo pluginInfo;
    private boolean isOnline;

    public PluginHealth(PluginInfo pluginInfo, boolean isOnline) {
        this.pluginInfo = pluginInfo;
        this.isOnline = isOnline;
    }

    public PluginInfo getPluginInfo() {
        return pluginInfo;
    }

    public void setPluginInfo(PluginInfo pluginInfo) {
        this.pluginInfo = pluginInfo;
    }

    public boolean getOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
