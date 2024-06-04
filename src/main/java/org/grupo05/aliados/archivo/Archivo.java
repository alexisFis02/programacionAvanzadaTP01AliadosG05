package org.grupo05.aliados.archivo;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.Scanner;

import org.grupo05.aliados.data.Datos;

public class Archivo {

	public static Datos abrirArchivo(String inputfilename) {
		Datos data = null;
		File f = new File(inputfilename);
		try {
			data = Archivo.procesarArchivo(f);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("LOG: error en abrir archivo, " + e.getMessage());
		}
		
		return data;
	}
	
	private static Datos procesarArchivo(File file) throws Exception{
		Scanner scan = new Scanner(file);
		
		int cant_vecinos = scan.nextInt(); // cantidad de vecinos
        int cant_lazos = scan.nextInt(); // cantidad de lazos
        int x = scan.nextInt(); // primer oponente
        int y = scan.nextInt(); // segundo oponente

        Datos data = new Datos(cant_vecinos, cant_lazos, x, y);
        
        data.iniciarMapas();
        
        for (int i = 0; i < cant_lazos; i++) {
            int k = scan.nextInt();
            int r = scan.nextInt();
            int l = scan.nextInt();
            
            

            if( k == x){
                defineAmistad(x, y, data.getAmistades(), r, l);
            } else if ( k == y){
                defineAmistad(y, x, data.getAmistades(), r, l);
            } else if (r == x){
                defineAmistad(x, y, data.getAmistades(), k, l);
            } else if (r == y){
                defineAmistad(y, x, data.getAmistades(), k, l);
            }
        }
        
        
        
        
        scan.close();
        return data;
	}
	
	private static void defineAmistad(int oponenteActual, int oponenteOtro, Map<Integer, Map<Integer, Integer>> amistades, int vecino, int amistadActual) {
        int amistadOtro = amistades.get(oponenteOtro).getOrDefault(vecino, -1);
        if( amistadActual > amistadOtro){
            amistades.get(oponenteOtro).remove(vecino);
            amistades.get(oponenteActual).put(vecino,amistadActual);
        } else if ( amistadActual == amistadOtro){
            amistades.get(oponenteOtro).remove(vecino);
        }
    }
	
	public static void crearArchivoSalida(String outfilename, Datos data) {
		File file = new File(outfilename);
		try {
			file.createNewFile();
			
			FileWriter fw = new FileWriter(file);
			fw.write(data.getAliadosX() + " " + data.getAliadosY());
			
			fw.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("LOG: Error en crearArchivoSalida, " + e.getMessage());
		}
	}
}
