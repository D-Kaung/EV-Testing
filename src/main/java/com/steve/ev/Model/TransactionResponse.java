package com.steve.ev.Model;

public class TransactionResponse extends ServerResponse{

    private String transactionId;

    public TransactionResponse(Long status, String transactionId) {
        super(String.valueOf(status));
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
