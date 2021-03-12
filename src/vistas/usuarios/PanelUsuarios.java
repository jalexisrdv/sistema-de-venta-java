/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.usuarios;

import controladores.usuarios.OyenteBotonesUsuario;
import javax.swing.JPanel;
import modelos.db.SQLUsuario;

/**
 *
 * @author jarv
 */
public class PanelUsuarios extends javax.swing.JPanel {

   
    /**
     * Creates new form PanelClientes
     */
    public PanelUsuarios() {
        initComponents();
//        this.panelCentro.add(new PanelNuevoUsuario());
        SQLUsuario sqlUsuario = new SQLUsuario();
        OyenteBotonesUsuario oyente = new OyenteBotonesUsuario(sqlUsuario, this);
        addEventos(oyente);
    }
    
    public void addEventos(OyenteBotonesUsuario oyente) {
        this.botonEliminarUsuario.addActionListener(oyente);
        this.botonModificarUsuario.addActionListener(oyente);
        this.botonNuevoUsuario.addActionListener(oyente);
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
        botonNuevoUsuario = new javax.swing.JButton();
        botonModificarUsuario = new javax.swing.JButton();
        botonEliminarUsuario = new javax.swing.JButton();
        panelCentro = new javax.swing.JPanel();

        setBackground(new java.awt.Color(228, 126, 194));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gill Sans MT", 0, 24))); // NOI18N
        setLayout(new java.awt.BorderLayout());

        panelNorte.setBackground(new java.awt.Color(228, 126, 194));

        botonNuevoUsuario.setBackground(new java.awt.Color(255, 195, 66));
        botonNuevoUsuario.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        botonNuevoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anadir.png"))); // NOI18N
        botonNuevoUsuario.setText("Nuevo");
        botonNuevoUsuario.setName("nuevoUsuario"); // NOI18N
        botonNuevoUsuario.setPreferredSize(new java.awt.Dimension(150, 50));
        panelNorte.add(botonNuevoUsuario);

        botonModificarUsuario.setBackground(new java.awt.Color(255, 195, 66));
        botonModificarUsuario.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        botonModificarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        botonModificarUsuario.setText("Modificar");
        botonModificarUsuario.setName("modificarUsuario"); // NOI18N
        botonModificarUsuario.setPreferredSize(new java.awt.Dimension(150, 50));
        botonModificarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarUsuarioActionPerformed(evt);
            }
        });
        panelNorte.add(botonModificarUsuario);

        botonEliminarUsuario.setBackground(new java.awt.Color(255, 195, 66));
        botonEliminarUsuario.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        botonEliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        botonEliminarUsuario.setText("Eliminar");
        botonEliminarUsuario.setName("eliminarUsuario"); // NOI18N
        botonEliminarUsuario.setPreferredSize(new java.awt.Dimension(150, 50));
        panelNorte.add(botonEliminarUsuario);

        add(panelNorte, java.awt.BorderLayout.PAGE_START);

        panelCentro.setBackground(new java.awt.Color(243, 194, 227));
        add(panelCentro, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void botonModificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonModificarUsuarioActionPerformed

    public JPanel getPanelCentro() {
        return panelCentro;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminarUsuario;
    private javax.swing.JButton botonModificarUsuario;
    private javax.swing.JButton botonNuevoUsuario;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JPanel panelNorte;
    // End of variables declaration//GEN-END:variables
}