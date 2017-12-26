package com.devicehive.core.controller;

import com.devicehive.core.model.PluginHealth;
import com.devicehive.core.model.PluginInfo;
import com.devicehive.core.service.PluginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final PluginInfoService pluginInfoService;
    private final PluginInfo pluginInfo;

    @Autowired
    public HealthController(PluginInfoService pluginInfoService) {
        this.pluginInfoService = pluginInfoService;
        this.pluginInfo = this.pluginInfoService.getPluginInfo();
    }

    @GetMapping(value = "${plugin.health-check-endpoint:health}")
    public PluginHealth getPluginHealth() {
        return new PluginHealth(pluginInfo, true);
    }
}
