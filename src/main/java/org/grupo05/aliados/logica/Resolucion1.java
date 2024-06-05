package org.grupo05.aliados.logica;

import org.grupo05.aliados.archivo.Archivo;
import org.grupo05.aliados.data.Amistad;
import org.grupo05.aliados.data.Datos;

public class Resolucion1 implements Resolucion{

	public String resolver(String inputfilename, String outputfilename) {
		String output = null;
		try {
			
			Archivo archivo = new Archivo(inputfilename);
			
			archivo.abrirArchivo();
			
			Datos data = archivo.getDatos();

			data.iniciarMapas();
			
			leerAmistades(archivo, data, data.getCant_lazos());
			
			data.decidirLazos();
            
			archivo.cerrarArchivo();
			
			output = data.obtenerResultado();
			
            Archivo.crearArchivoSalida(outputfilename, output);
			
            
		}catch(Exception e){
			e.printStackTrace();

		}
		return output;
	}
	
	private void leerAmistades(Archivo archivo, Datos data, int cantidad_lineas) {
		Amistad amistad;
		for(int i = 0; i < cantidad_lineas; i++) {
			amistad = archivo.getSiguienteAmistad();
			data.insertarAmistad(amistad);
		}
	}
}
