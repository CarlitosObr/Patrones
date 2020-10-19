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
     ArrayList<Patron> representativos;

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
                //instancias.add(new Patron(vector,instancias.get(x).getClase()));
                //instancias = representativos;
                representativos.add(new Patron(vector,instancias.get(x).getClase()));
                vector = new double[instancias.get(x).getVectorC().length];
                prom = 0;
            }else{
                prom++;
                for(int y=0; y<vector.length;y++){
                vector[y]+=instancias.get(x).getVectorC()[y];
                }
            }   
            
            //instancias = representativos;
        }
        
       instancias.clear();
            for(int i = 0; i<representativos.size(); i++){
                instancias.add(representativos.get(i));
            }
        //instancias.add(representativos);  
    }

    @Override
    public void clasificar(ArrayList<Patron> instancias) {
       // clasificar las instancias
      
       /*for(int k =){
           
       }*/
    }
}
