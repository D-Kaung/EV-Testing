package com.steve.ev.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steve.ev.cache.ChargingPointStatusCache;
import com.steve.ev.exception.InvalidInputException;
import com.steve.ev.exception.WebsocketExceptionHandler;
import com.steve.ev.model.ServerResponse;
import com.steve.ev.service.ActionHandler;
import com.steve.ev.util.DataExchangeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class ChargerWebsocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> chargerSessions = new ConcurrentHashMap<>();

    private final ActionHandlerFactory actionHandlerFactory;
    private final ChargingPointStatusCache chargingPointStatusCache;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final WebsocketExceptionHandler websocketExceptionHandler;

    public ChargerWebsocketHandler(ActionHandlerFactory actionHandlerFactory,
                                   ChargingPointStatusCache chargingPointStatusCache,
                                   WebsocketExceptionHandler websocketExceptionHandler){
        this.chargingPointStatusCache = chargingPointStatusCache;
        this.actionHandlerFactory = actionHandlerFactory;
        this.websocketExceptionHandler = websocketExceptionHandler;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String chargerId = DataExchangeUtil.getChargingPointIdFromPath(session);
        JsonNode node = objectMapper.readTree(message.getPayload());

        String messageId = node.path("messageId").asText();
        String action = node.path("action").asText();

//        System.out.print("New message received from {} for action: {}. messageId: {}",
//                chargerId, action, messageId);

        ActionHandler handler = actionHandlerFactory.getHandler(action);

        if (handler != null) {

            try{
                ServerResponse actionStatus = handler.handle(chargerId, node.path("payload"));
                TextMessage response = DataExchangeUtil.sendOcppResponse(action, messageId, DataExchangeUtil.convertObjectToJsonString(actionStatus));
                session.sendMessage(response);

                chargingPointStatusCache.updateStatus(chargerId, LocalDateTime.now());
            } catch (InvalidInputException ex){
                websocketExceptionHandler.handleInvalidInputException(session, ex);
            } catch (RuntimeException ex){
                websocketExceptionHandler.handleGenericException(session, ex);
            }

        } else {
            throw new InvalidInputException("Invalid Action received");
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String chargerId = DataExchangeUtil.getChargingPointIdFromPath(session);
        chargerSessions.put(chargerId, session);
//        log.info("New Connection is established with {}" , chargerId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String chargerId = DataExchangeUtil.getChargingPointIdFromPath(session);
        chargerSessions.put(chargerId, session);
//        log.info("Connection is closed with {}", chargerId);
    }
}