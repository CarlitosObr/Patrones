/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rp20201a;

import clasificadores.Bayes;
import clasificadores.KNN;
import clasificadores.MinimaDistancia;
import clasificadoresNoSupervisados.cmeans;
import clusterimagenes.generarPatrones;
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
        
        generarPatrones nuevo = new generarPatrones();
        nuevo.abrir();
        nuevo.clusterizar(10);
       // double[] n = {6,180,12};
      //  ArrayList<Patron> patrones = LeerDatos.tokenizarDataSet();
      //  cmeans cm = new cmeans(3);
      //  cm.entrenar(patrones);
       // cm.clasificar();
       
      //  cm.getRepresentativos();
       
        //ArrayList<Patron> patrones = new ArrayList();
      //  ArrayList<Patron> clasificadoMD;
      //  ArrayList<Patron> clasificadoKNN;
       // Matriz nueva;
       // Matriz nueva2;
      //  System.out.println(patrones.size());
        //MinimaDistancia mn = new MinimaDistancia();
       // for(int i = 0; i<patrones.size(); i++){
       //     System.out.println("Patrón "+i+" Clase Resultante: "+patrones.get(i).getClaseResultante());
       // }
        
        //Bayes b = new Bayes();
        //KNN knn = new KNN(10);
      //  b.entrenar(patrones);
        //b.entrenar(patrones);
        //knn.entrenar(patrones);
        /*for (int i = 0; i<b.desviacion.size();i++){
            System.out.println(b.desviacion.get(i).getClase());
           for(int j=0; j<b.desviacion.get(i).getVectorC().length;j++){
               System.out.println(b.desviacion.get(i).getVectorC()[j]);
           }
           
        }*/
        /*for (int i = 0; i<b.varianza.size();i++){
            System.out.println(b.varianza.get(i).getClase());
           for(int j=0; j<b.varianza.get(i).getVectorC().length;j++){
               System.out.println(b.varianza.get(i).getVectorC()[j]);
           }
           
        }
        for (int i = 0; i<b.desviacion.size();i++){
            System.out.println(b.desviacion.get(i).getClase());
           for(int j=0; j<b.desviacion.get(i).getVectorC().length;j++){
               System.out.println(b.desviacion.get(i).getVectorC()[j]);
           }
           
        }
        b.clasificar(patrones, new Patron(n,""));
        
        for (int i = 0; i<b.dist.size();i++){
            System.out.println(b.dist.get(i).getClase());
           for(int j=0; j<b.dist.get(i).getVectorC().length;j++){
               System.out.println(b.dist.get(i).getVectorC()[j]);
           }
           
        }
        
        System.out.println(b.evidencia);
        for (int i = 0; i<b.posteriori.size();i++){
            System.out.println(b.posteriori.get(i).getClase());
           for(int j=0; j<b.posteriori.get(i).getVectorC().length;j++){
               System.out.println(b.posteriori.get(i).getVectorC()[j]);
           }
          
        }*/
       
        //KNN knn = new KNN(5);
        //knn.entrenar(patrones);
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
        //System.out.println(b.evidencia);
      //  for(int i=0;i<patrones.size();i++){
      //     b.clasificar(patrones, patrones.get(i));
       // }
       /* for(int i=0;i<patrones.size();i++){
           mn.clasificar(patrones, patrones.get(i));
        }*/
        /*for(int x = 0; x < 15; x++){
              knn.clasificar(patrones, patrones.get(x));
        }
        for(int x = 50; x < 65; x++){
              knn.clasificar(patrones, patrones.get(x));
        }
        for(int x = 100; x < 115; x++){
              knn.clasificar(patrones, patrones.get(x));
        }*/
        
   //      clasificadoKNN = b.getClasificados();
       // clasificadoMD = mn.getClasificados();
       /* for(int x = 0; x < patrones.size(); x++){
              knn.clasificar(patrones, patrones.get(x));
        }
        clasificadoKNN = knn.getClasificado();*/
       
        
        /*for(int x = 0; x < clasificadoKNN.size(); x++){
             System.out.println("Indice: "+x+" Clase: "+clasificadoKNN.get(x).getClase()+" ClaseResultante: "+clasificadoKNN.get(x).getClaseResultante());
        }*/
      //  System.out.println("La eficiencia con la que se clasifica es de "+b.calculaEficiencia(clasificadoKNN));
        //System.out.println("La eficiencia con la que se clasifica es de "+b.calculaEficiencia(clasificadoMD));
        //System.out.println("La eficiencia con la que se clasifica es de "+knn.calculaEficiencia(clasificadoKNN));
        
       //  nueva = new Matriz(clasificadoKNN);
        // nueva.toString();
        /// nueva.crearMatriz(b.priori);
         
    }
    
}
