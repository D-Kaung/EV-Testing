package com.steve.ev.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class DataExchangeUtil {

    public DataExchangeUtil() {

    }

    public static String getChargingPointIdFromPath(WebSocketSession webSocketSession) {
        String path = webSocketSession.getUri().getPath();
        return path.substring(path.lastIndexOf("/") + 1);
    }

    public static TextMessage sendOcppResponse(String messageId, String action, String payload) {
        String responseMessage = String.format("{\"messageId\": \"%s\", \"action\": \"%s\", \"payload\": %s}",
                messageId, action, payload);
        return new TextMessage(responseMessage);
    }

    public static String convertObjectToJsonString(Object o){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting object to JSON", e);
        }
    }
}
