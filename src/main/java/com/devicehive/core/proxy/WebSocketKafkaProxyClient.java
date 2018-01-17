package com.devicehive.core.proxy;

import com.devicehive.core.proxy.payload.MessagePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@ClientEndpoint(
        encoders = {ProxyMessageEncoder.class}, decoders = {ProxyMessageDecoder.class}
)
public class WebSocketKafkaProxyClient {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketKafkaProxyClient.class);

    private Map<String, CompletableFuture<ProxyMessage>> futureMap;
    private Session session;
    private DhMessageHandler messageHandler;

    public void start(String uri, DhMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        try {
            this.futureMap = new ConcurrentHashMap<>();
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            this.session = container.connectToServer(this, new URI("ws://" + uri));
        } catch (Exception e) {
            logger.error("Error during establishing connection: ", e);
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        try {
            session.close();
        } catch (IOException e) {
            logger.error("Error during closing connection: ", e);
        }
    }

    public CompletableFuture<ProxyMessage> push(ProxyMessage message) {
        this.session.getAsyncRemote().sendObject(message);

        CompletableFuture<ProxyMessage> future = new CompletableFuture<>();
        futureMap.put(message.getId(), future);
        logger.info("Message {} was sent", message);
        return future;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        logger.info("New WebSocket session established: {}", session.getId());
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        logger.info("WebSocket session {} closed, close code {}", session.getId(), reason.getCloseCode());
        this.session = null;
        futureMap.clear();
    }

    @OnMessage
    public void onMessage(List<ProxyMessage> messages) {
        messages.forEach(message -> {
            if (message.getStatus() == null || message.getStatus() != 0) {
                MessagePayload payload = (MessagePayload) message.getPayload();
                String msg = "Response message is failed: " + payload.getMessage();
                logger.warn(msg);
                throw new RuntimeException(msg);
            }

            String id = message.getId();
            CompletableFuture<ProxyMessage> future = futureMap.get(id);
            if (future != null) {
                future.complete(message);
                futureMap.remove(id);
            }

            if ("notif".equals(message.getType()) && message.getAction() == null) {
                MessagePayload payload = (MessagePayload) message.getPayload();
                messageHandler.handleMessage(payload.getMessage());
            }
            logger.info("Message {} was received", message);
        });
    }
}
