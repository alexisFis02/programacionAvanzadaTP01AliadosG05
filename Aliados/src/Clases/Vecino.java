package Clases;
import java.util.*;

public class Vecino {
	private int id;
	private Map<Vecino, Integer> relaciones;
	
	public Vecino (int id)
	{
		this.id = id;
		this.relaciones = new HashMap<>();
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void agregarRelacion(Vecino vecino, int fuerzaRelacion)
	{
		this.relaciones.put(vecino,fuerzaRelacion);
	}
	
	public int getFuerzaRelacion(Vecino vecino)
	{
		return this.relaciones.getOrDefault(vecino, 0); // Caso en que no haya relacion devuelvo 0
	}
	
	public Map<Vecino,Integer> getRelaciones()
	{
		return this.relaciones;
	}
}
