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
/**
 *
 * @author carli
 */
public class KNN implements ClasificadorSupervisado{
    private final int k;
    private ArrayList<Patron> entrenado;
    calculaDistancias cd = new calculaDistancias();
    public KNN(int k){
        this.k = k;
    }
    
    @Override
    public void entrenar(ArrayList<Patron> instancias) {
        this.entrenado = instancias;
    }

    @Override
    public void clasificar(ArrayList<Patron> instancias, Patron nuevo) {
        cd.calcular(instancias,nuevo);
        
    }

    @Override
    public double calculaEficiencia(ArrayList<Patron> instancias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getK() {
        return k;
    }
}
