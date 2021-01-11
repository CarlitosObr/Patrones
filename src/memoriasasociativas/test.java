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
public class test {
   public static void main(String args[]) throws IOException{
       ArrayList<PatronesBinarios> patrones = LeerPatrones.tokenizarDataSet();
       LearnMatrix nueva = new LearnMatrix(patrones);
       nueva.aprendizaje();
       nueva.recuperación();
       
       System.out.println(nueva.salidaY);
       System.out.println();
   }
}
