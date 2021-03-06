/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.proveedor;

import controladores.proveedor.*;
import BaseDatos.SQLProveedores;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author jarv
 */
public class PanelProveedores extends javax.swing.JPanel {

    /**
     * @param panelCentro the panelCentro to set
     */

    /**
     * Creates new form PanelClientes
     */
    public PanelProveedores() {
        initComponents();
        
        SQLProveedores sqlProveedores = new SQLProveedores();
        OyenteBotonesProveedores oyente = new OyenteBotonesProveedores(sqlProveedores, this);
        addEventos(oyente);
        this.botonNuevoProveedor.setEnabled(false);
    }
    
    public final void addEventos(OyenteBotonesProveedores oyente) {
        this.botonEliminarProveedor.addActionListener(oyente);
        this.botonModificarProveedor.addActionListener(oyente);
        this.botonNuevoProveedor.addActionListener(oyente);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNorte = new javax.swing.JPanel();
        botonModificarProveedor = new javax.swing.JButton();
        botonNuevoProveedor = new javax.swing.JButton();
        botonEliminarProveedor = new javax.swing.JButton();
        panelCentro = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PROVEEDOR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Britannic Bold", 0, 24))); // NOI18N
        setLayout(new java.awt.BorderLayout());

        botonModificarProveedor.setBackground(new java.awt.Color(153, 153, 255));
        botonModificarProveedor.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        botonModificarProveedor.setText("Modificar Proveedor      ");
        botonModificarProveedor.setName("modificarProveedor"); // NOI18N
        botonModificarProveedor.setPreferredSize(new java.awt.Dimension(150, 50));
        panelNorte.add(botonModificarProveedor);

        botonNuevoProveedor.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        botonNuevoProveedor.setText("Nuevo Proveedor");
        botonNuevoProveedor.setName("nuevoProveedor"); // NOI18N
        botonNuevoProveedor.setPreferredSize(new java.awt.Dimension(150, 50));
        panelNorte.add(botonNuevoProveedor);

        botonEliminarProveedor.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        botonEliminarProveedor.setText("Eliminar Proveedor");
        botonEliminarProveedor.setName("eliminarProveedor"); // NOI18N
        botonEliminarProveedor.setPreferredSize(new java.awt.Dimension(150, 50));
        panelNorte.add(botonEliminarProveedor);

        add(panelNorte, java.awt.BorderLayout.PAGE_START);

        panelCentro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(panelCentro, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public JPanel getPanelCentro() {
        return panelCentro;
    }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminarProveedor;
    private javax.swing.JButton botonModificarProveedor;
    private javax.swing.JButton botonNuevoProveedor;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JPanel panelNorte;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the panelNorte
     */
    public javax.swing.JPanel getPanelNorte() {
        return panelNorte;
    }
}
