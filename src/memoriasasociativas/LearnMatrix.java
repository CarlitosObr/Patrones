/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriasasociativas;

import java.util.ArrayList;

/**
 *
 * @author carli
 */
public class LearnMatrix {
    public ArrayList<PatronesBinarios> iniciales;
    public ArrayList<PatronesBinarios> X = new ArrayList<>();
    public ArrayList<PatronesBinarios> Y = new ArrayList<>();
    public ArrayList<PatronesBinarios> salidaY = new ArrayList<>();
    public int[][] learnmatrix;
    public final int E = 1;
    
    public LearnMatrix(ArrayList<PatronesBinarios> iniciales){
        this.iniciales = (ArrayList<PatronesBinarios>) iniciales.clone();
    }
    
    public void aprendizaje(){
        acomodarPatrones();
        learnmatrix = new int[Y.get(0).getVectorP().length+1][X.get(0).getVectorP().length+1];
        incorporarParejadePatrones();    
    }
    
    public void recuperación(){
            int indice=0;
            while(indice != X.size()){        
                int[] vector = new int[Y.get(0).getVectorP().length];
                for(int i = 1; i<=Y.get(0).getVectorP().length; i++){
                    for(int j = 1; j<= X.get(0).getVectorP().length; j++){
                       vector[i-1] = vector[i-1]+(learnmatrix[i][j]*X.get(indice).getVectorP()[j-1]);
                    }
                }
                saberMayor(vector);
                salidaY.add(new PatronesBinarios(vector));
                indice++;
            }
         
    }

    private void acomodarPatrones() {
        for(int i = 0; i<iniciales.size(); i+=2){
            X.add(iniciales.get(i));
        }
        for(int i = 1; i<iniciales.size(); i+=2){
            Y.add(iniciales.get(i));
        }
    }

    private void imprimirMatriz() {
        for(int i = 0; i<=Y.get(0).getVectorP().length;i++){
            for(int j= 0; j<=X.get(0).getVectorP().length; j++){
                System.out.print(""+learnmatrix[i][j]+"\t");
            }
            System.out.println();
        }
    }

    private void incorporarParejadePatrones() {
        int parejanueva = X.size();
        int indice = 0;
        int indicex = 0;
        while(parejanueva>0){
            for(int i= 1; i<=Y.get(0).getVectorP().length;i++){
                learnmatrix[i][0] = Y.get(indice).getVectorP()[i-1];
            }
            for(int i= 1; i<=X.get(0).getVectorP().length;i++){
                learnmatrix[0][i] = X.get(indicex).getVectorP()[i-1];
            }
            for(int i = 1; i<=Y.get(0).getVectorP().length; i++){
                for(int j=1; j<=X.get(0).getVectorP().length; j++){
                    if(learnmatrix[i][0] == 1 && learnmatrix[0][j] == 1){
                        learnmatrix[i][j] = learnmatrix[i][j] + E;
                    }else if(learnmatrix[i][0] == 1 && learnmatrix[0][j] == 0){
                        learnmatrix[i][j] = learnmatrix[i][j] - E;
                    }
                }
            }
            System.out.println("-----------------Nueva pareja----------------");
            imprimirMatriz();
            parejanueva--;
            indice++;
            indicex++;
        }
    }

    private void saberMayor(int[] vector) {
        int mayor = vector[0];
        int n = 0, d = 0; 
        int[] z= new int[vector.length];
        for(int i = 1; i<vector.length; i++){
            int mayoraux = vector[i];
            if(mayoraux>mayor){
                mayor = mayoraux;
                n = i;
            }
        }  
        //System.out.println("El valor de n: "+n);
        d= vector[n];
        for(int i=0; i<vector.length; i++){
           //System.out.println("N: "+d+" I: "+vector[i]);
           if(d == vector[i]){
              vector[i] = 1;
           }else{
              vector[i] = 0;
           }
        }
    } 
}
