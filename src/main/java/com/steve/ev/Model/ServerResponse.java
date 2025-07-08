package com.steve.ev.Model;

public class ServerResponse {

    private String status;

    public ServerResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
