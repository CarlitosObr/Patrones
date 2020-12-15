/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clusterimagenes;

import data.Patron;
import interfaces.ClasificadorNoSupervisado;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author carli
 */
public class cmeansImagen{
    private int c;
    private ArrayList<PatronImagen> representativos = new ArrayList();
    private ArrayList<PatronImagen> patrones;   
            
    public cmeansImagen(int c) {
        this.c = c;
    }
    
    
    public void entrenar(ArrayList<PatronImagen> instancias) {
        this.patrones = (ArrayList<PatronImagen>) instancias.clone();
    }

    public void clasificar() {
       ArrayList<PatronImagen> comparar;
       ArrayList<PatronImagen> comparar2;
       double distancia = 1;
       double comparacion;
       double com;
       int contador=0;
       generarRepresentativosIn();
       
       asignarClase(this.representativos);
       while(distancia!=0){
                System.out.println(contador++);
                comparacion=0;
                com = 0;
                comparar = this.representativos;
                for(int i=0; i<this.representativos.size(); i++){
                    for(int j = 0; j<this.representativos.get(i).getVectorC().length; j++){
                        comparacion += comparar.get(i).getVectorC()[j];
                    }  
                }
                asignarClase(comparar);
                comparar2 = encontrarNuevoRepresentativo();
                for(int i=0; i<comparar2.size(); i++){
                    for(int j = 0; j<comparar2.get(i).getVectorC().length; j++){
                        com += comparar2.get(i).getVectorC()[j];
                    }  
                }
                
                this.representativos = (ArrayList<PatronImagen>) comparar2.clone();
                distancia = Math.abs(comparacion - com);   
                
        }
    }

    private void generarRepresentativosIn() {
        
        Random ran = new Random();
        int aux = ran.nextInt(this.patrones.size());
         //this.representativos.add(patrones.get(aux));
        while(this.c!=0){
            if(!buscaPatron(patrones.get(aux))){
                aux = ran.nextInt(this.patrones.size());
                this.representativos.add(new PatronImagen(0,0,patrones.get(aux).getVectorC()));
                this.c--;
            }
        }
        
    }

    public ArrayList<PatronImagen> getRepresentativos() {
        return representativos;
    }

    private ArrayList<PatronImagen> encontrarNuevoRepresentativo() {
        
        ArrayList<PatronImagen> nuevo = new ArrayList();
        HashMap<Integer,String> entrenador = new HashMap();
         int prom=0;
         int n1;
         int con = 1;
        double[] vector = new double[this.patrones.get(0).getVectorC().length];
        for(int x=0; x<this.patrones.size();x++){
            if(!entrenador.containsValue(this.patrones.get(x).getClase())){
                entrenador.put(con, this.patrones.get(x).getClase());
                con++;
            } 
        }
        Iterator<Integer> iterador = entrenador.keySet().iterator();
        while(iterador.hasNext()){
            Integer llave = iterador.next();
            for(int x=0; x<this.patrones.size();x++){
                if(entrenador.get(llave).equals(this.patrones.get(x).getClase())){
                    prom++;
                    for(int y=0; y<vector.length;y++){
                        vector[y]+=this.patrones.get(x).getVectorC()[y];
                        
                    }
                }
            }
                for(int j = 0; j<vector.length; j++){
                    vector[j] = vector[j]/prom; 
                }
                nuevo.add(new PatronImagen(0,0,vector));
               
                vector = new double[this.patrones.get(0).getVectorC().length];
                prom = 0;
        } 
        return nuevo;
    }
     
    private void asignarClase(ArrayList<PatronImagen> instancias) {
        
        for(int i = 0; i<patrones.size(); i++){
        double menor =  instancias.get(0).calcularDistancia(patrones.get(i));
        int n = 0;
        for(int k = 1; k<instancias.size(); k++){
            double numeroActual = instancias.get(k).calcularDistancia(this.patrones.get(i));
            if (numeroActual < menor) {
            menor = numeroActual;
            n = k;
            }
        }
      
       patrones.get(i).setClase(instancias.get(n).getClase());
       }
    }

    private boolean buscaPatron(PatronImagen aux) {
        for(int i=0; i<this.representativos.size(); i++){
            if(this.representativos.get(i).getVectorC().equals(aux)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<PatronImagen> getPatrones() {
        return patrones;
    }
    
    
}
