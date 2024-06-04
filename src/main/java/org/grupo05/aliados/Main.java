package org.grupo05.aliados;

import org.grupo05.aliados.logica.Resolucion1;

public class Main {
    public static void main(String[] args) {
    	Resolucion1 rs = new Resolucion1();
        try{
            //String solucion = Aliados.resolver1("ALIADOS.IN");
            
            rs.resolver("ALIADOS.IN", "ALIADOS.OUT");
            
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
