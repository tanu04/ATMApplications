package com.example.atmapplication.entity;

import com.example.atmapplication.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class Transaction {
    private String id;
    private String atmId;
    private TransactionType type; // "Deposit", "Withdrawal", Balance Information, etc.
    private LocalDateTime timestamp;
    private double amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAtmId() {
        return atmId;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
