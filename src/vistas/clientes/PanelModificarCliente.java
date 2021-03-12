/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.clientes;

import controladores.clientes.OyenteModifcarCliente;
import javax.swing.JTable;
import javax.swing.JTextField;
import modelos.db.SQLClientes;

/**
 *
 * @author jarv
 */
public class PanelModificarCliente extends javax.swing.JPanel {

    /**
     * Creates new form PanelModificarCliente
     */
    public PanelModificarCliente(SQLClientes sqlClientes) {
        initComponents();
        addEventos(new OyenteModifcarCliente(sqlClientes, this));
    }
    
    public final void addEventos(OyenteModifcarCliente oyente) {
        this.botonAceptar.addActionListener(oyente);
        this.campoCliente.getDocument().addDocumentListener(oyente);
    }
    
    public JTable getTablaClientes() {
        return tablaClientes;
    }
    
    public JTextField getCampoCliente() {
        return campoCliente;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNorteTextos = new javax.swing.JPanel();
        etiquetaModificarCliente = new javax.swing.JLabel();
        etiquetaInformacionModificar = new javax.swing.JLabel();
        panelCentroTabla = new javax.swing.JPanel();
        panelTabla = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        campoCliente = new javax.swing.JTextField();
        panelBoton = new javax.swing.JPanel();
        botonAceptar = new javax.swing.JButton();

        setBackground(new java.awt.Color(192, 236, 245));
        setBorder(javax.swing.BorderFactory.createLineBorder(getBackground(), 10));
        setLayout(new java.awt.BorderLayout());

        panelNorteTextos.setBackground(new java.awt.Color(192, 236, 245));
        panelNorteTextos.setLayout(new java.awt.GridLayout(2, 0));

        etiquetaModificarCliente.setBackground(new java.awt.Color(192, 236, 245));
        etiquetaModificarCliente.setFont(new java.awt.Font("Myanmar Text", 1, 24)); // NOI18N
        etiquetaModificarCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaModificarCliente.setText("Modificar Cliente");
        panelNorteTextos.add(etiquetaModificarCliente);

        etiquetaInformacionModificar.setBackground(new java.awt.Color(192, 236, 245));
        etiquetaInformacionModificar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaInformacionModificar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaInformacionModificar.setText("Teclee el Número o Nombre del Cliente:");
        panelNorteTextos.add(etiquetaInformacionModificar);

        add(panelNorteTextos, java.awt.BorderLayout.PAGE_START);

        panelCentroTabla.setLayout(new java.awt.BorderLayout());

        panelTabla.setPreferredSize(new java.awt.Dimension(454, 200));

        tablaClientes.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido Paterno", "Apellido Materno", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelTabla.setViewportView(tablaClientes);

        panelCentroTabla.add(panelTabla, java.awt.BorderLayout.CENTER);

        campoCliente.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        panelCentroTabla.add(campoCliente, java.awt.BorderLayout.PAGE_START);

        add(panelCentroTabla, java.awt.BorderLayout.CENTER);

        panelBoton.setBackground(new java.awt.Color(192, 236, 245));
        panelBoton.setLayout(new java.awt.BorderLayout(0, 10));

        botonAceptar.setBackground(new java.awt.Color(255, 195, 66));
        botonAceptar.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        botonAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar (3).png"))); // NOI18N
        botonAceptar.setText("Aceptar");
        botonAceptar.setPreferredSize(new java.awt.Dimension(97, 50));
        panelBoton.add(botonAceptar, java.awt.BorderLayout.PAGE_END);

        add(panelBoton, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JTextField campoCliente;
    private javax.swing.JLabel etiquetaInformacionModificar;
    private javax.swing.JLabel etiquetaModificarCliente;
    private javax.swing.JPanel panelBoton;
    private javax.swing.JPanel panelCentroTabla;
    private javax.swing.JPanel panelNorteTextos;
    private javax.swing.JScrollPane panelTabla;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}