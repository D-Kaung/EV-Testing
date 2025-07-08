package com.steve.ev.configuration;

import com.steve.ev.websocket.ChargeWebsocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
public class WebsocketConfig implements WebSocketConfigurer {

    private final ChargeWebsocketHandler chargeWebsocketHandler;

    public WebsocketConfig(ChargeWebsocketHandler chargeWebsocketHandler) {
        this.chargeWebsocketHandler = chargeWebsocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
         registry.addHandler(chargeWebsocketHandler, "ev/test/{stationId}")
                 .setAllowedOrigins("*");
    }
}
