/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;

import data.Medidas;
import data.Patron;
import interfaces.ClasificadorSupervisado;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author carli
 */
public class Bayes implements ClasificadorSupervisado{
    Medidas mediditas;
    public ArrayList<Patron> priori;
    public ArrayList<Patron> promedio;
    public ArrayList<Patron> varianza;
    public ArrayList<Patron> desviacion;
    public ArrayList<Patron> dist;
    public ArrayList<Patron> posteriori;
    public double evidencia;
    public ArrayList<Patron> clasificados = new ArrayList();
    public Bayes() {
    }
    
    @Override
    public void entrenar(ArrayList<Patron> instancias) {
        HashMap<Integer,String> entrenador = new HashMap();
        int prom=0;
        int n;
        int con = 1;
    // instancias = representativos;
        double[] vector = new double[instancias.get(0).getVectorC().length];
        for(int x=0; x<instancias.size();x++){
            if(!entrenador.containsValue(instancias.get(x).getClase())){
                entrenador.put(con, instancias.get(x).getClase());
                con++;
            } 
        }
        mediditas = new Medidas(instancias,entrenador);
        priori = mediditas.obtenPriori();
        promedio = mediditas.obtenerPromedio();
        varianza = mediditas.obtenerVarianza();
        desviacion = mediditas.obtenerDesviacion();
    }

    @Override
    public void clasificar(ArrayList<Patron> instancias, Patron nuevo) {
        dist = mediditas.obtenerDistNorm(nuevo);
        evidencia = mediditas.obtenerEvidencia();
        posteriori = mediditas.obtenerPosteriori();
       double mayor = posteriori.get(0).getVectorC()[0];
       int n = 0;
       for(int k = 1; k<posteriori.size(); k++){
            double numeroActual =  posteriori.get(k).getVectorC()[0];
            if (numeroActual > mayor) {
            mayor = numeroActual;
            n = k;
            }
        }
       nuevo.setClaseResultante(posteriori.get(n).getClase());
       clasificados.add(nuevo);
    }
    public double calculaEficiencia(ArrayList<Patron> instancias) {
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
    public ArrayList<Patron> getClasificados() {
        return clasificados;
    }

    public void setClasificados(ArrayList<Patron> clasificados) {
        this.clasificados = clasificados;
    }
    
}
