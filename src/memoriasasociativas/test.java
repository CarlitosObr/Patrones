/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriasasociativas;


import pruebas.Patron;
import java.io.IOException;
import java.util.ArrayList;
import pruebas.Cap;

/**
 *
 * @author carli
 */
public class test {
   public static void main(String args[]) throws IOException{
       ArrayList<PatronesBinarios> patrones = LeerPatrones.tokenizarDataSet();
       LearnMatrix cap = new LearnMatrix(patrones);
       cap.aprendizaje();
       cap.recuperación();
       ArrayList<PatronesBinarios> salidaY = cap.salidaY;
       
       for(int i=0; i<salidaY.size();i++){
           System.out.println("La salida de la entrada X"+(i+1)+" es: ");
           for(int y = 0; y<salidaY.get(i).getVectorP().length; y++){
               System.out.println(salidaY.get(i).getVectorP()[y]);
           }
       }
       //nueva.aprendizaje();
       //System.out.println(n1);
       //nueva.recuperación();*/
       //PatronesBinarios n = cap.medio;
       System.out.println();
   }
}
