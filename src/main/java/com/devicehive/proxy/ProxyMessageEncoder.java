package com.devicehive.proxy;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ProxyMessageEncoder implements Encoder.Text<ProxyMessage> {

    private static Gson gson = new Gson();

    @Override
    public String encode(ProxyMessage message) throws EncodeException {
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
