package com.devicehive.proxy;

import com.devicehive.proxy.payload.Payload;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ProxyMessage {

    private String id;

    @JsonProperty("t")
    private String type;

    @JsonProperty("a")
    private String action;

    @JsonProperty("s")
    private Integer status;

    @JsonProperty("p")
    private Payload payload;

    public ProxyMessage(String id, String type, String action, Integer status, Payload payload) {
        this.id = id;
        this.type = type;
        this.action = action;
        this.status = status;
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    public Integer getStatus() {
        return status;
    }

    public Payload getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "ProxyMessage{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", status=" + status +
                ", payload=" + payload +
                '}';
    }

    public static <T extends Payload> Builder<T> newBuilder() {
        return new Builder<>();
    }

    public static class Builder<T extends Payload> {
        private String id = UUID.randomUUID().toString();
        private String type;
        private String action;
        private Integer status;
        private T payload;

        public Builder<T> withId(String id) {
            this.id = id;
            return this;
        }

        public Builder<T> withType(String type) {
            this.type = type;
            return this;
        }

        public Builder<T> withAction(String action) {
            this.action = action;
            return this;
        }

        public Builder<T> withStatus(Integer status) {
            this.status = status;
            return this;
        }

        public Builder<T> withPayload(T payload) {
            this.payload = payload;
            return this;
        }

        public ProxyMessage build() {
            return new ProxyMessage(id, type, action, status, payload);
        }

    }
}
