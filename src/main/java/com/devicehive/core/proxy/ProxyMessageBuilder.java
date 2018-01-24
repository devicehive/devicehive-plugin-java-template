package com.devicehive.core.proxy;

import com.devicehive.core.proxy.payload.AuthenticateRequestPayload;
import com.devicehive.core.proxy.payload.SubscribePayload;

public class ProxyMessageBuilder {

    public static ProxyMessage authenticate(AuthenticateRequestPayload payload) {
        return ProxyMessage.newBuilder()
                .withType("plugin")
                .withAction("authenticate")
                .withPayload(payload)
                .build();
    }

    public static ProxyMessage subscribe(SubscribePayload payload) {
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
