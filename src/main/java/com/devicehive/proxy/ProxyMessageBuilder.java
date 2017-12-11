package com.devicehive.proxy;

import com.devicehive.proxy.payload.AuthenticatePayload;
import com.devicehive.proxy.payload.TopicSubscribePayload;

public class ProxyMessageBuilder {

    public static ProxyMessage authenticate(AuthenticatePayload payload) {
        return ProxyMessage.newBuilder()
                .withType("plugin")
                .withAction("authenticate")
                .withPayload(payload)
                .build();
    }

    public static ProxyMessage subscribe(TopicSubscribePayload payload) {
        return ProxyMessage.newBuilder()
                .withType("topic")
                .withAction("subscribe")
                .withPayload(payload)
                .build();
    }

    public static ProxyMessage unsubscribe() {
        return ProxyMessage.newBuilder()
                .withType("topic")
                .withAction("unsubscribe")
                .build();
    }

    public static ProxyMessage health() {
        return ProxyMessage.newBuilder()
                .withType("health")
                .build();
    }
}
