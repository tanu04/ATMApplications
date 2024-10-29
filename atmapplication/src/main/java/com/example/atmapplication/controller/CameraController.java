package com.example.atmapplication.controller;


import com.example.atmapplication.InMemoryDatabase;
import com.example.atmapplication.entity.CameraLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/camera")
public class CameraController {
    private final String AUTH_KEY = "intutive_cloud";
    private final InMemoryDatabase database;

    public CameraController(InMemoryDatabase database) {
        this.database = database;
    }

    @GetMapping("/logs")
    public List<CameraLog> getCameraLogs(
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime) {
        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);

        return database.getAllCameraLogs().stream()
                .filter(log -> log.getStartTime().isAfter(start) && log.getEndTime().isBefore(end))
                .collect(Collectors.toList());
    }

}
