package com.steve.ev.websocket;

import com.steve.ev.service.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ActionHandlerFactory {

    private final Map<String, ActionHandler> actionHandlers;

    public ActionHandlerFactory(BootNotificationHandler bootNotificationHandler,
                                HeartbeatHandler heartbeatHandler,
                                StartTransactionHandler startTransactionHandler,
                                StatusNotificationHandler statusNotificationHandler,
                                StopTransactionHandler stopTransactionHandler){
        this.actionHandlers = Map.of(
                "BootNotification", bootNotificationHandler,
                "HeartBeat", heartbeatHandler,
                "StartTransaction", startTransactionHandler,
                "StatusNotification", statusNotificationHandler,
                "StopTransaction", stopTransactionHandler
        );
    }

    public ActionHandler getHandler(String action) {
        return actionHandlers.get(action);
    }
}
