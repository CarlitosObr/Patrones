/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author carli
 */
public class Medidas {
    public ArrayList<Patron> priori;
    public ArrayList<Patron> promedio;
    public ArrayList<Patron> varianza;
    public ArrayList<Patron> desviacion;
    public ArrayList<Patron> instancias;
    public HashMap<Integer,String> entrenador;
    public ArrayList<Patron> distNorm;
    public ArrayList<Patron> posteriori;

    public Medidas(ArrayList<Patron> instancias, HashMap<Integer,String> entrenador) {
        this.instancias = instancias; 
        this.entrenador = entrenador;
    }

    public ArrayList<Patron> obtenPriori(){
        priori = new ArrayList();
       
        double prom=0;
    // instancias = representativos;
        double[] vector = new double[1];
        
        Iterator<Integer> iterador = entrenador.keySet().iterator();
        while(iterador.hasNext()){
            Integer llave = iterador.next();
            for(int x=0; x<instancias.size();x++){
                if(entrenador.get(llave).equals(instancias.get(x).getClase())){
                    prom++; 
                }
            }
                vector[0] = (prom*1)/instancias.size(); 
                priori.add(new Patron(vector,entrenador.get(llave)));
                vector = new double[1];
                prom = 0;
        }
        return priori;
    }
    public ArrayList<Patron> obtenerPromedio(){
        promedio = new ArrayList();
        int prom=0;
    // instancias = representativos;
        double[] vector = new double[instancias.get(0).getVectorC().length];
        Iterator<Integer> iterador = entrenador.keySet().iterator();
        while(iterador.hasNext()){
            Integer llave = iterador.next();
            for(int x=0; x<instancias.size();x++){
                if(entrenador.get(llave).equals(instancias.get(x).getClase())){
                    prom++;
                    for(int y=0; y<vector.length;y++){
                        vector[y]+=instancias.get(x).getVectorC()[y];
                        
                    }
                }
            }
                for(int j = 0; j<vector.length; j++){
                    vector[j] = vector[j]/prom; 
                }
                promedio.add(new Patron(vector,entrenador.get(llave)));
                vector = new double[instancias.get(0).getVectorC().length];
                prom = 0;
        }  
        return promedio;
    }
    public ArrayList<Patron> obtenerVarianza(){
        varianza = new ArrayList();
        int prom=0;
        int con=0;
        double[] vector = new double[instancias.get(0).getVectorC().length];
        Iterator<Integer> iterador = entrenador.keySet().iterator();
        while(iterador.hasNext()){
            Integer llave = iterador.next();
            for(int x=0; x<instancias.size();x++){
                if(entrenador.get(llave).equals(instancias.get(x).getClase())){
                    prom++;
                    for(int y=0; y<vector.length;y++){
                        vector[y]+=Math.pow((instancias.get(x).getVectorC()[y]-promedio.get(con).getVectorC()[y]),2);  
                    }
                }
            }
            for(int j = 0; j<vector.length; j++){
                    vector[j] = vector[j]/(prom-1); 
                }
                varianza.add(new Patron(vector,entrenador.get(llave)));
                vector = new double[instancias.get(0).getVectorC().length];
                prom = 0;
                con++;
        }  
        return varianza;
    }
    public ArrayList<Patron> obtenerDesviacion(){
        desviacion = new ArrayList();
        int prom=0;
        int con=0;
        double[] vector = new double[instancias.get(0).getVectorC().length];
        Iterator<Integer> iterador = entrenador.keySet().iterator();
        while(iterador.hasNext()){
            Integer llave = iterador.next();
            for(int x=0; x<varianza.size();x++){
                if(entrenador.get(llave).equals(varianza.get(x).getClase())){
                    for(int y=0; y<vector.length;y++){
                        vector[y]+=Math.sqrt(varianza.get(x).getVectorC()[y]);  
                    }
                }
            }
                desviacion.add(new Patron(vector,entrenador.get(llave)));
                vector = new double[instancias.get(0).getVectorC().length];
                prom = 0;
                con++;
        }  
        return desviacion;
    }
    public ArrayList<Patron> obtenerDistNorm(Patron p){
        distNorm = new ArrayList();
        int prom=0;
        int con=0;
        double[] vector = new double[instancias.get(0).getVectorC().length];
        Iterator<Integer> iterador = entrenador.keySet().iterator();
        while(iterador.hasNext()){
            Integer llave = iterador.next();
            for(int x=0; x<varianza.size();x++){
                if(entrenador.get(llave).equals(varianza.get(x).getClase())){
                    for(int y=0; y<vector.length;y++){
                        //vector[y]+=((1/Math.sqrt(2*Math.PI*Math.pow(varianza.get(x).getVectorC()[y], 2))))*(Math.pow(Math.E,-((Math.pow(p.getVectorC()[y]-promedio.get(x).getVectorC()[y], 2))/(2*Math.pow(varianza.get(x).getVectorC()[y], 2)))));
                        vector[y]+=((1/Math.sqrt(2*Math.PI*varianza.get(x).getVectorC()[y])))*(Math.pow(Math.E,-((Math.pow(p.getVectorC()[y]-promedio.get(x).getVectorC()[y], 2))/(2*varianza.get(x).getVectorC()[y]))));
                    }
                }
            }
                distNorm.add(new Patron(vector,entrenador.get(llave)));
                vector = new double[instancias.get(0).getVectorC().length];
                prom = 0;
                con++;
        }  
        
        return distNorm;
    }
    public double obtenerEvidencia(){
        double evidencia=0;
        double prom=0;
        int con=0;
        double[] vector = new double[instancias.get(0).getVectorC().length];
        Iterator<Integer> iterador = entrenador.keySet().iterator();
        while(iterador.hasNext()){
            Integer llave = iterador.next();
            prom+=priori.get(con).getVectorC()[0];
            for(int x=0; x<desviacion.size();x++){
                if(entrenador.get(llave).equals(desviacion.get(x).getClase())){
                    for(int y=0; y<vector.length;y++){
                        prom = prom*desviacion.get(x).getVectorC()[y];  
                    }
                }
            }
            evidencia+=prom;
            prom=0;
            con++;
        }  
        return evidencia;
    }
    public ArrayList<Patron> obtenerPosteriori(){
        posteriori = new ArrayList();
        double prom=0;
        int con=0;
        double[] vector = new double[1];
        Iterator<Integer> iterador = entrenador.keySet().iterator();
        while(iterador.hasNext()){
            Integer llave = iterador.next();
            vector[0]+=priori.get(con).getVectorC()[0];
            for(int x=0; x<distNorm.size();x++){
                if(entrenador.get(llave).equals(distNorm.get(x).getClase())){
                    for(int y=0; y<distNorm.get(x).getVectorC().length;y++){
                        vector[0] *= distNorm.get(x).getVectorC()[y];  
                    }
                }
            }
            vector[0]= vector[0]/obtenerEvidencia();
            posteriori.add(new Patron(vector,entrenador.get(llave)));
            vector = new double[1];
            prom=0;
            con++;
        }  
        return posteriori;
    }
}
