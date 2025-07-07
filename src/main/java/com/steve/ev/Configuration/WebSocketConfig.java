package com.steve.ev.Configuration;

import com.steve.ev.Handler.OcppWebSocketHandler;
import com.steve.ev.Service.OcppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final OcppWebSocketHandler ocppWebSocketHandler;

    @Autowired
    public WebSocketConfig(OcppWebSocketHandler ocppWebSocketHandler)  {
        this.ocppWebSocketHandler = ocppWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
       registry.addHandler(ocppWebSocketHandler, "/testing/ev/{chargeBoxId}")
               .setAllowedOrigins("*");
    }
}