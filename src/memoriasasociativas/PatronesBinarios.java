/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriasasociativas;

/**
 *
 * @author carli
 */
public class PatronesBinarios {
    private int[] VectorP;
    private String clase;
    private String claseResultante;
    private double[] VectorN;
    private double[] VectorT;
    private int[] vectorBin;
    
    public PatronesBinarios(){
        
    }
    public PatronesBinarios(int[] VectorP){
        this.VectorP = VectorP;
    }
    
    public PatronesBinarios(double[] VectorN,String clase){
        this.VectorN = VectorN;
        this.clase = clase;
        this.claseResultante = "";
    }

    public int[] getVectorP() {
        return VectorP;
    }

    public void setVectorP(int[] VectorP) {
        this.VectorP = VectorP;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getClaseResultante() {
        return claseResultante;
    }

    public void setClaseResultante(String claseResultante) {
        this.claseResultante = claseResultante;
    }

    public double[] getVectorN() {
        return VectorN;
    }

    public void setVectorN(double[] VectorN) {
        this.VectorN = VectorN;
    }

    public int[] getVectorBin() {
        return vectorBin;
    }

    public void setVectorBin(int[] vectorBin) {
        this.vectorBin = vectorBin;
    }

    public double[] getVectorT() {
        return VectorT;
    }

    public void setVectorT(double[] VectorT) {
        this.VectorT = VectorT;
    }
    
    
    
}
