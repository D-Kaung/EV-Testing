package com.steve.ev.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.steve.ev.Model.ServerResponse;

@FunctionalInterface
public interface ActionHandler {

    ServerResponse handle(Long chargeId, JsonNode payload);
}
