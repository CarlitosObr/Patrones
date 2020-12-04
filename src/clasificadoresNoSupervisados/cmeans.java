/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadoresNoSupervisados;

import data.Patron;
import interfaces.ClasificadorNoSupervisado;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author carli
 */
public class cmeans implements ClasificadorNoSupervisado{
    private int c;
    private ArrayList<Patron> representativos = new ArrayList();
    private ArrayList<Patron> patrones;   
            
    public cmeans(int c) {
        this.c = c;
    }
    
    @Override
    public void entrenar(ArrayList<Patron> instancias) {
        this.patrones = instancias;
    }

    @Override
    public void clasificar() {
       ArrayList<Patron> comparar;
       ArrayList<Patron> comparar2;
       double distancia = 1;
       double comparacion;
       double com;
       //distancia=this.representativos.get(0).calcularDistancia(comparar.get(0));
       generarRepresentativosIn();
       
       asignarClase(this.representativos);
       while(distancia!=0){
                comparacion=0;
                com = 0;
                comparar = this.representativos;
                for(int i=0; i<this.representativos.size(); i++){
                    for(int j = 0; j<this.representativos.get(i).getVectorC().length; j++){
                        
                        comparacion += comparar.get(i).getVectorC()[j];
                        //System.out.println(comparar.get(i).getVectorC()[j]);
                    }  
                }
                
                System.out.println(comparacion);
                
                asignarClase(comparar);
                comparar2 = encontrarNuevoRepresentativo();
                
               
                for(int i=0; i<comparar2.size(); i++){
                    for(int j = 0; j<comparar2.get(i).getVectorC().length; j++){
                        com += comparar2.get(i).getVectorC()[j];
                    }  
                }
               
                this.representativos = (ArrayList<Patron>) comparar2.clone();
                System.out.println(com);
                //System.out.println(Math.abs(com-comparacion));
                distancia = Math.abs(comparacion - com);   
        }
    }

    private void generarRepresentativosIn() {
        
        Random ran = new Random();
        int aux = ran.nextInt(this.patrones.size());
        while(this.c!=0){
            this.representativos.add(patrones.get(aux));
            aux = ran.nextInt(this.patrones.size());
            this.c--;
        }
        
    }

    public ArrayList<Patron> getRepresentativos() {
        return representativos;
    }

    private ArrayList<Patron> encontrarNuevoRepresentativo() {
        
        ArrayList<Patron> nuevo = new ArrayList();
        HashMap<Integer,String> entrenador = new HashMap();
         int prom=0;
         int n1;
         int con = 1;
        double[] vector = new double[this.patrones.get(0).getVectorC().length];
        for(int x=0; x<this.patrones.size();x++){
            if(!entrenador.containsValue(this.patrones.get(x).getClaseResultante())){
                entrenador.put(con, this.patrones.get(x).getClaseResultante());
                con++;
            } 
        }
        Iterator<Integer> iterador = entrenador.keySet().iterator();
        while(iterador.hasNext()){
            Integer llave = iterador.next();
            for(int x=0; x<this.patrones.size();x++){
                if(entrenador.get(llave).equals(this.patrones.get(x).getClaseResultante())){
                    prom++;
                    for(int y=0; y<vector.length;y++){
                        vector[y]+=this.patrones.get(x).getVectorC()[y];
                        
                    }
                }
            }
                for(int j = 0; j<vector.length; j++){
                    vector[j] = vector[j]/prom; 
                }
                nuevo.add(new Patron(vector,entrenador.get(llave)));
               
                vector = new double[this.patrones.get(0).getVectorC().length];
                prom = 0;
        } 
        return nuevo;
    }
     
    private void asignarClase(ArrayList<Patron> instancias) {
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
       patrones.get(i).setClaseResultante("Clase "+(n+1));
       }
    }
    
    
}
