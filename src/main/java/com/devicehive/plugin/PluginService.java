package com.devicehive.plugin;

import com.devicehive.proxy.DhMessageHandler;
import org.springframework.stereotype.Service;

@Service
public class PluginService implements DhMessageHandler {

    public void handleMessage(String message) {
        System.out.println("Message received " + message);
    }
}
