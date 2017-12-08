package com.devicehive.model;

public class PluginRegistrationRequest {

    private String deviceIds;
    private String networkIds;
    private String names;
    private String timestamp;
    private boolean returnCommands;
    private boolean returnUpdatedCommands;
    private boolean returnNotifications;

    public PluginRegistrationRequest(String deviceIds, String networkIds, String names, String timestamp,
                                     boolean returnCommands, boolean returnUpdatedCommands, boolean returnNotifications) {
        this.deviceIds = deviceIds;
        this.networkIds = networkIds;
        this.names = names;
        this.timestamp = timestamp;
        this.returnCommands = returnCommands;
        this.returnUpdatedCommands = returnUpdatedCommands;
        this.returnNotifications = returnNotifications;
    }

    public String getDeviceIds() {
        return deviceIds;
    }

    public String getNetworkIds() {
        return networkIds;
    }

    public String getNames() {
        return names;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isReturnCommands() {
        return returnCommands;
    }

    public boolean isReturnUpdatedCommands() {
        return returnUpdatedCommands;
    }

    public boolean isReturnNotifications() {
        return returnNotifications;
    }
}
