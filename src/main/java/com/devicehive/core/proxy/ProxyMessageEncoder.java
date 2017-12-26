package com.devicehive.core.proxy;

import com.google.gson.Gson;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ProxyMessageEncoder implements Encoder.Text<ProxyMessage> {

    private static Gson gson = new Gson();

    @Override
    public String encode(ProxyMessage message) {
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
