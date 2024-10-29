package com.example.atmapplication.controller;

import com.example.atmapplication.InMemoryDatabase;
import com.example.atmapplication.entity.Transaction;
import com.example.atmapplication.enums.TransactionType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final String AUTH_KEY = "intutive_cloud";
    private final InMemoryDatabase database;

    public TransactionController(InMemoryDatabase database) {
        this.database = database;
    }

    @GetMapping("/count")
    public ResponseEntity<?> getTotalCustomersLast24Hours(@RequestParam(value = "authKey", required = false) String authKey) {
        if (authKey == null || !authKey.equals(AUTH_KEY)) {
            return new ResponseEntity<>("Access Denied: Unauthorized user, Please add correct auth key to access", HttpStatus.FORBIDDEN);  // Return 403 status code for unauthorized users
        }
        LocalDateTime cutoffTime = LocalDateTime.now().minusDays(1);
        System.out.println("Filtering transactions after: " + cutoffTime);

        long count = database.getAllTransactions().stream()
                .peek(t -> System.out.println("Transaction timestamp: " + t.getTimestamp()))
                .filter(t -> t.getTimestamp().isAfter(cutoffTime))
                .count();

        System.out.println("Total transactions in the last 24 hours: " + count);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/type")
    public ResponseEntity<?> getTransactionBreakdown(@RequestParam(value = "authKey", required = false) String authKey) {
        if (authKey == null || !authKey.equals(AUTH_KEY)) {
            return new ResponseEntity<>("Access Denied: Unauthorized user, Please add correct auth key to access", HttpStatus.FORBIDDEN);  // Return 403 status code for unauthorized users
        }
        return ResponseEntity.ok(database.getAllTransactions().stream()
                .collect(Collectors.groupingBy(Transaction::getType, Collectors.counting())));
    }

}
