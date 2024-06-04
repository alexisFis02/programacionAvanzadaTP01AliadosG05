package org.grupo05.aliados.data;

public class Datos {
	int cant_vecinos;
	int cant_lazos;
	int x,y;
	
	
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
	
	
}
