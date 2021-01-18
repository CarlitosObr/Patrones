/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.ArrayList;

/**
 *
 * @author carli
 */
public class Cap implements Clasificador{
    
    private double rendimiento;
    private MatrizConfusion matriz;
    private double[][] memoria;
    private ArrayList<String> clases;
    private Patron patronMedio;

    public Cap() {
        this.clases = new ArrayList<>();
    }

    
    @Override
    public void entrenar(ArrayList<Patron> instancias) {
       // determinar las clases participantes
       
       determinarClasesInvolucradas(instancias);
       // crear la matriz 
       this.memoria = new double[this.clases.size()][instancias.get(0).getVectorCa().length];
       // calcular el vector promedio para trasladar el conjunto fundamental
       calcularPatronMedio(instancias);
       // trasladar todo el junto fundamental
       for(Patron aux: instancias)
           aux.trasladar(patronMedio);
       // construir la memoria
       for(Patron aux: instancias){
       int indiceClase = this.clases.indexOf(aux.getClase());
       // acumulamos en la clase que le corresponde 
       for(int x=0; x<this.memoria[indiceClase].length;x++){
         this.memoria[indiceClase][x]+=aux.getVectorCa()[x];
       }
       }   
        imprimirMatriz(instancias);
       
    }
     private void imprimirMatriz(ArrayList<Patron> instancias) {
        for(int i = 0; i<this.clases.size();i++){
            for(int j= 0; j<instancias.get(0).getVectorCa().length; j++){
                System.out.print(""+this.memoria[i][j]+"\t");
            }
            System.out.println();
        }
    }
    @Override
    public void clasifica(Patron patron) {
       // trasladar el patron 
       // patron.trasladar(this.patronMedio);
       // ejecutamos la clasificación 
       // recorremos la memoria 
       
       double aso[] = new double[this.memoria.length];
       int iM = 0;
       for (int x=0; x<this.memoria.length;x++){
            double acu = 0;
            for (int y=0;y<this.memoria[x].length;y++){
              acu+=patron.getVectorCa()[y]*
                     this.memoria[x][y];
            }
            // asignamos la parte de la asociacion que 
            // corresponde
            aso[x]=acu;
            if (aso[x]>aso[iM]){
             iM = x;
            }
            patron.setClase_resultado(this.clases.get(iM));
       }  
       
        System.out.println();
    }

    @Override
    public void clasificaConjunto(ArrayList<Patron> conjunto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getRendimiento() {
       return this.rendimiento;
    }

    @Override
    public MatrizConfusion getMatriz() {
        return this.matriz;
    }

    private void determinarClasesInvolucradas(ArrayList<Patron> instancias) {
      // agregamos la primer clase
       this.clases.add(instancias.get(0).getClase());
      // recorremos el conjunto para encontrar clases
      for(int x=1;x<instancias.size();x++){
        if(!this.clases.contains(instancias.get(x).getClase())){
            // si no la contiene la agrega
            this.clases.add(instancias.get(x).getClase());
        }
      }
    }

    private void calcularPatronMedio(ArrayList<Patron> instancias) {
        // acumulamos en un patron existente
        this.patronMedio = new Patron(instancias.get(0).getVectorCa().length);
        // recorrer las instancias
        for (Patron aux: instancias){
           for(int x=0;x<aux.getVectorCa().length;x++){
               patronMedio.getVectorCa()[x]+=aux.getVectorCa()[x];
           }
        }
        // dividimos 
        for(int x=0;x<patronMedio.getVectorCa().length;x++){
               patronMedio.getVectorCa()[x]/=instancias.size();
           }
        
       
    }
    
    
}
