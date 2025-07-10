package com.steve.ev.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.steve.ev.model.ServerResponse;
import org.springframework.stereotype.Service;

@Service
public class StopTransactionHandler implements ActionHandler{

    @Override
    public ServerResponse handle(String chargerId, JsonNode payload) {
        return null;
    }
}
