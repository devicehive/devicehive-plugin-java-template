package com.devicehive.core.proxy.payload;

import com.google.gson.annotations.SerializedName;

public class MessagePayload implements Payload {

    @SerializedName("m")
    private String message;

    public MessagePayload(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "message='" + message + '\'' +
                '}';
    }
}

