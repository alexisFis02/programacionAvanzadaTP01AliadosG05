package org.grupo05.aliados;

import org.grupo05.aliados.logica.Resolucion;
import org.grupo05.aliados.logica.Resolucion1;

public class Main {
    public static void main(String[] args) {
    	Resolucion rs = new Resolucion1();

        String out = rs.resolver("ALIADOS.IN", "ALIADOS.OUT");
        
        System.out.println("Archivo out generado: ");
        System.out.println(out);
    }
}
