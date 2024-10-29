package com.example.atmapplication.entity;

import com.example.atmapplication.enums.FailureType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FailureIncident {
    private String id;
    private String atmId;
    private LocalDateTime timestamp;
    private FailureType failureType; // e.g., "Device Error", "Network Down"
    private String context;
}
