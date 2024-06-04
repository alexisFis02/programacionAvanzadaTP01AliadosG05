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
	
	
	
	public int getAliadosX() {
		return aliadosX;
	}
	public void setAliadosX(int aliadosX) {
		this.aliadosX = aliadosX;
	}
	public int getAliadosY() {
		return aliadosY;
	}
	public void setAliadosY(int aliadosY) {
		this.aliadosY = aliadosY;
	}
	public void iniciarMapas() {
		amistades.put(x, new HashMap<>());
        amistades.put(y, new HashMap<>());
	}
	
	public void decidirLazos() {
		aliadosX = amistades.get(x).size();
        aliadosY = amistades.get(y).size();
	}
}
