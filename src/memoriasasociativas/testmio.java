/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriasasociativas;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author carli
 */
public class testmio {
    public static void main(String args[]) throws IOException{
       ArrayList<PatronesBinarios> patrones = LeerPatrones.tokenizarDataSet();
       CAP cap = new CAP(patrones);
       cap.aprendizaje();
       
       double n1 = 0;
       for(int i = 0; i<patrones.size(); i++){
           //System.out.println("--------"+i);
           cap.recuperacion(patrones.get(i));
           //n1+= patrones.get(i).getVectorN()[1];
       }
       for(int i = 0; i<patrones.size(); i++){
           System.out.println("Patron "+i+": "+patrones.get(i).getClaseResultante());
           
       }
       
       //nueva.aprendizaje();
       //System.out.println(n1);
       //nueva.recuperación();*/
       //PatronesBinarios n = cap.medio;
       System.out.println(cap.calculaEficiencia(patrones));
    }
}
