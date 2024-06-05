package org.grupo05.aliados.logica;

import org.grupo05.aliados.archivo.Archivo;
import org.grupo05.aliados.data.Amistad;
import org.grupo05.aliados.data.Datos;

public class Resolucion2 implements Resolucion {
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
			/*
			if( k==x || k==y ){
                amistades.get(k).put(r, l);
            } else if( r==x || r==y){
                amistades.get(r).put(k, l);
            }*/
		}
	}
	/*
	private void resolverAmistades(int cantidad_lazos) {
		int aliadosX = 0, aliadosY = 0;
        for (int i = 1; i <= cantidad_lazos; i++) {
            if (i == x || i == y) continue;

            int lazoX = amistades.get(x).getOrDefault(i, -1);
            int lazoY = amistades.get(y).getOrDefault(i, -1);

            if (lazoX > lazoY) {
                aliadosX++;
            } else if (lazoY > lazoX) {
                aliadosY++;
            }
        }
	}
	*/
}
