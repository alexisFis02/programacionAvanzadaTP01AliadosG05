package org.grupo05.aliados.archivo;

import java.io.File;
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
		
        scan.close();
        return new Datos(cant_vecinos, cant_lazos, x, y);
	}
}
