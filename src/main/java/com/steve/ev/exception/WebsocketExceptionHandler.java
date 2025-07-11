package com.steve.ev.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public class WebsocketExceptionHandler {

    public void handleInvalidInputException(WebSocketSession session, InvalidInputException ex) throws IOException {
        sendErrorResponse(session, "InvalidInputException", ex.getMessage());
    }

    // Handle other exceptions in a generic way
    public void handleGenericException(WebSocketSession session, Exception ex) throws IOException {
        sendErrorResponse(session, "Exception", "An unexpected error occurred: " + ex.getMessage());
    }

    // Sends error response in OCPP format
    private void sendErrorResponse(WebSocketSession session, String errorType, String errorMessage)
            throws IOException, IOException {
        String response = String.format(
                "{\"action\": \"%s\", \"status\": \"Error\", \"message\": \"%s\"}",
                errorType, errorMessage
        );
        session.sendMessage(new TextMessage(response));
    }
}
