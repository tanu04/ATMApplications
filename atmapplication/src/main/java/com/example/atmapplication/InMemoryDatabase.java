package com.example.atmapplication;

import com.example.atmapplication.entity.CameraLog;
import com.example.atmapplication.entity.FailureIncident;
import com.example.atmapplication.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryDatabase {
    private List<Transaction> transactions = new ArrayList<>();
    private List<FailureIncident> failureLogs = new ArrayList<>();
    private List<CameraLog> cameraLogs = new ArrayList<>();

    // Method to get all transactions
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // Other getter methods for failure logs and camera logs
    public List<FailureIncident> getAllFailureLogs() {
        return failureLogs;
    }

    public List<CameraLog> getAllCameraLogs() {
        return cameraLogs;
    }
}
