package com.steve.ev.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class OcppService {

    public void handleIncomingMessage(WebSocketSession session, String rawMessage) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(rawMessage);

        int messageType = node.get(0).asInt();
        String uniqueId = node.get(1).asText();

        if (messageType == 2) {
            String action = node.get(2).asText();
            JsonNode payload = node.get(3);

            switch (action) {
                case "BootNotification":
                    handleBootNotification(session, uniqueId);
                    break;

                case "Authorize":
                    handleAuthorize(session, uniqueId, payload);
                    break;

                case "StatusNotification":
                    handleStatusNotification(session, uniqueId);
                    break;

                case "TriggerMessage":
                    handleTriggerMessage(session, uniqueId);
                    break;

            }
        }
    }

    private void handleTriggerMessage(WebSocketSession session, String uid)
            throws IOException {
        String response = String.format("[3,\"%s\",{\"status\":\"Accepted\"}]", uid);
        session.sendMessage(new TextMessage(response));
    }

    private void handleStatusNotification(WebSocketSession session, String uid) throws IOException {
        String response = String.format("[3,\"%s\",{\"currentTime\":\"%s\"status\":\"Available\"}]",
                uid, LocalDateTime.now());
        session.sendMessage(new TextMessage(response));
    }

    private void handleBootNotification(WebSocketSession session, String uid) throws IOException {
        String response = String.format("[3,\"%s\",{\"currentTime\":\"%s\",\"interval\":300,\"" +
                        "status\":\"Accepted\"}]",
                uid, LocalDateTime.now());
        session.sendMessage(new TextMessage(response));
    }

    private void handleAuthorize(WebSocketSession session, String uid, JsonNode payload) throws IOException {
        String idTag = payload.get("idTag").asText();
        // Assume always authorized
        String response = String.format("[3,\"%s\",{\"idTagInfo\":{\"status\":\"Accepted\"}}]", uid);
        session.sendMessage(new TextMessage(response));
    }
}