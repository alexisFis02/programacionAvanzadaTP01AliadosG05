package org.grupo05.aliados.archivo;

import java.io.File;
import java.util.Scanner;

import org.grupo05.aliados.data.Datos;

public class Archivo {

	public static Datos abrirArchivo(String inputfilename) {
		File f = new File(inputfilename);
		try {
			Archivo.procesarArchivo(f);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("LOG: error en abrir archivo, " + e.getMessage());
		}
		
		return new Datos();
	}
	
	private static void procesarArchivo(File file) throws Exception{
		Scanner scan = new Scanner(file);
		
		int cant_vecinos = scan.nextInt(); // cantidad de vecinos
        int cant_lazos = scan.nextInt(); // cantidad de lazos
        int x = scan.nextInt(); // primer oponente
        int y = scan.nextInt(); // segundo oponente
		
        
	}
}
