/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author working
 */
public class Patron {
    
    private String clase;
    private String claseResultante;
    private double[] vectorC;
    private int num_clas;
    private double distancia;
    public Patron(int n) {
        this.clase = "";
        this.claseResultante = "";
        this.vectorC = new double[n];
        //this.num_clas = num_clas; 
    }

    public Patron(String clase, String claseResultante, double[] vectorC,int num_clas) {
        this.clase = clase;
        this.claseResultante = claseResultante;
        this.vectorC = vectorC;
        this.num_clas= num_clas;
    }

    public Patron(double[] vectorC, String clase) {
        this.vectorC = vectorC;
        this.clase = clase;
    }
    
    public Patron(int distancia, String clase,double[] vectorC) {
        this.distancia = distancia;
        this.clase = clase;
        this.vectorC = vectorC;
    }
    public Patron(Patron patron) {
        this.vectorC = patron.getVectorC().clone();
        this.clase = patron.getClase();
        /*this.x = patron.getX();
        this.y = patron.getY();*/
        
    }
    // distancia euclidiana
    public double calcularDistancia (Patron aux){
        double sumatoria = 0;
        // recorre el vector característico
        for (int x=0; x<aux.getVectorC().length;x++ ){
         sumatoria+= Math.pow(this.vectorC[x]-aux.getVectorC()[x], 2);
        }
        sumatoria = Math.sqrt(sumatoria);
        return sumatoria;
    }

    /**
     * @return the clase
     */
    public String getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * @return the claseResultante
     */
    public String getClaseResultante() {
        return claseResultante;
    }

    /**
     * @param claseResultante the claseResultante to set
     */
    public void setClaseResultante(String claseResultante) {
        this.claseResultante = claseResultante;
    }

    /**
     * @return the vectorC
     */
    public double[] getVectorC() {
        return vectorC;
    }

    /**
     * @param vectorC the vectorC to set
     */
    public void setVectorC(double[] vectorC) {
        this.vectorC = vectorC;
    }

    public int getNum_clas() {
        return num_clas;
    }

    public void setNum_clas(int num_clas) {
        this.num_clas = num_clas;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    
    
    
}
