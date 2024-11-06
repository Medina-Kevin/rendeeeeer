package com.example.MutantePrueba.services;

import com.example.MutantePrueba.repositories.DnaRepository;
import com.example.MutantePrueba.entity.Dna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantServiceImpl implements MutantService {

    @Autowired
    private DnaRepository dnaRepository;

    @Override
    public boolean isMutant(String[] dna) {
        //Aqui si nos ingresan una matriz nula devolverá falso
        if (dna == null) {
            return false;
        }
        // Convertimos el array en una matriz
        char[][] matrizz = convertiraMatriz(dna);

        // Verificar si la matriz es de NxN
        if (!esMatrizNxN(matrizz)) {
            System.out.println("La matriz debe ser de NxN.");
            return false;
        }

        // Detectaremos si es un mutante usando un contador
        int n = matrizz.length;
        int contadorLetras = 0;

        // Verificamos todas las filas
        for (int i = 0; i < n; i++) {
            if (verificarFila(matrizz, i, 1, matrizz[i][0], 1)) {
                contadorLetras++;
                if (contadorLetras > 1) {
                    guardarDNA(dna, true);
                    return true;
                }
            }
        }

        // Verificamos todas las columnas
        for (int j = 0; j < n; j++) {
            if (verificarColumna(matrizz, 1, j, matrizz[0][j], 1)) {
                contadorLetras++;
                if (contadorLetras > 1) {
                    guardarDNA(dna, true);
                    return true;
                }
            }
        }

        // Verificar diagonal principal
        for (int i = 0; i < n - 3; i++) {
            for (int j = 0; j < n - 3; j++) {
                if (verificarDiagonalPrincipal(matrizz, i, j, matrizz[i][j], 1)) {
                    contadorLetras++;
                    if (contadorLetras > 1) {
                        guardarDNA(dna, true);
                        return true;
                    }
                }
            }
        }

        // Verificar contradiagonal
        for (int i = 0; i < n - 3; i++) {
            for (int j = 3; j < n; j++) {
                if (verificarContradiagonal(matrizz, i, j, matrizz[i][j], 1)) {
                    contadorLetras++;
                    if (contadorLetras > 1) {
                        guardarDNA(dna, true);
                        return true;
                    }
                }
            }
        }

        guardarDNA(dna, false);
        return contadorLetras > 1;
    }

    // Aqui guardaremos los DNA en la base de datos
    private void guardarDNA(String[] dna, boolean isMutant) {
        String sequence = String.join("", dna);
        Dna dnaEntity = new Dna(sequence, isMutant);
        dnaRepository.save(dnaEntity);
    }

    // Metodo para convertir strings a matriz
    private char[][] convertiraMatriz(String[] dna) {
        if (dna == null) {
            return null;
        }
        int n = dna.length;
        char[][] matrizz = new char[n][n];
        for (int i = 0; i < n; i++) {
            matrizz[i] = dna[i].toCharArray();
        }
        return matrizz;
    }

    //Metodo para saber si es una matriz de NxN
    private boolean esMatrizNxN(char[][] matriz) {
        int n = matriz.length;
        for (int i = 0; i < n; i++) {
            if (matriz[i].length != n) {
                return false;
            }
        }
        return true;
    }

    // Metodo para verificar las filas
    private boolean verificarFila(char[][] dnaMatrix, int fila, int columna, char letraAnterior, int contadorConsecutivas) {
        if (columna >= dnaMatrix.length) {
            return false;
        }

        if (dnaMatrix[fila][columna] == letraAnterior) {
            contadorConsecutivas++;
            if (contadorConsecutivas == 4) {
                return true;
            }
        } else {
            contadorConsecutivas = 1;
            letraAnterior = dnaMatrix[fila][columna];
        }

        return verificarFila(dnaMatrix, fila, columna + 1, letraAnterior, contadorConsecutivas);
    }

    // Metodo para verificar las columnas
    private boolean verificarColumna(char[][] dnaMatrix, int fila, int columna, char letraAnterior, int contadorConsecutivas) {
        if (fila >= dnaMatrix.length) {
            return false;
        }
        if (dnaMatrix[fila][columna] == letraAnterior) {
            contadorConsecutivas++;
            if (contadorConsecutivas == 4) {
                return true;
            }
        } else {
            contadorConsecutivas = 1;
            letraAnterior = dnaMatrix[fila][columna];
        }
        return verificarColumna(dnaMatrix, fila + 1, columna, letraAnterior, contadorConsecutivas);
    }

    // Metodo para verificar la diagonal principal de la matriz
    private boolean verificarDiagonalPrincipal(char[][] dnaMatrix, int fila, int columna, char letraAnterior, int contadorConsecutivas) {
        if (fila >= dnaMatrix.length || columna >= dnaMatrix.length) {
            return false;
        }
        if (dnaMatrix[fila][columna] == letraAnterior) {
            contadorConsecutivas++;
            if (contadorConsecutivas == 4) {
                return true;
            }
        } else {
            contadorConsecutivas = 1;
            letraAnterior = dnaMatrix[fila][columna];
        }
        return verificarDiagonalPrincipal(dnaMatrix, fila + 1, columna + 1, letraAnterior, contadorConsecutivas);
    }

    // Metodo para verificar la contradiagonal de la matriz
    private boolean verificarContradiagonal(char[][] dnaMatrix, int fila, int columna, char letraAnterior, int contadorConsecutivas) {
        if (fila >= dnaMatrix.length || columna < 0) {
            return false;
        }
        if (dnaMatrix[fila][columna] == letraAnterior) {
            contadorConsecutivas++;
            if (contadorConsecutivas == 4) {
                return true;
            }
        } else {
            contadorConsecutivas = 1;
            letraAnterior = dnaMatrix[fila][columna];
        }
        return verificarContradiagonal(dnaMatrix, fila + 1, columna - 1, letraAnterior, contadorConsecutivas);
    }
}