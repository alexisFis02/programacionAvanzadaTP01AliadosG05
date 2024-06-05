package org.grupo05.aliados.data;

import java.util.HashMap;
import java.util.Map;

public class Datos {
	int cant_vecinos;
	int cant_lazos;
	int x,y;
	Map<Integer, Map<Integer, Integer>> amistades = new HashMap<>();
	
	int aliadosX;
	int aliadosY;
	
	public Datos() {
		super();
	}
	public Datos(int cant_vecinos, int cant_lazos, int x, int y) {
		super();
		this.cant_vecinos = cant_vecinos;
		this.cant_lazos = cant_lazos;
		this.x = x;
		this.y = y;
	}
	public int getCant_vecinos() {
		return cant_vecinos;
	}
	public void setCant_vecinos(int cant_vecinos) {
		this.cant_vecinos = cant_vecinos;
	}
	public int getCant_lazos() {
		return cant_lazos;
	}
	public void setCant_lazos(int cant_lazos) {
		this.cant_lazos = cant_lazos;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Map<Integer, Map<Integer, Integer>> getAmistades() {
		return amistades;
	}
	public void setAmistades(Map<Integer, Map<Integer, Integer>> amistades) {
		this.amistades = amistades;
	}
	
	
	
	public void iniciarMapas() {
		amistades.put(x, new HashMap<>());
        amistades.put(y, new HashMap<>());
	}
	
	public void decidirLazos() {
		aliadosX = amistades.get(x).size();
        aliadosY = amistades.get(y).size();
	}
	
	public void insertarAmistad(Amistad amistad) {
		int k = amistad.getVecinoA(),
				r = amistad.getVecinoB(),
				l = amistad.getFuerzaAmistad();
		
		if( k == x){
            defineAmistad(x, y, r, l);
        } else if ( k == y){
            defineAmistad(y, x, r, l);
        } else if (r == x){
            defineAmistad(x, y, k, l);
        } else if (r == y){
            defineAmistad(y, x, k, l);
        }
	}
	
	private void defineAmistad(int oponenteActual, int oponenteOtro, int vecino, int amistadActual) {
        int amistadOtro = amistades.get(oponenteOtro).getOrDefault(vecino, -1);
        if( amistadActual > amistadOtro){
            amistades.get(oponenteOtro).remove(vecino);
            amistades.get(oponenteActual).put(vecino,amistadActual);
        } else if ( amistadActual == amistadOtro){
            amistades.get(oponenteOtro).remove(vecino);
        }
    }
	
	public String obtenerResultado() {
		return aliadosX + " " + aliadosY;
	}
}
