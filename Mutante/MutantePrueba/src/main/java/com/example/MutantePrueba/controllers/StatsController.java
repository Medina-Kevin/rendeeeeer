package com.example.MutantePrueba.controllers;

import com.example.MutantePrueba.DTO.StatsDTO;
import com.example.MutantePrueba.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping
    public ResponseEntity<StatsDTO> getStats() {
        StatsDTO stats = statsService.getStats();
        return ResponseEntity.ok(stats);
    }
}