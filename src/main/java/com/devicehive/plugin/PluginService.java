package com.devicehive.plugin;

import com.devicehive.core.proxy.DhMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PluginService implements DhMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(PluginService.class);

    public void handleMessage(String message) {
        logger.info("Message received: {}", message);
    }
}
