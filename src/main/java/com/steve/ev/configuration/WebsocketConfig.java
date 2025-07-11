package com.steve.ev.configuration;

import com.steve.ev.websocket.ChargerWebsocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
public class WebsocketConfig implements WebSocketConfigurer {

    private final ChargerWebsocketHandler chargerWebsocketHandler;

    @Autowired
    public WebsocketConfig(ChargerWebsocketHandler chargerWebsocketHandler) {
        this.chargerWebsocketHandler = chargerWebsocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
         registry.addHandler(chargerWebsocketHandler, "/ws/chargePoint/{Id}")
                 .setAllowedOrigins("*");
    }
}