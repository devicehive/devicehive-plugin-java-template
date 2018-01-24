package com.devicehive.core.proxy.payload;

import com.google.gson.annotations.SerializedName;

public class AuthenticateResponsePayload implements Payload {

    @SerializedName("e")
    private String expirationDate;

    @SerializedName("t")
    private String type;

    @SerializedName("tpc")
    private String topicName;

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public String toString() {
        return "AuthenticateResponsePayload{" +
                "expirationDate='" + expirationDate + '\'' +
                ", type='" + type + '\'' +
                ", topic='" + topicName + '\'' +
                '}';
    }
}
