package com.devicehive.pluginmanagement.service;

import com.devicehive.pluginmanagement.model.*;
import com.devicehive.plugin.PluginService;
import com.devicehive.pluginmanagement.proxy.ProxyMessageBuilder;
import com.devicehive.pluginmanagement.proxy.payload.TopicSubscribePayload;
import com.devicehive.pluginmanagement.proxy.WebSocketKafkaProxyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PluginRegistrationService {

    private final PluginInfoService pluginInfoService;
    private final Environment environment;
    private final PluginService pluginService;

    private WebSocketKafkaProxyClient client = new WebSocketKafkaProxyClient();
    private JwtToken userToken;

    @Autowired
    public PluginRegistrationService(PluginInfoService pluginInfoService, Environment environment, PluginService pluginService) {
        this.pluginInfoService = pluginInfoService;
        this.environment = environment;
        this.pluginService = pluginService;
    }

    public PluginRegistration initAndRegisterPlugin() {
        userToken = getUserToken();
        PluginRegistration pluginRegistration = registerPlugin();

        client.start(pluginRegistration.getProxyEndpoint(), pluginService);
//        client.push(ProxyMessageBuilder.authenticate(new AuthenticatePayload(pluginRegistration.getAccessToken()))).join();
        client.push(ProxyMessageBuilder.subscribe(new TopicSubscribePayload(pluginRegistration.getTopicName()))).join();

        return pluginRegistration;
    }

    private JwtToken getUserToken() {
        String login = environment.getProperty("dh.user.login");
        String password = environment.getProperty("dh.user.password");
        String authEndpoint = environment.getProperty("dh.endpoint.auth");

        UserAuthRequest authRequest = new UserAuthRequest(login, password);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(authEndpoint, authRequest, JwtToken.class);
    }

    private PluginRegistration registerPlugin() {
        String deviceId = environment.getProperty("plugin.device-id");
        String networkIds = environment.getProperty("plugin.network-ids");
        String deviceTypeIds = environment.getProperty("plugin.device-type-ids");
        String names = environment.getProperty("plugin.names");
        boolean returnCommands = environment.getProperty("plugin.return-commands", Boolean.class);
        boolean returnUpdatedCommands = environment.getProperty("plugin.return-updated-commands", Boolean.class);
        boolean returnNotifications = environment.getProperty("plugin.return-notifications", Boolean.class);

        String registrationEndpoint = environment.getProperty("dh.endpoint.plugin");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(registrationEndpoint);
        if (deviceId != null) builder.queryParam("deviceId", deviceId);
        if (networkIds != null) builder.queryParam("networkIds", networkIds);
        if (deviceTypeIds != null) builder.queryParam("deviceTypeIds", deviceTypeIds);
        if (names != null)  builder.queryParam("names", names);

        builder
                .queryParam("returnCommands", returnCommands)
                .queryParam("returnUpdatedCommands", returnUpdatedCommands)
                .queryParam("returnNotifications", returnNotifications);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + userToken.getAccessToken());

        HttpEntity<PluginInfo> requestEntity = new HttpEntity<>(pluginInfoService.getPluginInfo(), headers);

        ResponseEntity<PluginRegistration> response = restTemplate.postForEntity(builder.build().encode().toUri(), requestEntity, PluginRegistration.class);
        return response.getBody();
    }
}
