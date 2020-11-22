/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;

import data.Patron;
import interfaces.ClasificadorSupervisado;
import java.util.ArrayList;
import data.calculaDistancias;
import static java.lang.System.exit;
/**
 *
 * @author carli
 */
public class KNN implements ClasificadorSupervisado{
    public final int k;
    public ArrayList<Patron> representativos = new ArrayList();
    public ArrayList<Patron> entrenado = new ArrayList();
    public ArrayList<Patron> clasificado = new ArrayList();
    calculaDistancias cd = new calculaDistancias();
    public KNN(int k){
        this.k = k;
    }
    
    @Override
    public void entrenar(ArrayList<Patron> instancias) {
        int cont;
        int clases=1;
        int clase = 1;
        int claseComp;
    // instancias = representativos;
        representativos.add(instancias.get(0));
        for(int x=0; x<instancias.size();x++){
          claseComp = clase;
          clase = instancias.get(x).getNum_clas();
          //representativos.add(instancias.get(x));
            if(clase != claseComp){    
                clases++;
                representativos.add(instancias.get(x));
            }  
        }
        for(int i=0; i<instancias.size();i++){
            entrenado.add(new Patron(0,instancias.get(i).getClase(),instancias.get(i).getVectorC()));
        }
    }

    @Override
    public void clasificar(ArrayList<Patron> instancias, Patron nuevo) {
         entrenado.clear();
         for(int i=0; i<instancias.size();i++){
            entrenado.add(new Patron(0,instancias.get(i).getClase(),instancias.get(i).getVectorC()));
        }
         cd.calcular(entrenado,nuevo);
         int[] contador = new int[representativos.size()];
         for(int i = 0; i<contador.length; i++){
             contador[i] = 0;
         }
         for(int i=0; i<entrenado.size(); i++){
             for(int j =0; j<representativos.size(); j++){
                 if(representativos.get(j).getClase().equals(entrenado.get(i).getClase())){
                     contador[j]+=1;
                     //System.out.println("Cuenta: "+contador[j]+ "Clase: "+representativos.get(j).getClase());
                     if(contador[j]==k){
                        nuevo.setClaseResultante(entrenado.get(i).getClase());
                        clasificado.add(nuevo);
                         //System.out.println("Cuenta: "+contador[j]+ "Clase: "+representativos.get(j).getClase());
                         //exit(0);
                        return;
                     }
                 }
             }
        }
    }

    
    public double calculaEficiencia(ArrayList<Patron> instancias) {
        double n=0;
        double elementos = 0;
        double eficiencia=0;
        for(int x=0; x<instancias.size();x++){
            if(instancias.get(x).getClase().equals(instancias.get(x).getClaseResultante())){
               n+=1; 
            }
        }
        System.out.println("Correctos: "+n);
        System.out.println("Incorrectos: "+(instancias.size()-n));
        eficiencia = (100*n)/(instancias.size());
        return eficiencia;
    }

    public int getK() {
        return k;
    }

    public ArrayList<Patron> getClasificado() {
        return clasificado;
    }

    public void setClasificado(ArrayList<Patron> clasificado) {
        this.clasificado = clasificado;
    }

    
    
    
}
