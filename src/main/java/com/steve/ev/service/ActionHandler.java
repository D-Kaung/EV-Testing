package com.steve.ev.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.steve.ev.model.ServerResponse;

@FunctionalInterface
public interface ActionHandler {

    ServerResponse handle(String chargerId, JsonNode payload);
}
