/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadoresNoSupervisados;

import data.Patron;
import java.util.ArrayList;

/**
 *
 * @author carli
 */
public class Min_Max {
    // conjunto de instancias
    private ArrayList<Patron> instancias;
    private double mediaDistancia;
    private double umbral;
    private ArrayList<Patron> centroidesBase = new ArrayList<>();
    private double a,d;
    
    public Min_Max(ArrayList<Patron> instancias,double umbral){
        this.instancias = instancias;
        this.umbral = umbral;
    }
    public void clasifica(){
        // se definar los centroides iniciales 
        // se defina la media de los centroides iniciales (2)
        encuentra2CentroidesIniciales();
        int iM;
        ArrayList<Double> minimos;
        boolean nuevoC= false;
        // generar un proceso iterativo en el cual 
        // se determinarar el numero de centrodies finales 
        do{
        //calcular la lista de minimos
        minimos = new ArrayList<>();
        for (Patron aux:this.instancias){
          double menor = aux.calcularDistancia(centroidesBase.get(0));
          for (int x=1; x < this.centroidesBase.size();x++){
             double dd = aux.calcularDistancia(centroidesBase.get(x));
              if (dd<menor){
               menor = dd;
              }
          }
         minimos.add(menor);
        }
        // determinar el argumento maximo 
        // y el indice de la instancia 
        this.d = minimos.get(0);
        int iI = 0;
        for (int y=0; y < minimos.size();y++){
          if (minimos.get(y)>this.d){
          this.d = minimos.get(y);
          iI = y;
          }
        }
        // se tiene que evaluar el argumento 
        // maximo d>= t*ai
        if (d>=umbral*a){
        // creamos un nuevo centroide
        crearNuevoCentroide(iI);
        nuevoC = true;
        }else {
        nuevoC = false;
        }
                
        }while(nuevoC);
        // clasificamos por MD
        // recorrer las instancias y clasificar cada una de ellas 
       System.out.println("Clasificaión----");
        for (Patron aux: this.instancias){
          aux.setClase(clasificaMd(aux));
          System.out.println(aux.getClase());
        }
       
        System.out.println();
        
       
    }
    private double[] sumaVectores(double[] vector, double[] vector0) {
       double aux[] = new double[vector.length];
       for (int x=0; x < aux.length;x++)
           aux[x] = vector[x]+vector0[x];
       
       return aux;
    }
    private void encuentra2CentroidesIniciales(){
    
        Patron central=new Patron(this.instancias.get(0).getVectorC().length);
        // acumular cada vector en central
        for(Patron patron: instancias)
        {
            central.setVectorC(sumaVectores(central.getVectorC(),patron.getVectorC()));
        }
        double[] vectoraux=central.getVectorC();
        for(int i=0;i<this.instancias.get(0).getVectorC().length;i++)
        {
            vectoraux[i]/=this.instancias.size();
        }
        central.setVectorC(vectoraux);
        // buscar el más alejado del central
        double distancia=central.calcularDistancia(instancias.get(0));
        int indice=0;
        for(int x=1;x<this.instancias.size();x++)
        {
            if(central.calcularDistancia(instancias.get(x))>distancia)
            {
                distancia=central.calcularDistancia(instancias.get(x));
                indice=x;
            }
        }
        
        Patron lejano1= new Patron(this.instancias.get(indice));
        this.instancias.remove(indice);
        // buscar el más alejado de lejano1
        distancia=lejano1.calcularDistancia(instancias.get(0));
        indice=0;
        for(int x=1;x<this.instancias.size();x++)
        {
            if(lejano1.calcularDistancia(instancias.get(x))>distancia)
            {
                distancia=lejano1.calcularDistancia(instancias.get(x));
                indice=x;
            }
        }
        Patron lejano2=new Patron(this.instancias.get(indice));
        this.instancias.remove(indice);
        // asigno el nombre
        lejano1.setClase("Cluster0");
        System.out.println(lejano1.getClase());
        lejano2.setClase("Cluster1");
        System.out.println(lejano2.getClase());
        centroidesBase.add(lejano1);
        centroidesBase.add(lejano2);
        a=(centroidesBase.get(0).calcularDistancia(centroidesBase.get(1)))/2;
    }

    private void crearNuevoCentroide(int iI) {
        int id = this.centroidesBase.size();
        // agregamos el nuevo centroide
        Patron aux = new Patron(this.instancias.get(iI));
        aux.setClase("Cluster"+id);
        System.out.println(aux.getClase());
        this.centroidesBase.add(aux);
        // eliminamos la instancia iI
        this.instancias.remove(iI);
        
    }
    
    public String clasificaMd(Patron patron) {
        String nC="";
        if (this.centroidesBase.size()>0){
            
        double distM = patron.calcularDistancia(this.centroidesBase.get(0));
        nC =  this.centroidesBase.get(0).getClase();
        // recorrer todas las medias
         for (Patron aux: this.centroidesBase){
         // comparar distancias 
         double distAux = patron.calcularDistancia(aux);
         if(distAux<distM){
           distM = distAux;
           nC =  aux.getClase();
         }
         
         //systemout
         
         }
        }
              
        return nC;
    }
}