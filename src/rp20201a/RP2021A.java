/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rp20201a;

import clasificadores.KNN;
import clasificadores.MinimaDistancia;
import data.Patron;
import data.LeerDatos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author working
 */
public class RP2021A {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        /**distancias 
        (2.4,5.6,3)   (5,6,3) d1:
        (1,2) (6.7,7.8)  d2:
        * 5.1,3.5,1.4,0.2
        **/ 
        ArrayList<Patron> patrones = LeerDatos.tokenizarDataSet();
       
        System.out.println(patrones.size());
        MinimaDistancia mn = new MinimaDistancia();
        KNN knn = new KNN(5);
        knn.entrenar(patrones);
        //System.out.println(mn.representativos.size());
        //System.out.println(patrones.size()-mn.representativos.size());
        /*for(int x = 0; x < patrones.size() - mn.representativos.size(); x++){
             mn.clasificar(patrones, patrones.get(x));
        }
        for(int x = 0; x < patrones.size() - mn.representativos.size(); x++){
             System.out.println("Clase: "+patrones.get(x).getClase()+" Clase resultante: "+patrones.get(x).getClaseResultante());
        }
        System.out.println("La eficacia con la que se clasifica es de "+mn.calculaEficiencia(patrones));*/
        
        //for(int x = 0; x < patrones.size(); x++){
             
        for(int x = 0; x < patrones.size(); x++){
              knn.clasificar(patrones, patrones.get(x));
        }
      
        for(int x = 0; x < patrones.size(); x++){
             System.out.println("Indice: "+x+" Clase: "+patrones.get(x).getClase()+" ClaseResultante: "+patrones.get(x).getClaseResultante());
        }
        System.out.println("La eficiencia con la que se clasifica es de "+knn.calculaEficiencia(patrones));
        
        /*for(int x = 0; x < patrones.size(); x++){
             System.out.println(patrones.get(x).getClase());
        }*/
        /*Patron p1 = new Patron("","",new double[]{2,2,2,2},1);
        mn.clasificar(patrones, p1);
        System.out.println(patrones.size());
        System.out.println("Pertenece a la clase: "+p1.getClase());
         System.out.println(patrones.get(0).calcularDistancia(p1));
         System.out.println(patrones.get(1).calcularDistancia(p1));
         System.out.println(patrones.get(2).calcularDistancia(p1));
        /*Patron p1 = new Patron("","",new double[]{2.4,3.3,5.6,7.8},1);
        System.out.println("Los resultados de las distancias con el primer punto son: ");
        for(int x = 0; x < patrones.size(); x++){
            System.out.println(x+1+": "+patrones.get(x).calcularDistancia(p1)+"Pertenece a: "+patrones.get(x).getClaseResultante());
        }
        Patron p2 = new Patron("", "", new double[]{2,3.7,5.6,8.8},2);
        System.out.println("Los resultados de las distancias con el segundo punto son: ");
        for(int x = 0; x < patrones.size(); x++){
            System.out.println(x+1+": "+patrones.get(x).calcularDistancia(p2));
        }*/
        // TODO: TOKENIZADOR PARA PODER SEPARAR POR COMAS Y GENERAR UN COLECCION DE PATRONES
    }
    
}
