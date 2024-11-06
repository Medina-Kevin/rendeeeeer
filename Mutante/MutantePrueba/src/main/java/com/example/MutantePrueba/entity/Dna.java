package com.example.MutantePrueba.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Dna {

    @Id
    @Column(name = "Secuencia DNA Ingresado")
    private String sequence;
    @Column(name = "Verdadero / Falso")
    private boolean isMutant;
}
