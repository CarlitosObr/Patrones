/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neurona;

import java.util.ArrayList;
import java.util.Random;
import memoriasasociativas.PatronesBinarios;

/**
 *
 * @author carli
 */
public class PerceptronSimple {
    public ArrayList<PatronesBinarios> X;
    public double a;
    public double[] W;
    public double error;
    public double[] X0;
    public double[] yD;
    public double[] y;
    
    public PerceptronSimple(){
        
    }
    
    public PerceptronSimple(ArrayList<PatronesBinarios> X, double a){
        this.X = X;
        this.a = a;
        this.W = new double[X.size()];
        this.X0 = new double[X.get(0).getVectorN().length];
        this.yD = new double[X.get(0).getVectorN().length];
        this.y = new double[X.get(0).getVectorN().length];
    }
    
    public void entrenar(){
        calculaPesosIniciales();
        hacerAmenosUno();
        llenarX0();
        int con = 0;
        double sum=0;
        while(con!=X.get(0).getVectorN().length){
            sum += (X0[con]*W[0]);
            for(int c=0; c<X.size()-1; c++){
                sum += (X.get(c).getVectorN()[con]*W[c+1]);
            }
            if(sum>=0){
                y[con]=1;
            }else if(sum<0){
                y[con]=-1;
            }
            error = yD[con] - y[con];
            
            if(error == 0){
                con++;
                sum=0;
            }else{
                calcularNuevosPesos(con);
                con=0;
                sum=0;
                y = new double[X.get(0).getVectorN().length];
            }
            
        }
        System.out.println("-----PESOS FINALES----");
        for(int i = 0; i< W.length; i++){
            System.out.print(W[i]+"\t");
        }
        System.out.println();
    }
    
    public void comprobar(){
        int con = 0;
        double sumatoria=0;
        while(con!=X.get(0).getVectorN().length){
        
            sumatoria += (X0[con]*W[0]);
            for(int c=0; c<X.size()-1; c++){
                sumatoria += (X.get(c).getVectorN()[con]*W[c+1]);
            }
            if(sumatoria>=0){
                y[con]=1;
            }else if(sumatoria<0){
                y[con]=0;
            }
            con++;
            sumatoria = 0;
        }
        System.out.println("SALIDA Y");
        for(int i = 0; i<y.length; i++){
            System.out.print(y[i]+"\t");
        }
        System.out.println();
    }

    private void calculaPesosIniciales() {
        Random ran = new Random();
        for(int i = 0; i< W.length; i++){
            W[i] = ran.nextDouble();
        }  
    }

    private void hacerAmenosUno() {
        for(int i = 0; i< X.size(); i++){
            for(int x = 0; x< X.get(0).getVectorN().length; x++){
                if(X.get(i).getVectorN()[x] == 0){
                    X.get(i).getVectorN()[x] = -1;
                }
            }
        }
        yD = X.get(X.size()-1).getVectorN();
    }

    private void llenarX0() {
        for(int i = 0; i< X0.length; i++){
            X0[i] = 1;
        }
    }

    private void calcularNuevosPesos(int c) {
        W[0] += (a*X0[c]*error); 
        for(int m= 1; m<W.length; m++){
            W[m]+= (a*X.get(m-1).getVectorN()[c]*error); 
        }
    }
    
}
