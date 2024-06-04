package org.grupo05.aliados.logica;

import org.grupo05.aliados.archivo.Archivo;
import org.grupo05.aliados.data.Datos;

public class Resolucion1 {

	public void resolver(String inputfilename, String outputfilename) {
		try {
			Datos data = Archivo.abrirArchivo(inputfilename);

			data.decidirLazos();
            
            Archivo.crearArchivoSalida(outputfilename, data);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
