package org.grupo05.aliados;

public class Main {
    public static void main(String[] args) {
        try{
            String solucion = Aliados.resolver1("ALIADOS.IN");
            System.out.println(solucion);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
