/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.awt.ComponentOrientation;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carli
 */
public class Matriz {
      
    public int[][] matriz;
    public Object[][] objetos;
    public ArrayList<Patron> instancias;
    public ArrayList<String> clases;
    public JTable tabla = new JTable();

    public Matriz(ArrayList<Patron> instancias) {
        this.instancias = instancias;
        this.clases = new ArrayList<>();
        this.matriz = null;
        inicializarMatriz();
    }

    private void inicializarMatriz() {
       for(Patron p: this.instancias){
           if(!this.clases.contains(p.getClase())){
                this.clases.add(p.getClase());
           }
       }
       int m = this.clases.size();
       this.matriz = new int[m][m+1];
       
       // recorremos las instancias nuevamente
       for(Patron p: this.instancias){
           int r =this.clases.indexOf(p.getClase());
           int c =this.clases.indexOf(p.getClaseResultante()) ;
           this.matriz[r][c]++;
       }
       
      
    }

    @Override
    public String toString() {
        String aux = "";
        for(int r=0;r<this.matriz.length;r++){
                aux+="|";
            for(int c=0;c<this.matriz.length;c++){
             aux+=" "+this.matriz[r][c]+",";
            }
            aux+="\n";
        }
        return aux;
    }
    
    public void crearMatriz(ArrayList<Patron> instancias){
        objetos = new Object[instancias.size()][instancias.size()+1];
        String[] clases1 = new String[instancias.size()+1];
        clases1[0]="";
        for(int i = 0; i<instancias.size(); i++){
            objetos[i][0] = instancias.get(i).getClase();
            clases1[i+1] = instancias.get(i).getClase();
        }
        for(int x = 0; x<instancias.size(); x++){
            for(int j = 1; j<=instancias.size(); j++){
                objetos[x][j]=matriz[x][j-1];
            }
        }
        Table tablita = new Table(objetos,clases1);
        tablita.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tablita.setSize(600,200);
       
        tablita.setVisible(true);
        tablita.setTitle("Matriz de confusión");
    }
    
}
