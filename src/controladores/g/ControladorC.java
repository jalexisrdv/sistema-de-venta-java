package controladores.g;

import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelos.g.*;
import vistas.g.*;

public class ControladorC implements ActionListener, DocumentListener {
    private final VCategoria ventanaC;
    private CargarProductos cargarP;
    

    public ControladorC(VCategoria ventanaC){
        this.ventanaC = ventanaC;
        cargarP = new CargarProductos();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
//        JList lista = ventanaC.getListaInternaD();
//        int filas = lista.getSelectedIndex();
//        if(filas >0){
//            int nombreCategoria =lista.getSelectedIndex();
//            buscarCategoria();
//        }
        JButton botonOrigen = (JButton)e.getSource();
        
        switch(botonOrigen.getName()){
            case "guardar":
            Categorias categoria = new Categorias();
            categoria.setNombre(ventanaC.getCampoNombreD().getText());
            int r = cargarP.registraCategoria(categoria);
            
            if(r>0){
                ventanaC.cargarCategorias();
                JOptionPane.showMessageDialog(null,"La categoria se registro correctamente"); 
            }else{
                JOptionPane.showMessageDialog(null,"Hubo un error en el registro");
            }
                break;
            case "cancelar":
                ventanaC.getCampoNombreD().setText("");
                break;
            case "eliminar":
                Categorias categoria2 = new Categorias();
                categoria2.setNombre(ventanaC.getListaInternaD().getSelectedValue()); 
                int res =cargarP.eliminarCategoria(categoria2);
                
                if(res > 0){
                    ventanaC.cargarCategorias();
                    JOptionPane.showMessageDialog(null,"La categoria se elimino Correctamente");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al eliminar la categoria");
                }     
        }  
    }
    
    public void buscarCategoria(){
        DefaultListModel lista =(DefaultListModel) ventanaC.getListaInternaD().getModel();
        String textoBuscar = ventanaC.getCampoBusquedaD().getText();
        cargarP.buscarCategoria(textoBuscar,lista);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
    buscarCategoria();
}
    @Override
    public void removeUpdate(DocumentEvent e) {
    buscarCategoria();
}

    @Override
    public void changedUpdate(DocumentEvent e) {
      buscarCategoria();
    }


}
