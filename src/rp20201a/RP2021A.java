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
import data.Matriz;
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
        ArrayList<Patron> clasificadoMD;
        ArrayList<Patron> clasificadoKNN;
        Matriz nueva;
        System.out.println(patrones.size());
        MinimaDistancia mn = new MinimaDistancia();
        KNN knn = new KNN(5);
        knn.entrenar(patrones);
        //mn.entrenar(patrones);
        /*for(int i = 0; i<mn.getRepresentativos().size();i++){
            System.out.println(mn.getRepresentativos().get(i).getClase());
        }
        for(int i = 0; i<mn.getRepresentativos().size();i++){
           for(int j = 0 ; j<mn.getRepresentativos().get(i).getVectorC().length;j++){
               System.out.println(j+1+". "+mn.getRepresentativos().get(i).getVectorC()[j]);
           }  
        }  */  
        /*for(int x = 0; x < 15; x++){
              knn.clasificar(patrones, patrones.get(x));
        }
        for(int x = 30; x < 45; x++){
              knn.clasificar(patrones, patrones.get(x));
        }
        for(int x = 63; x < 78; x++){
              knn.clasificar(patrones, patrones.get(x));
        }*/
        for(int i=0;i<patrones.size();i++){
            knn.clasificar(patrones, patrones.get(i));
        }
        /*for(int x = 0; x < 15; x++){
              knn.clasificar(patrones, patrones.get(x));
        }
        for(int x = 50; x < 65; x++){
              knn.clasificar(patrones, patrones.get(x));
        }
        for(int x = 100; x < 115; x++){
              knn.clasificar(patrones, patrones.get(x));
        }*/
         clasificadoKNN = knn.getClasificado();
        //clasificadoMD = mn.getClasificados();
       /* for(int x = 0; x < patrones.size(); x++){
              knn.clasificar(patrones, patrones.get(x));
        }
        clasificadoKNN = knn.getClasificado();*/
       
        
        for(int x = 0; x < clasificadoKNN.size(); x++){
             System.out.println("Indice: "+x+" Clase: "+clasificadoKNN.get(x).getClase()+" ClaseResultante: "+clasificadoKNN.get(x).getClaseResultante());
        }
        System.out.println("La eficiencia con la que se clasifica es de "+knn.calculaEficiencia(clasificadoKNN));
        //System.out.println("La eficiencia con la que se clasifica es de "+knn.calculaEficiencia(clasificadoKNN));
        
         nueva = new Matriz(clasificadoKNN);
         nueva.toString();
         nueva.crearMatriz(knn.getRepresentativos());
    }
    
}
