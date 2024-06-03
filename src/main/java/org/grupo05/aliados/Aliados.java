package org.grupo05.aliados;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aliados {

    /*
    * solucion de O(m+n)
    * */
    public static String resolver(String inputFilename){
        try {
            List<String> lineas = Files.readAllLines(Paths.get(inputFilename));
            String[] primeraLinea = lineas.get(0).split(" ");
            int n = Integer.parseInt(primeraLinea[0]); // cantidad de vecinos
            int m = Integer.parseInt(primeraLinea[1]); // cantidad de lazos
            int x = Integer.parseInt(primeraLinea[2]); // primer oponente
            int y = Integer.parseInt(primeraLinea[3]); // segundo oponente

            // Aca en realidad solo voy a poner dos elementos en este mapa, los lazos de amistad para x e y,
            // pero se usa un mapa para diferenciar los mapas de lazos con la key, en vez de utilizar dos mapas por separados
            // por que de esa manera deberia hacer mas preguntas para saber que mapa utilizar en cada caso
            Map<Integer, Map<Integer, Integer>> amistades = new HashMap<>();
            amistades.put(x, new HashMap<>());
            amistades.put(y, new HashMap<>());

            // Leo las m relaciones de amistad
            for (int i = 1; i <= m; i++) {
                String[] parts = lineas.get(i).split(" ");
                int k = Integer.parseInt(parts[0]);
                int r = Integer.parseInt(parts[1]);
                int l = Integer.parseInt(parts[2]);

                // solo me interezan las relaciones de x o y, me aseguro que la key de amistades sea siempre x o y
                if( k==x || k==y ){
                    amistades.get(k).put(r, l);
                } else if( r==x || r==y){
                    amistades.get(r).put(k, l);
                }
            }

            int aliadosX = 0, aliadosY = 0;
            for (int i = 1; i <= n; i++) {
                if (i == x || i == y) continue;

                int lazoX = amistades.get(x).getOrDefault(i, -1);
                int lazoY = amistades.get(y).getOrDefault(i, -1);

                if (lazoX > lazoY) {
                    aliadosX++;
                } else if (lazoY > lazoX) {
                    aliadosY++;
                }
            }
            String output = aliadosX + " " + aliadosY;
            Files.write(Paths.get("ALIADOS.OUT"), output.getBytes());
            return output;
        } catch (IOException e) {
            e.printStackTrace();
            return inputFilename;
        }
    }

    /*
     * solucion de O(m)
     * */
    public static String resolver1(String inputFilename){
        try {
            List<String> lineas = Files.readAllLines(Paths.get(inputFilename));
            String[] primeraLinea = lineas.get(0).split(" ");
            int n = Integer.parseInt(primeraLinea[0]); // cantidad de vecinos
            int m = Integer.parseInt(primeraLinea[1]); // cantidad de lazos
            int x = Integer.parseInt(primeraLinea[2]); // primer oponente
            int y = Integer.parseInt(primeraLinea[3]); // segundo oponente

            // Aca en realidad solo voy a poner dos elementos en este mapa, los lazos de amistad para x e y,
            // pero se usa un mapa para diferenciar los mapas de lazos con la key, en vez de utilizar dos mapas por separados
            // por que de esa manera deberia hacer mas preguntas para saber que mapa utilizar en cada caso
            Map<Integer, Map<Integer, Integer>> amistades = new HashMap<>();
            amistades.put(x, new HashMap<>());
            amistades.put(y, new HashMap<>());

            // Leo las m relaciones de amistad
            for (int i = 1; i <= m; i++) {
                String[] parts = lineas.get(i).split(" ");
                int k = Integer.parseInt(parts[0]);
                int r = Integer.parseInt(parts[1]);
                int l = Integer.parseInt(parts[2]);

                if( k == x){
                    defineAmistad(x, y, amistades, r, l);
                } else if ( k == y){
                    defineAmistad(y, x, amistades, r, l);
                } else if (r == x){
                    defineAmistad(x, y, amistades, k, l);
                } else if (r == y){
                    defineAmistad(y, x, amistades, k, l);
                }
            }
            int aliadosX = amistades.get(x).size();
            int aliadosY = amistades.get(y).size();
            String output = aliadosX + " " + aliadosY;
            Files.write(Paths.get("ALIADOS.OUT"), output.getBytes());
            return output;
        } catch (IOException e) {
            e.printStackTrace();
            return inputFilename;
        }
    }

    /**
     * Define de quien es aliado el vecino comparando el lazo con el otro oponente y actualiza las amitades
     */
    private static void defineAmistad(int oponenteActual, int oponenteOtro, Map<Integer, Map<Integer, Integer>> amistades, int vecino, int amistadActual) {
        int amistadOtro = amistades.get(oponenteOtro).getOrDefault(vecino, -1);
        if( amistadActual > amistadOtro){
            amistades.get(oponenteOtro).remove(vecino);
            amistades.get(oponenteActual).put(vecino,amistadActual);
        } else if ( amistadActual == amistadOtro){
            amistades.get(oponenteOtro).remove(vecino);
        }
    }
}
