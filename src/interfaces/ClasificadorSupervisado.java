/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author carli
 */
import data.Patron;
import java.util.ArrayList;


public interface ClasificadorSupervisado {
    
    public abstract void entrenar(ArrayList<Patron> instancias);
    public abstract void clasificar(ArrayList<Patron> instancias,Patron nuevo);
    //public abstract double calculaEficiencia(ArrayList<Patron> instancias);
    
}