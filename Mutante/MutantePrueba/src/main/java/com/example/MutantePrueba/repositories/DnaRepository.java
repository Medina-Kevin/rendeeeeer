package com.example.MutantePrueba.repositories;

import com.example.MutantePrueba.entity.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepository extends JpaRepository<Dna, String> {

    @Query("SELECT COUNT(d) FROM Dna d WHERE d.isMutant = true")
    long countMutantDna();

    @Query("SELECT COUNT(d) FROM Dna d WHERE d.isMutant = false")
    long countHumanDna();
}