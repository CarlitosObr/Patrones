/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author carli
 */
public class Table extends JFrame{
    JTable table;
    
    
    
    public Table(int[][] matriz,ArrayList<Patron> instancias){
        setLayout(new FlowLayout());
        table = new javax.swing.JTable();
       // table.setModel(dataModel);
       table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                
            }));
        table.setPreferredScrollableViewportSize(new Dimension(500,60));
        table.setFillsViewportHeight(true);
        
        JScrollPane scroollPane = new JScrollPane(table);
        add(scroollPane);
        DefaultTableModel model =  (DefaultTableModel)table.getModel();
        model.setRowCount(instancias.size()+1);
        model.setColumnCount(instancias.size()+1);
        
        for(int i = 0; i<=instancias.size();i++){
            if(i == 0){
                table.setValueAt("", 0, 0);
            }else{
                table.setValueAt(instancias.get(i-1).getClase(), i, i);
            }
        }
        for(int x = 1; x<=instancias.size(); x++){
            for(int k = 1; k<=instancias.size(); k++){
                table.setValueAt(matriz[x-1][k-1], x, k);
            }
        }
        //table.setPreferredScrollableViewportSize(new Dimensions(500,50));
    }   
    public Table(Object[][] matriz,String[] nombres){
        setLayout(new FlowLayout());
        table = new JTable(matriz,nombres);
       
      
        table.setPreferredScrollableViewportSize(new Dimension(500,48));
        table.setFillsViewportHeight(true);
        
        JScrollPane scroollPane = new JScrollPane(table);
        add(scroollPane);
        
        //table.setPreferredScrollableViewportSize(new Dimensions(500,50));
    }   
}
