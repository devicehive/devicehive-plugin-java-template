package com.devicehive.core.service;

import com.devicehive.core.model.*;
import com.devicehive.plugin.PluginService;
import com.devicehive.core.proxy.ProxyMessageBuilder;
import com.devicehive.core.proxy.payload.TopicsPayload;
import com.devicehive.core.proxy.WebSocketKafkaProxyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PluginRegistrationService {

    private final PluginInfoService pluginInfoService;
    private final PluginService pluginService;

    private WebSocketKafkaProxyClient client = new WebSocketKafkaProxyClient();

    @Value("${dh.endpoint.auth}")
    private String authEndpoint;

    @Value("${dh.endpoint.plugin}")
    private String registrationEndpoint;

    @Value("${dh.user.login:}")
    private String login;

    @Value("${dh.user.password:}")
    private String password;

    @Value("${plugin.refresh-token:}")
    private String refreshToken;

    @Value("${plugin.proxy-endpoint:}")
    private String proxyEndpoint;

    @Value("${plugin.topic-name:}")
    private String topicName;

    @Value("${plugin.device-id:}")
    private String deviceId;

    @Value("${plugin.network-ids:}")
    private String networkIds;

    @Value("${plugin.device-type-ids:}")
    private String deviceTypeIds;

    @Value("${plugin.names:}")
    private String names;

    @Value("${plugin.return-commands:}")
    private boolean returnCommands;

    @Value("${plugin.return-updated-commands:}")
    private boolean returnUpdatedCommands;

    @Value("${plugin.return-notifications:}")
    private boolean returnNotifications;

    @Autowired
    public PluginRegistrationService(PluginInfoService pluginInfoService, PluginService pluginService) {
        this.pluginInfoService = pluginInfoService;
        this.pluginService = pluginService;
    }

    public PluginRegistration initAndRegisterPlugin() {
        PluginRegistration pluginRegistration;
        if (!refreshToken.isEmpty() && !proxyEndpoint.isEmpty() && !topicName.isEmpty()) {
            pluginRegistration = new PluginRegistration(getPluginAccessToken(), refreshToken, proxyEndpoint, topicName);
        } else if (!login.isEmpty() && !password.isEmpty()) {
            String accessToken = getUserAccessToken();
            pluginRegistration = registerPlugin(accessToken);
        } else {
            throw new RuntimeException("Plugin config must contain either login and password, or plugin refresh token, proxy endpoint and topic name");
        }

        client.start(pluginRegistration.getProxyEndpoint(), pluginService);
        client.push(ProxyMessageBuilder.subscribe(new TopicsPayload(pluginRegistration.getTopicName()))).join();

        return pluginRegistration;
    }

    private String getUserAccessToken() {
        PasswordAuthRequest authRequest = new PasswordAuthRequest(login, password);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(authEndpoint, authRequest, JwtToken.class).getAccessToken();
    }

    private String getPluginAccessToken() {
        TokenAuthRequest authRequest = new TokenAuthRequest(refreshToken);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(authEndpoint + "/refresh", authRequest, JwtToken.class).getAccessToken();
    }

    private PluginRegistration registerPlugin(String accessToken) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(registrationEndpoint);
        if (!deviceId.isEmpty()) builder.queryParam("deviceId", deviceId);
        if (!networkIds.isEmpty()) builder.queryParam("networkIds", networkIds);
        if (!deviceTypeIds.isEmpty()) builder.queryParam("deviceTypeIds", deviceTypeIds);
        if (!names.isEmpty())  builder.queryParam("names", names);

        builder
                .queryParam("returnCommands", returnCommands)
                .queryParam("returnUpdatedCommands", returnUpdatedCommands)
                .queryParam("returnNotifications", returnNotifications);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<PluginInfo> requestEntity = new HttpEntity<>(pluginInfoService.getPluginInfo(), headers);

        ResponseEntity<PluginRegistration> response = restTemplate.postForEntity(builder.build().encode().toUri(), requestEntity, PluginRegistration.class);
        return response.getBody();
    }
}
