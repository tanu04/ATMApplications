package com.example.atmapplication.controller;


import com.example.atmapplication.InMemoryDatabase;
import com.example.atmapplication.entity.FailureIncident;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/failures")
public class FailureController {
    private final String AUTH_KEY = "intutive_cloud";
    private final InMemoryDatabase database;

    public FailureController(InMemoryDatabase database) {
        this.database = database;
    }

    @GetMapping
    public ResponseEntity<?> getFailures(@RequestParam(value = "authKey", required = false) String authKey) {
        if (authKey == null || !authKey.equals(AUTH_KEY)) {
            return new ResponseEntity<>("Access Denied: Unauthorized user, Please add correct auth key to access", HttpStatus.FORBIDDEN);  // Return 403 status code for unauthorized users
        }
        return ResponseEntity.ok(database.getAllFailureLogs());
    }
}
