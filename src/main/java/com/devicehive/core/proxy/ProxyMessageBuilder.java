package com.devicehive.core.proxy;

import com.devicehive.core.proxy.payload.TopicsPayload;

public class ProxyMessageBuilder {

    public static ProxyMessage subscribe(TopicsPayload payload) {
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
