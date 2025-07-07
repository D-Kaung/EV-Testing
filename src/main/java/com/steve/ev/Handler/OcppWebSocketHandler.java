package com.steve.ev.Handler;

import com.steve.ev.Service.OcppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class OcppWebSocketHandler extends TextWebSocketHandler {

    private final OcppService ocppService;

    @Autowired
    public OcppWebSocketHandler(OcppService ocppService) {
        this.ocppService = ocppService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws IOException {
        ocppService.handleIncomingMessage(session, message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Connected: " + session.getUri());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("DisConnected: " + session.getUri());
        super.afterConnectionClosed(session, status);
    }
}