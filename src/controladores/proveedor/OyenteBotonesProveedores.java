/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.proveedor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import BaseDatos.SQLProveedores;
import vistas.proveedor.*;


/**
 *
 * @author jarv
 */
public class OyenteBotonesProveedores implements ActionListener {

    private PanelProveedores panelProveedores;
    private SQLProveedores sqlProveedores;
    private PanelNuevoProveedor panelNuevoProveedor;
    private PanelModificarProveedor panelModificarProveedor;
    private PanelEliminarProveedor panelEliminarProveedor;
  

    public OyenteBotonesProveedores(SQLProveedores sqlProveedores, PanelProveedores panelProveedores) {
        this.panelProveedores = panelProveedores;
        this.sqlProveedores = sqlProveedores;
        panelNuevoProveedor = new PanelNuevoProveedor(sqlProveedores);
        panelModificarProveedor = new PanelModificarProveedor(sqlProveedores);
        panelEliminarProveedor = new PanelEliminarProveedor(sqlProveedores);
    
        
        panelProveedores.getPanelCentro().add(panelNuevoProveedor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component[] botones = panelProveedores.getPanelNorte().getComponents();
        JPanel panelCentro = panelProveedores.getPanelCentro();
        //panelProveedores.getPanelEste().add(ventanaBuscarProveedor);
        JButton boton = (JButton) e.getSource();
        panelCentro.removeAll();
        switch (boton.getName()) {
            case "nuevoProveedor":
                panelCentro.add(panelNuevoProveedor);
                break;
            case "modificarProveedor":
                panelCentro.add(panelModificarProveedor);
       
                break;
            case "eliminarProveedor":
                panelCentro.add(panelEliminarProveedor);
               
        }
        boton.setEnabled(false);
        for (int i = 0; i < botones.length; i++) {
            if(!botones[i].getName().equals(boton.getName())) {
                botones[i].setEnabled(true);
            }
        }
        panelCentro.revalidate();
        panelCentro.repaint();
    }

}
