package com.devicehive.configuration;

import com.devicehive.service.PluginRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class PluginConfiguration {

    private final PluginRegistrationService pluginRegistrationService;

    @Autowired
    public PluginConfiguration(PluginRegistrationService pluginRegistrationService) {
        this.pluginRegistrationService = pluginRegistrationService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initPlugin() {
        pluginRegistrationService.initPlugin();
    }
}
