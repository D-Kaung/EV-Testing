package com.steve.ev.model;

public class TransactionResponse extends ServerResponse{

    private String transactionId;

    public TransactionResponse(String status, String transactionId) {
        super(status);
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
