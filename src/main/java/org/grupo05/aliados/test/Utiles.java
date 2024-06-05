package org.grupo05.aliados.test;

import org.grupo05.aliados.logica.Resolucion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Utiles {

    /**
     * Ejecuta un caso de prueba específico para el problema de los aliados y verifica
     * que el resultado obtenido coincida con el resultado esperado.
     *
     * @param caso              El caso de prueba como un String, con el formato
     *                          especificado en el enunciado del problema.
     * @param resultadoEsperado El resultado esperado como un String, en el formato
     *                          "cantidad_aliados_x cantidad_aliados_y".
     */
    static void ejecutarYVerificarCaso(String caso, String resultadoEsperado, Resolucion resolucion) {
        try {
            generarArchivoPrueba("ALIADOS_test.IN", caso);

            String out = resolucion.resolver("ALIADOS_test.IN", "ALIADOS_test.OUT");

            String resultadoObtenido = leerArchivoSalida("ALIADOS_test.OUT");

            assertEquals(resultadoEsperado, resultadoObtenido, "El resultado no coincide con el esperado para el caso: " + caso);
            assertEquals(resultadoEsperado, out, "El resultado no coincide con el esperado para el caso: " + caso);
        } catch (IOException e) {
            fail("Error al ejecutar el caso de prueba: " + e.getMessage());
        } finally {
            eliminarArchivo("ALIADOS_test.IN");
            eliminarArchivo("ALIADOS_test.OUT");
        }
    }

    /**
     * Lee el contenido de un archivo y lo devuelve como un String.
     *
     * @param filename La ruta del archivo a leer.
     * @return El contenido del archivo como un String.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    static String leerArchivoSalida(String filename) throws IOException {
        return Files.readString(Paths.get(filename));
    }

    /**
     * Calcula el número de aliados de un vecino dado, según las reglas del problema,
     * a partir de un caso de prueba representado como un String.
     *
     * @param caso     El caso de prueba como un String, con el formato especificado en el enunciado del problema.
     * @param vecino   El ID del vecino para el cual se calcularán los aliados.
     * @param oponente El ID del oponente del vecino.
     * @return El número de aliados del vecino dado.
     */
    static int calcularAliados(String caso, int vecino, int oponente) {
        String[] partesCaso = caso.split("\n");
        Map<Integer, Integer> amistadesVecino = new HashMap<>();
        Map<Integer, Integer> amistadesOponente = new HashMap<>();

        for (int i = 1; i < partesCaso.length; i++) {
            String[] partesLinea = partesCaso[i].split(" ");
            int k = Integer.parseInt(partesLinea[0]);
            int r = Integer.parseInt(partesLinea[1]);
            int L = Integer.parseInt(partesLinea[2]);

            if (k == vecino) {
                amistadesVecino.put(r, L);
            } else if (r == vecino) {
                amistadesVecino.put(k, L);
            }

            if (k == oponente) {
                amistadesOponente.put(r, L);
            } else if (r == oponente) {
                amistadesOponente.put(k, L);
            }
        }

        int aliados = 0;
        for (Map.Entry<Integer, Integer> entrada : amistadesVecino.entrySet()) {
            int otroVecino = entrada.getKey();
            int fuerzaAmistadVecino = entrada.getValue();
            int fuerzaAmistadOponente = amistadesOponente.getOrDefault(otroVecino, -1);

            if (fuerzaAmistadVecino > fuerzaAmistadOponente) {
                aliados++;
            }
        }

        return aliados;
    }

    /**
     * Genera un caso de prueba aleatorio para el problema de los aliados y lo guarda en un archivo.
     *
     * @param numVecinos    El número de vecinos en el caso de prueba.
     * @param numLazos      El número de lazos de amistad en el caso de prueba.
     * @param nombreArchivo El nombre del archivo donde se guardará el caso de prueba.
     * @return El caso de prueba generado como un String.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    static String generarCasoPrueba(int numVecinos, int numLazos, String nombreArchivo) throws IOException {
        if (numLazos > numVecinos * (numVecinos - 1) / 2) {
            throw new IllegalArgumentException("El número de lazos no puede ser mayor que la cantidad máxima de combinaciones posibles.");
        }

        Random random = new Random();
        Set<String> lazosExistentes = new HashSet<>();

        // Genera los oponentes
        int x = random.nextInt(numVecinos) + 1;
        int y = random.nextInt(numVecinos) + 1;
        while (x == y) {
            y = random.nextInt(numVecinos) + 1;
        }

        // Genera las relaciones de amistad
        StringBuilder relaciones = new StringBuilder();
        for (int i = 0; i < numLazos; i++) {
            int k, r;
            String lazo;

            // genero un lazo de amistad que no haya generado antes y que no sea entre x e y
            do {
                k = random.nextInt(numVecinos) + 1;
                r = random.nextInt(numVecinos) + 1;
                while (k == r) {
                    r = random.nextInt(numVecinos) + 1;
                }
                lazo = Math.min(k, r) + "-" + Math.max(k, r);
            } while (lazosExistentes.contains(lazo) || (k == x && r == y) || (k == y && r == x));

            lazosExistentes.add(lazo);

            int L = random.nextInt(100) + 1;
            relaciones.append(k).append(" ").append(r).append(" ").append(L).append("\n");
        }

        // Crea el archivo de entrada
        File file = new File(nombreArchivo);
        FileWriter writer = new FileWriter(file);
        String caso = numVecinos + " " + numLazos + " " + x + " " + y + "\n" + relaciones;
        writer.write(caso);
        writer.close();
        return caso;
    }

    /**
     * Genera un archivo de prueba con el contenido dado.
     *
     * @param nombreArchivo El nombre del archivo de prueba a generar.
     * @param contenido     El contenido del archivo de prueba.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    static void generarArchivoPrueba(String nombreArchivo, String contenido) throws IOException {
        Files.write(Paths.get(nombreArchivo), contenido.getBytes());
    }

    /**
     * Elimina un archivo si existe.
     *
     * @param filename La ruta del archivo a eliminar.
     */
    static void eliminarArchivo(String filename) {
        File archivo = new File(filename);
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    /**
     * Genera y ejecuta un conjunto de casos de prueba aleatorios para el problema
     * de los aliados, utilizando una implementación de la solucion
     * Para cada caso de prueba, se genera un archivo de entrada con datos aleatorios
     * y se ejecuta la solución proporcionada. Luego, se calcula el resultado esperado
     * y se compara con el resultado obtenido por la solución, verificando que ambos
     * coincidan.
     *
     * @param pruebas    El número de casos de prueba aleatorios a generar y ejecutar.
     * @param resolucion La implementación de la interfaz {@link Resolucion} que
     *                   contiene la solución al problema.
     */
    static void probadorSolucionN(int pruebas, Resolucion resolucion) {
        Random random = new Random();
        for (int i = 0; i < pruebas; i++) {
            try {
                int numVecinos = random.nextInt(198) + 3; // Genera un número entre 3 y 200
                int numLazos = Math.min(random.nextInt((numVecinos * (numVecinos - 1)) / 2 - 1), 5000);

                String caso = generarCasoPrueba(numVecinos, numLazos, "ALIADOS_test.IN");

                String[] partesCaso = caso.split("\n");
                String[] primeraLinea = partesCaso[0].split(" ");
                int x = Integer.parseInt(primeraLinea[2]);
                int y = Integer.parseInt(primeraLinea[3]);

                String out = resolucion.resolver("ALIADOS_test.IN", "ALIADOS_test.OUT");

                int aliadosX = calcularAliados(caso, x, y);
                int aliadosY = calcularAliados(caso, y, x);
                String resultadoEsperado = aliadosX + " " + aliadosY;

                String resultadoObtenido = leerArchivoSalida("ALIADOS_test.OUT");

                assertEquals(resultadoEsperado, resultadoObtenido, "El resultado no coincide con el esperado para el caso: " + caso);
                assertEquals(resultadoEsperado, out, "El resultado no coincide con el esperado para el caso: " + caso);
            } catch (IOException e) {
                fail("Error al ejecutar el caso de prueba: " + e.getMessage());
            } finally {
                eliminarArchivo("ALIADOS_test.IN");
                eliminarArchivo("ALIADOS_test.OUT");
            }
        }
    }
}
