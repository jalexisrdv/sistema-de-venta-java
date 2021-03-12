package controladores.g;

import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelos.g.*;
import vistas.g.*;

public class ControladorCatalogo extends WindowAdapter implements ActionListener{
    private final VCatalogo ventanaCT;
    
    private CargarProductos cargarP;
    
    public ControladorCatalogo(VCatalogo ventanaCT){
        this.ventanaCT = ventanaCT;
        cargarP = new CargarProductos();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent origen = (JComponent)e.getSource();
        
        switch(origen.getName()){
            case "categoria":
                Categorias categoria = new Categorias();
                categoria.setId(ventanaCT.getComboDepartamentoC().getSelectedIndex());

                int columnas = cargarP.listarProductos(categoria);
                
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("CODIGO");
                modelo.addColumn("COLOR");
                modelo.addColumn("TAMAÑO");
                modelo.addColumn("MARCA");
                modelo.addColumn("DESCRIPCIÓN");
                modelo.addColumn("GANANCIA");
                modelo.addColumn("EXISTENCIA");
                modelo.addColumn("CATEGORIA");
                modelo.addColumn("COSTO COMPRA");
                modelo.addColumn("PRECIO VENTA");
                ventanaCT.getTablaC().setModel(modelo);       
            try{
                while(cargarP.getRs().next()){
                    Object[] filas = new Object[columnas];
                    for(int i=0; i<columnas; i++){
                        filas[i] = cargarP.getRs().getObject(i+1);
                    }
                    modelo.addRow(filas);
            }
            }catch(SQLException ex){
                System.out.println(ex);
        }         
        }
    }
    
 
    
}
