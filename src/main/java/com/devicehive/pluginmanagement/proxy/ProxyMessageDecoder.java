package com.devicehive.pluginmanagement.proxy;

import com.devicehive.pluginmanagement.proxy.payload.HealthPayload;
import com.devicehive.pluginmanagement.proxy.payload.NotificationPayload;
import com.google.gson.*;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProxyMessageDecoder implements Decoder.Text<List<ProxyMessage>> {

    private static final JsonParser parser = new JsonParser();
    private static final Gson gson = new Gson();

    @Override
    public List<ProxyMessage> decode(String s) {
        JsonElement object = parser.parse(s);
        if (object instanceof JsonArray) {
            List<ProxyMessage> list = new ArrayList<>();
            object.getAsJsonArray().forEach(elem -> list.add(buildMessage(elem.getAsJsonObject())));
            return list;
        }
        if (object instanceof JsonObject) {
            return Collections.singletonList(buildMessage(object.getAsJsonObject()));
        }
        throw new JsonParseException(String.format("Cannot deserialize ProxyMessage from '%s'", s));
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }

    @SuppressWarnings("unchecked")
    private ProxyMessage buildMessage(JsonObject object) {
        JsonElement t = object.get("t");
        JsonElement a = object.get("a");
        if (t == null) {
            throw new JsonParseException("Cannot deserialize ProxyMessage because it does not define a field named 't'");
        }
        String type = object.get("t").getAsString();
        if (a != null) {
            type += "/" + a.getAsString();
        }

        ProxyMessage.Builder decoded = ProxyMessage.newBuilder()
                .withId(object.get("id") != null ? object.get("id").getAsString() : null)
                .withType(t.getAsString())
                .withAction(a != null ? a.getAsString() : null)
                .withStatus(object.get("s") != null ? object.get("s").getAsInt() : null);

        if (object.get("p") != null) {
            switch (type) {
                case "notif":
                    decoded.withPayload(new NotificationPayload(gson.fromJson(object.get("p"), String.class)));
                    break;
                case "health":
                    decoded.withPayload(gson.fromJson(object.get("p"), HealthPayload.class));
                    break;
            }
        }
        return decoded.build();
    }
}

