package org.grupo05.aliados.archivo;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.Scanner;

import org.grupo05.aliados.data.Amistad;
import org.grupo05.aliados.data.Datos;

public class Archivo {
	private String nombre_archivo;
	private File f;
	private Scanner scan;
	
	public Archivo(String inputfile) {
		nombre_archivo = inputfile;
	}
	
	public void abrirArchivo() throws Exception{
		File file = new File(this.nombre_archivo);

		if(file.exists() && file.canRead()) {
			this.f = file;
			scan = new Scanner(file);
		}else
			throw new Exception("Archivo innacesible o no creado");
			

	}
	
	public Datos getDatos() {
		int cant_vecinos = scan.nextInt(); // cantidad de vecinos
        int cant_lazos = scan.nextInt(); // cantidad de lazos
        int x = scan.nextInt(); // primer oponente
        int y = scan.nextInt(); // segundo oponente

        Datos data = new Datos(cant_vecinos, cant_lazos, x, y);
        
        return data;
	}
	
	public Amistad getSiguienteAmistad() {
		int k = scan.nextInt(); //primer vecino
        int r = scan.nextInt();	//segundo vecino
        int l = scan.nextInt(); //fuerza amistad
        
        return new Amistad(k, r, l);
	}
	
	public void cerrarArchivo() {
		this.scan.close();
	}
	
	public static void crearArchivoSalida(String outfilename, String output) {
		File file = new File(outfilename);
		try {
			file.createNewFile();
			
			FileWriter fw = new FileWriter(file);
			fw.write(output);
			
			fw.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("LOG: Error en crearArchivoSalida, " + e.getMessage());
		}
	}
}
