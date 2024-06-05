package org.grupo05.aliados.data;

public class Amistad {
	private int vecinoA, vecinoB, fuerzaAmistad;

	public Amistad(int vecinoA, int vecinoB, int fuerzaAmistad) {
		super();
		this.vecinoA = vecinoA;
		this.vecinoB = vecinoB;
		this.fuerzaAmistad = fuerzaAmistad;
	}

	public int getVecinoA() {
		return vecinoA;
	}

	public void setVecinoA(int vecinoA) {
		this.vecinoA = vecinoA;
	}

	public int getVecinoB() {
		return vecinoB;
	}

	public void setVecinoB(int vecinoB) {
		this.vecinoB = vecinoB;
	}

	public int getFuerzaAmistad() {
		return fuerzaAmistad;
	}

	public void setFuerzaAmistad(int fuerzaAmistad) {
		this.fuerzaAmistad = fuerzaAmistad;
	}
	
	
}
