package com.example.MutantePrueba.services;

import com.example.MutantePrueba.DTO.StatsDTO;
import com.example.MutantePrueba.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Autowired
    private DnaRepository dnaRepository;

    public StatsDTO getStats() {
        long countMutantDna = dnaRepository.countMutantDna();
        long countHumanDna = dnaRepository.countHumanDna();
        double ratio = countHumanDna == 0 ? 0 : (double) countMutantDna / countHumanDna;

        StatsDTO stats = new StatsDTO();
        stats.setCountMutantDna(countMutantDna);
        stats.setCountHumanDna(countHumanDna);
        stats.setRatio(ratio);

        return stats;
    }
}