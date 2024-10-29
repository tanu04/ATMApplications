package com.example.atmapplication;

import com.example.atmapplication.entity.CameraLog;
import com.example.atmapplication.entity.FailureIncident;
import com.example.atmapplication.entity.Transaction;
import com.example.atmapplication.enums.FailureType;
import com.example.atmapplication.enums.TransactionType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TestDataInit implements CommandLineRunner {

    private final InMemoryDatabase database;

    public TestDataInit(InMemoryDatabase database) {
        this.database = database;
    }

    @Override
    public void run(String... args) {
        System.out.println("Data init call");

        LocalDateTime now = LocalDateTime.now();

        // Initialize transactions
        addTransaction("ATM01", TransactionType.WITHDRAWAL, now.minusMinutes(50), 4000.0);
        addTransaction("ATM01", TransactionType.DEPOSIT, now.minusHours(1), 200.0);
        addTransaction("ATM02", TransactionType.WITHDRAWAL, now.minusMinutes(30), 100.0);

        // Add some failure incidents
        addFailureIncident("ATM01", now.minusDays(1), FailureType.DEVICE_ERROR, "Failed Deposit");
        addFailureIncident("ATM02", now.minusHours(10), FailureType.NETWORK_DOWN,"Server Down");
        // Add some camera logs
        addCameraLog("ATM01", now.minusHours(1), now, "/videos/atm01.mp4");

        // Debugging output to verify the transactions added
        System.out.println("Initialized transactions: " + database.getAllTransactions());
    }

    private void addTransaction(String atmId, TransactionType type, LocalDateTime timestamp, double amount) {
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), atmId, type, timestamp, amount);
        database.getAllTransactions().add(transaction);  // Call a method from InMemoryDatabase to add the transaction
    }

    private void addFailureIncident(String atmId, LocalDateTime timestamp, FailureType errorType, String description) {
        FailureIncident incident = new FailureIncident(UUID.randomUUID().toString(), atmId, timestamp, errorType, description);
        database.getAllFailureLogs().add(incident);
    }

    private void addCameraLog(String atmId, LocalDateTime startTime, LocalDateTime endTime, String videoPath) {
        CameraLog log = new CameraLog(UUID.randomUUID().toString(), atmId, startTime, endTime, videoPath);
        database.getAllCameraLogs().add(log);
    }
}
