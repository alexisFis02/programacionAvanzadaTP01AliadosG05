package gestorAliados;
import Clases.*;
import java.io.*;
import java.util.*;

public class GestorAliados {
	private PuebloRelaciones puebloRelaciones;
	private int liderX;
	private int liderY;
	
	public GestorAliados(String inputFile) throws Exception
	{
		this.readData(inputFile);
	}
	
	private void readData(String inputFile) throws Exception
	{
		Scanner scanner = null;
		try{
			
			String filePath = "Input/" + inputFile;
			scanner = new Scanner (new File(filePath));
			
			// n = cantidad de vecinos
			int n = scanner.nextInt(); 
			if(!validarRango(n,2,200))
			{
				System.out.println("La cantidad de vecinos no esta dentro del rango correcto");
				return;
			}
			
			// m = cantidad de relaciones
			int m = scanner.nextInt(); 
			if(!validarRango(m,0,5000))
			{
				System.out.println("La cantidad de relaciones no esta dentro del rango correcto");
				return;
			}
			
			// Oponente 1
			this.liderX = scanner.nextInt(); 
			if(!(liderX >= 1))
			{
				System.out.println("El lider X tiene un valor menor a 1");
				return;
			}
			
			 // Oponente 2
			this.liderY = scanner.nextInt();
			if(!(liderY <= n))
			{
				System.out.println("El lider y tiene un valor mayor a la cantidad total de vecinos(n)");
				return;
			}
			
			
			this.puebloRelaciones = new PuebloRelaciones();
			
			// Leo el archivo y voy cargando los vecinos y sus relaciones
			for(int i = 0; i < m; i++)
			{
				int k = scanner.nextInt(); // Vecino 1
				int r = scanner.nextInt(); // Vecino 2
				int L = scanner.nextInt(); // Fuerza Relacion entre los 2 vecinos
				if(!validarRango(L,1,100))
				{
					System.out.println("La fuerza de relacion no esta dentro del rango correcto,");
					System.out.println("En la relacion: " + i + "Vecinos" + k + " " + r);
					return;
				}
				
				if(puebloRelaciones.getVecino(k) == null) //Reviso que el vecino no haya sido cargado anteriormente
					puebloRelaciones.agregarVecino(k);
				if(puebloRelaciones.getVecino(r) == null)
					puebloRelaciones.agregarVecino(r);
				
				puebloRelaciones.agregarRelacion(k, r, L); // Cargo la relacion entre los vecinos
			}
		}catch(Exception e)
		{
			System.out.println("Error en el archivo de entrada: " + inputFile);
			e.printStackTrace();
		}finally
		{
			scanner.close();
		}
	}
	
	public void getAliados(String outputFile) throws Exception
	{
		if(puebloRelaciones.getVecino(liderX) == null) {
			System.out.println("El primer lider no corresponde a un vecino");
			return;
		}
		if(puebloRelaciones.getVecino(liderY) == null) {
			System.out.println("El primer lider no corresponde a un vecino");
			return;
		}
		Vecino leaderX = puebloRelaciones.getVecino(liderX);
		Vecino leaderY = puebloRelaciones.getVecino(liderY);
		
		int aliadosX = countAliados(leaderX,leaderY);
		int aliadosY = countAliados(leaderY,leaderX);
		
		try {
			String filePath = "Output/" + outputFile;
			PrintWriter writer = new PrintWriter(new File(filePath));
			writer.print(aliadosX + " " + aliadosY);
			writer.close();
			System.out.println("Se creo un nuevo archivo: " + outputFile + " exitosamente en la carpeta de Outputs");
			System.out.println("Aliados lider X: " + aliadosX);
			System.out.println("Aliados lider Y: " + aliadosY);
		}catch (Exception e)
		{
			System.out.println("Error al escribir el nuevo archivo");
			e.printStackTrace();
		}

	}
	
	private int countAliados(Vecino lider, Vecino oponente)
	{
		int aliados = 0;
		Queue<Vecino> vecinos = new LinkedList<>(); // Cola de Vecinos
		Set<Vecino> visitados = new HashSet<>(); // Set de Vecinos visitados
		
		vecinos.offer(lider);
		visitados.add(lider);
		
		while(!vecinos.isEmpty())
		{
			Vecino actual = vecinos.poll(); //obtengo el vecino actual de la cola
			Map<Vecino,Integer> relaciones = actual.getRelaciones(); // Del vecino obtengo sus relaciones
			
			for(Map.Entry<Vecino,Integer> relacion : relaciones.entrySet())
			{
				Vecino vecino = relacion.getKey();
				if(vecino != lider && vecino != oponente && !visitados.contains(vecino)) // Reviso que el vecino actual no sea ninguno de los lideres y no haya sido visitado anteriormente
				{
					if(lider.getFuerzaRelacion(vecino) > oponente.getFuerzaRelacion(vecino))
						aliados++;
				}
				if(!visitados.contains(vecino)) {
					vecinos.offer(vecino);
					visitados.add(vecino);
				}
			}
		}
		return aliados;
	}
		
	public boolean validarRango(int valor, int min, int max)
	{
		if(valor >= min && valor <= max)
			return true;
		return false;
	}
	
}