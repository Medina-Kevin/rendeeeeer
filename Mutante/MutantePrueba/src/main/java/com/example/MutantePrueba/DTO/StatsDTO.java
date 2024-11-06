package com.example.MutantePrueba.DTO;

import lombok.Data;

@Data
public class StatsDTO {
    private long countMutantDna;
    private long countHumanDna;
    private double ratio;
}