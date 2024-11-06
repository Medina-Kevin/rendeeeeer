package com.example.MutantePrueba.repositories;

import java.util.List;

public interface MutantRepository {
    void saveMutant(String[] dna, boolean isMutant);
    List<String[]> findAllMutants();
    int countMutants();
    int countHumans();
}
