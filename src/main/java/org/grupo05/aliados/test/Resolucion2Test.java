package org.grupo05.aliados.test;

import org.grupo05.aliados.logica.Resolucion;
import org.grupo05.aliados.logica.Resolucion2;
import org.junit.Test;

import static org.grupo05.aliados.test.Utiles.ejecutarYVerificarCaso;
import static org.grupo05.aliados.test.Utiles.probadorSolucionN;

public class Resolucion2Test {

    /**
     * Ejecuta el probador N veces para la solucion dada
     */
    @Test
    public void testCasosAleatorios() {
        int cantidadPruebas = 350;
        probadorSolucionN(cantidadPruebas, new Resolucion2());
    }

    @Test
    public void testCasoPuntual1() {
        String caso = "7 10 1 5\n" +
                "1 2 29\n" +
                "2 5 43\n" +
                "3 1 12\n" +
                "2 3 9\n" +
                "4 5 6\n" +
                "1 4 6\n" +
                "3 5 7\n" +
                "4 6 78\n" +
                "3 7 98\n" +
                "6 1 2";
        String resultadoEsperado = "2 1";
        ejecutarYVerificarCaso(caso, resultadoEsperado, new Resolucion2());
    }

    @Test
    public void testCasoPuntual2() {
        String caso = "5 5 1 3\n" +
                "1 2 5\n" +
                "2 3 10\n" +
                "3 4 1\n" +
                "1 4 7\n" +
                "2 5 2";
        String resultadoEsperado = "1 1";
        ejecutarYVerificarCaso(caso, resultadoEsperado, new Resolucion2());
    }

    @Test
    public void testCasoPuntual3() {
        String caso = "10 14 2 7\n" +
                "1 2 15\n" +
                "2 3 10\n" +
                "3 4 20\n" +
                "4 5 12\n" +
                "5 6 18\n" +
                "6 7 25\n" +
                "7 8 17\n" +
                "8 9 19\n" +
                "9 10 11\n" +
                "1 3 8\n" +
                "2 4 16\n" +
                "3 5 14\n" +
                "4 6 13\n" +
                "5 7 9";
        String resultadoEsperado = "3 3";
        ejecutarYVerificarCaso(caso, resultadoEsperado, new Resolucion2());
    }

    @Test
    public void testCasoPuntual4() {
        String caso = "4 4 1 2\n" +
                "1 2 10\n" +
                "1 3 5\n" +
                "2 4 15\n" +
                "3 4 20";
        String resultadoEsperado = "2 1";
        ejecutarYVerificarCaso(caso, resultadoEsperado, new Resolucion2());
    }

    @Test
    public void testCasoPuntual5() {
        String caso = "5 4 3 1\n" +
                "3 4 5\n" +
                "2 3 10\n" +
                "1 4 8\n" +
                "2 5 7";
        String resultadoEsperado = "1 1";
        ejecutarYVerificarCaso(caso, resultadoEsperado, new Resolucion2());
    }

}
