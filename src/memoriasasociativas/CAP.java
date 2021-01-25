/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriasasociativas;

import data.Patron;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author carli
 */
public class CAP {
    public ArrayList<PatronesBinarios> iniciales;
    HashMap<Integer,String> entrenador = new HashMap();
    public PatronesBinarios medio;
    public double[][] C;
    public final int E = 1;
    
    public CAP(ArrayList<PatronesBinarios> iniciales){
        this.iniciales = (ArrayList<PatronesBinarios>) iniciales.clone();
       obtenerClasesExistentes();
    }
    
    public void aprendizaje(){   
        obtenerVectorMedio(entrenador.size());  
        obtenerClasificadorC();
    }
    
    public void recuperacion(PatronesBinarios instancia){
         double[] vector = new double[entrenador.size()];
         for(int i = 0; i< entrenador.size(); i++){
             for(int j = 0; j<iniciales.get(0).getVectorN().length;j++){
                 vector[i] += (C[i][j] * instancia.getVectorT()[j]);
             }
         }
         instancia.setClaseResultante(obtenerClaseResultante(vector));
    }

    private void imprimirMatriz() {
        for(int i = 0; i<entrenador.size();i++){
            for(int j= 0; j<iniciales.get(0).getVectorN().length; j++){
                System.out.print(""+C[i][j]+"\t");
            }
            System.out.println();
        }
    }

    private void obtenerClasesExistentes() {
        int con = 1;
           double[] vector = new double[this.iniciales.get(0).getVectorN().length];
           for(int x=0; x<this.iniciales.size();x++){
               if(!entrenador.containsValue(this.iniciales.get(x).getClase())){
                   entrenador.put(con, this.iniciales.get(x).getClase());
                   con++;
               } 
           }
             
    }

    public ArrayList<PatronesBinarios> getIniciales() {
        return iniciales;
    }

    private void obtenerVectorMedio(int n) {
        double[] vector = new double[iniciales.get(0).getVectorN().length];
        for(int x=0; x<iniciales.size();x++){
            for(int y=0; y<vector.length;y++){
                vector[y]+=iniciales.get(x).getVectorN()[y]; 
            }        
        }
        for(int j = 0; j<vector.length; j++){
                    vector[j] = vector[j]/iniciales.size(); 
        }
        medio = new PatronesBinarios(vector,"");
        realizarTraslacion();
    }

    private void realizarTraslacion() {
        double[] vectorTrasladado = new double[iniciales.get(0).getVectorN().length]; 
        for(int x=0; x<iniciales.size();x++){
            for(int y=0; y<iniciales.get(0).getVectorN().length;y++){
                vectorTrasladado[y] = iniciales.get(x).getVectorN()[y] - medio.getVectorN()[y];
                 
            }   
            iniciales.get(x).setVectorT(vectorTrasladado);
            vectorTrasladado = new double[iniciales.get(0).getVectorN().length]; 
        }
    }

    private void obtenerClasificadorC() {
        C = new double[entrenador.size()][iniciales.get(0).getVectorN().length];
        int fila = 0;
        double[] vector = new double[iniciales.get(0).getVectorN().length];
        Iterator<Integer> iterador = entrenador.keySet().iterator();
        while(iterador.hasNext()){
               Integer llave = iterador.next();
               for(int x=0; x<this.iniciales.size();x++){
                   if(entrenador.get(llave).equals(this.iniciales.get(x).getClase())){
                       for(int y=0; y<vector.length;y++){
                        vector[y]+=iniciales.get(x).getVectorT()[y];   
                     }
                   }
               }
                for(int y=0; y<vector.length;y++){
                        C[fila][y] = vector[y];   
                }
                fila++;
                vector = new double[iniciales.get(0).getVectorN().length];
           } 
        imprimirMatriz();
    }

    private String obtenerClaseResultante(double[] vector) {
        String cr = "";
        double mayor = vector[0];
        double d = 0; 
        int n = 0;
        int[] z= new int[vector.length];
        for(int i = 1; i<vector.length; i++){
            double mayoraux = vector[i];
            if(mayoraux>mayor){
                mayor = mayoraux;
                n = i;
            }
        }  
        cr = entrenador.get(n+1);
        return cr;
    }
    public double calculaEficiencia(ArrayList<PatronesBinarios> instancias) {
        double n=0;
        double elementos = 0;
        double eficacia=0;
        for(int x=0; x<instancias.size();x++){
            if(instancias.get(x).getClase().equals(instancias.get(x).getClaseResultante())){
               n+=1; 
            }
        }
        System.out.println("Correctos: "+n);
        System.out.println("Incorrectos: "+(instancias.size()-n));
        eficacia = (100*n)/(instancias.size());
        return eficacia;
    }
    
}

