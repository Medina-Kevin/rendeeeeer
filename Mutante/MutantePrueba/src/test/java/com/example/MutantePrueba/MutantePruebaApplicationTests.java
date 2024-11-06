package com.example.MutantePrueba;

import com.example.MutantePrueba.services.MutantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
class MutantePruebaApplicationTests {

	@Autowired
	private MutantService mutantService;

	@Test
	void testIsMutant() {

		//1) ADN de un mutante
		String[] DNAMutante = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
		char[][] DNAMatrizMutante = convertiraMatriz(DNAMutante);
		MostrarMatriz(DNAMatrizMutante);
		boolean isMutant = mutantService.isMutant(DNAMutante);
		System.out.println("¿ El DNA que ingresaste es de un mutante ?" + isMutant);
		assertTrue(isMutant);

		//2)  ADN de un humano no mutante
		String[] DNAHumano = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
		char[][] DNAMatrizHumano = convertiraMatriz(DNAHumano);
		MostrarMatriz(DNAMatrizHumano);
		isMutant = mutantService.isMutant(DNAHumano);
		System.out.println("¿ El DNA que ingresaste es de un mutante ?" + isMutant);
		assertFalse(isMutant);

		//3)  AND de otro mutante
		String[] DNAMutante2 = {"ATGCGA", "CAGTGC", "TAATGT", "AGAAGG", "CCCCAT", "TCACTA"};
		char[][] DNAMatrizMutante2 = convertiraMatriz(DNAMutante2);
		MostrarMatriz(DNAMatrizMutante2);
		isMutant = mutantService.isMutant(DNAMutante2);
		System.out.println("¿ El DNA que ingresaste es de un mutante ?" + isMutant);
		assertTrue(isMutant);

		//4) Array vacio
		String[] DNAStringVacio = {};
		char[][] DNAMatrizVacia = convertiraMatriz(DNAStringVacio);
		MostrarMatriz(DNAMatrizVacia);
		isMutant = mutantService.isMutant(DNAStringVacio);
		System.out.println("¿ El DNA que ingresaste es de un mutante ?" + isMutant);

		//5) Array de NxM en vez de NxN
		// Recibir matriz de NxM
		String[] DNANxM = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA"};
		char[][] DNAMatrizNxM = convertiraMatriz(DNANxM);
		MostrarMatriz(DNAMatrizNxM);
		isMutant = mutantService.isMutant(DNANxM);
		assertFalse(isMutant);


		//Array de números
		String[] DNANumeros = {"123456", "125648", "127456", "128423", "986145", "512648"};
		char[][] DNAMatrizNumeros = convertiraMatriz(DNANumeros);
		MostrarMatriz(DNAMatrizNumeros);
		isMutant = mutantService.isMutant(DNANumeros);
		System.out.println("¿ El DNA que ingresaste es de un mutante ?" + isMutant);
		assertTrue(isMutant);

		//Recibir null
		String [] DNAnull = null;
		isMutant = mutantService.isMutant(DNAnull);
		MostrarMatriz(null);
	}

	// Convertimos los strings a matrices
	private char[][] convertiraMatriz(String[] dna) {
		int n = dna.length;
		char[][] matrizz = new char[n][n];
		for (int i = 0; i < n; i++) {
			matrizz[i] = dna[i].toCharArray();
		}
		return matrizz;
	}

	//El metodo MostrarMatriz nos muestra en consola el DNA ingresado en forma de matriz, así podremos corroborar si es un mutante o no
	public static void MostrarMatriz(char[][] matrizz) {
		System.out.println("");
		System.out.println("Esta es la matriz que ingresaste");
		System.out.println("|-----------|");
		if (matrizz == null) {
			System.out.println("La matriz es nula.");
		} else {
			// Convertir cada fila de la matriz de caracteres a una cadena y unirlas con saltos de línea
			String matrizString = Arrays.stream(matrizz)
					.map(row -> new String(row))
					.collect(Collectors.joining("\n"));
			System.out.println(matrizString);
		}
		System.out.println("|-----------|");
	}
}