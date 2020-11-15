/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author carli
 */
public class calculaDistancias {
    
    public void calcular(ArrayList<Patron> patrones, Patron desconocido){
        for (Patron patrone : patrones) {
            patrone.setDistancia(desconocido.calcularDistancia(patrone));
        }    
        ordenar(patrones);
    }
    
    public void ordenar(ArrayList<Patron> patron){
        int i,  j;  
        double key;// 3
        String clase,claseR;
        double[] vector;
        for (i = 1; i < patron.size(); i++){ //  3n  =  10n
            key = patron.get(i).getDistancia();
            clase = patron.get(i).getClase();
            //vector = patron.get(i).getVectorC();
            //claseR = patron.get(i).getClaseResultante();// 2
            j = i - 1;      // 2 
            while (j >= 0 && patron.get(j).getDistancia() > key){  // 4nn  =   10n2 
                // 4 
                patron.get(j+1).setClase(patron.get(j).getClase());
                //patron.get(j+1).setClaseResultante(patron.get(j).getClaseResultante());
                patron.get(j+1).setDistancia(patron.get(j).getDistancia());
                //patron.get(j+1).setVectorC(patron.get(j).getVectorC());
                j = j - 1;  // 2
            }  
            patron.get(j+1).setDistancia(key);   // 3    
            patron.get(j+1).setClase(clase);
            //patron.get(j+1).setVectorC(vector);
            //patron.get(j+1).setClaseResultante(claseR);
        } 
    }
}
