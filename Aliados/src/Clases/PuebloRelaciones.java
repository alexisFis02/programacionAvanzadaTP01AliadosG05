package Clases;
import java.util.*;

public class PuebloRelaciones {
	private Map<Integer,Vecino> vecinos; // Uso un mapa para almacenar a todos los vecinos
	
	public PuebloRelaciones()
	{
		this.vecinos = new HashMap<>();
	}
	
	public void agregarVecino(int id)
	{
		this.vecinos.put(id, new Vecino(id)); //Aca se crea a los nuevos vecinos
	}
	
	public void agregarRelacion(int idVecino1, int idVecino2, int fuerzaRelacion)
	{
		Vecino vecino1 = this.vecinos.get(idVecino1);
		Vecino vecino2 = this.vecinos.get(idVecino2);
		vecino1.agregarRelacion(vecino2, fuerzaRelacion);
		vecino2.agregarRelacion(vecino1, fuerzaRelacion);
	}
	
	public Vecino getVecino(int idVecino)
	{
		return this.vecinos.get(idVecino);
	}
	
	
}