/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neurona;

import java.io.IOException;
import java.util.ArrayList;
import memoriasasociativas.LeerPatrones;
import memoriasasociativas.PatronesBinarios;

/**
 *
 * @author carli
 */
public class test {
    public static void main(String args[]) throws IOException{
       ArrayList<PatronesBinarios> patrones = LeerPatrones.tokenizarDataSet();
       PerceptronSimple ps = new PerceptronSimple(patrones,0.4);
       ps.entrenar();
       System.out.println();
       ps.comprobar();
    }
}
