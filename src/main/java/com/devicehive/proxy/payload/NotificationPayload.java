package com.devicehive.proxy.payload;

import com.devicehive.proxy.payload.Payload;

public class NotificationPayload implements Payload {

    private String value;

    public NotificationPayload(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NotificationPayload{" +
                "value='" + value + '\'' +
                '}';
    }
}
