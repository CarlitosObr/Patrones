/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;
import data.Patron;
import interfaces.ClasificadorSupervisado;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/**
 *
 * @author carli
 */
public class MinimaDistancia implements ClasificadorSupervisado{
    public ArrayList<Patron> representativos;
    public ArrayList<Patron> clasificados = new ArrayList();
     
    public MinimaDistancia() {
        this.representativos = new ArrayList<>();
    }
    /*public MinimaDistancia(ArrayList<Patron> representativos) {
        this.representativos = representativos;
    }*/
    @Override
    public void entrenar(ArrayList<Patron> instancias) {
      // calcular los representativos
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
                representativos.add(new Patron(vector,entrenador.get(llave)));
                vector = new double[instancias.get(0).getVectorC().length];
                prom = 0;
        }             
 }
       
    
    @Override
    public void clasificar(ArrayList<Patron> instancias, Patron comp) {
       // clasificar las instancias
       double menor = representativos.get(0).calcularDistancia(comp);
       int n = 0;
       for(int k = 1; k<representativos.size(); k++){
            double numeroActual =  representativos.get(k).calcularDistancia(comp);
            if (numeroActual < menor) {
            menor = numeroActual;
            n = k;
            }
        }
       comp.setClaseResultante(representativos.get(n).getClase());
       clasificados.add(comp);
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

    public ArrayList<Patron> getRepresentativos() {
        return representativos;
    }

    public void setRepresentativos(ArrayList<Patron> representativos) {
        this.representativos = representativos;
    }
    
    
}
