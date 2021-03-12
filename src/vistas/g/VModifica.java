/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas.g;

import controladores.g.ControladorModifica;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JTextField;
import modelos.g.CargarCategorias;

/**
 *
 * @author GenaroLM
 */
public class VModifica extends javax.swing.JPanel {
    
//    private CargarCategorias cargaC;

    /** Creates new form VentanaModificar */
    public VModifica() {
        initComponents();
    }
    
    public void addEventos(ControladorModifica e){
        this.botonModifica.addActionListener(e);
        this.campoModifica.addKeyListener(e);
        
        this.botonModifica.setName("aceptar");
    }
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelcentral = new javax.swing.JPanel();
        campoModifica = new javax.swing.JTextField();
        botonModifica = new javax.swing.JButton();

        setBackground(new java.awt.Color(211, 249, 170));
        setBorder(javax.swing.BorderFactory.createLineBorder(getBackground(), 10));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        setLayout(flowLayout1);

        panelcentral.setBackground(new java.awt.Color(211, 249, 170));
        panelcentral.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modifica Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N
        panelcentral.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelcentral.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        campoModifica.setColumns(20);
        campoModifica.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoModifica.setToolTipText("");
        panelcentral.add(campoModifica);

        botonModifica.setBackground(new java.awt.Color(255, 195, 66));
        botonModifica.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        botonModifica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        botonModifica.setText("Aceptar");
        botonModifica.setPreferredSize(new java.awt.Dimension(150, 30));
        panelcentral.add(botonModifica);

        add(panelcentral);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonModifica;
    private javax.swing.JTextField campoModifica;
    private javax.swing.JPanel panelcentral;
    // End of variables declaration//GEN-END:variables

    public JTextField getCampoModifica() {
        return campoModifica;
    }
}
