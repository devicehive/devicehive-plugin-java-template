package com.devicehive.proxy.payload;

public class HealthPayload implements Payload {

    private String status;

    public HealthPayload(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HealthPayload{" +
                "status='" + status + '\'' +
                '}';
    }
}
