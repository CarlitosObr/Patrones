/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;
import data.Patron;
import interfaces.ClasificadorSupervisado;
import java.util.ArrayList;
/**
 *
 * @author carli
 */
public class MinimaDistancia implements ClasificadorSupervisado{
     public ArrayList<Patron> representativos;
     
    public MinimaDistancia() {
        this.representativos = new ArrayList<>();
    }
    /*public MinimaDistancia(ArrayList<Patron> representativos) {
        this.representativos = representativos;
    }*/
           
    @Override
    public void entrenar(ArrayList<Patron> instancias) {
      // calcular los representativos
     int prom=0;
     int clase = 1;
     int claseComp;
     int n;
    // instancias = representativos;
     double[] vector = new double[instancias.get(0).getVectorC().length];
        for(int x=0; x<instancias.size();x++){
          claseComp = clase;
          clase = instancias.get(x).getNum_clas();
            if(clase != claseComp){
                for(int j = 0; j<vector.length; j++){
                    vector[j] = vector[j]/prom; 
                }
                instancias.add(new Patron(vector,instancias.get(x-1).getClase()));
                //instancias = representativos;
                representativos.add(new Patron(vector,instancias.get(x-1).getClase()));
                vector = new double[instancias.get(x).getVectorC().length];
                prom = 0;
            }else{
                prom++;
                for(int y=0; y<vector.length;y++){
                vector[y]+=instancias.get(x).getVectorC()[y];
                }
            }   
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
    }

    @Override
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
        System.out.println("Incorrectos: "+(instancias.size()-n-representativos.size()));
        eficacia = (100*n)/(instancias.size()-representativos.size());
        return eficacia;
    }
}
