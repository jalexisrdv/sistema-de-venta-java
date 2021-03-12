/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.proveedor;

import controladores.proveedor.*;
import javax.swing.JTable;
import javax.swing.JTextField;
import BaseDatos.SQLProveedores;

/**
 *
 * @author jarv
 */
public class VentanaCamposModificarProveedor extends javax.swing.JDialog {
    
    private long telefono;
    private JTable tablaProveedores;
    private JTextField campoProveedores;

    /**
     * Creates new form VentanaCamposModificarCliente
     */
    public VentanaCamposModificarProveedor(SQLProveedores sqlProveedores, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        addEventos(new OyenteCamposModificarProveedor(sqlProveedores, this));
    }

    public final void addEventos(OyenteCamposModificarProveedor oyente) {
        this.botonCancelar.addActionListener(oyente);
        this.botonGuardarCambiosProveedor.addActionListener(oyente);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorderLine = new javax.swing.JPanel();
        panelSurBotones = new javax.swing.JPanel();
        botonGuardarCambiosProveedor = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        panelCentroCampos = new javax.swing.JPanel();
        panelEtiquetas = new javax.swing.JPanel();
        etiquetaNombre = new javax.swing.JLabel();
        etiquetaApellidoP = new javax.swing.JLabel();
        etiquetaApellidoM = new javax.swing.JLabel();
        etiquetaSexo = new javax.swing.JLabel();
        etiquetaTelefono = new javax.swing.JLabel();
        etiquetaEmail = new javax.swing.JLabel();
        etiquetaEmail1 = new javax.swing.JLabel();
        etiquetaEmail2 = new javax.swing.JLabel();
        panelCampos = new javax.swing.JPanel();
        campoCompania = new javax.swing.JTextField();
        campoEmail = new javax.swing.JTextField();
        campoTelefono = new javax.swing.JTextField();
        campoCalle = new javax.swing.JTextField();
        campoColonia = new javax.swing.JTextField();
        campoEstado = new javax.swing.JTextField();
        campoCiudad = new javax.swing.JTextField();
        campoCodigoPostal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        panelBorderLine.setBorder(javax.swing.BorderFactory.createLineBorder(getBackground(), 20));
        panelBorderLine.setLayout(new java.awt.BorderLayout());

        botonGuardarCambiosProveedor.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        botonGuardarCambiosProveedor.setText("Guardar Cambios");
        botonGuardarCambiosProveedor.setToolTipText("");
        botonGuardarCambiosProveedor.setName("botonGuardarCambios"); // NOI18N
        botonGuardarCambiosProveedor.setPreferredSize(new java.awt.Dimension(141, 50));
        panelSurBotones.add(botonGuardarCambiosProveedor);

        botonCancelar.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        botonCancelar.setText("Cancelar");
        botonCancelar.setName("botonCancelar"); // NOI18N
        botonCancelar.setPreferredSize(new java.awt.Dimension(98, 50));
        panelSurBotones.add(botonCancelar);

        panelBorderLine.add(panelSurBotones, java.awt.BorderLayout.PAGE_END);

        panelCentroCampos.setLayout(new java.awt.BorderLayout());

        panelEtiquetas.setBorder(javax.swing.BorderFactory.createLineBorder(getBackground(), 5));
        panelEtiquetas.setLayout(new java.awt.GridLayout(8, 1, 0, 15));

        etiquetaNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre.setText("Nombre Compañía:");
        panelEtiquetas.add(etiquetaNombre);

        etiquetaApellidoP.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaApellidoP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaApellidoP.setText("E-mail:");
        panelEtiquetas.add(etiquetaApellidoP);

        etiquetaApellidoM.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaApellidoM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaApellidoM.setText("Teléfono:");
        panelEtiquetas.add(etiquetaApellidoM);

        etiquetaSexo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaSexo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaSexo.setText("Calle:");
        panelEtiquetas.add(etiquetaSexo);

        etiquetaTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaTelefono.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaTelefono.setText("Colonia:");
        panelEtiquetas.add(etiquetaTelefono);

        etiquetaEmail.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaEmail.setText("Estado:");
        panelEtiquetas.add(etiquetaEmail);

        etiquetaEmail1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaEmail1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaEmail1.setText("Ciudad:");
        panelEtiquetas.add(etiquetaEmail1);

        etiquetaEmail2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaEmail2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaEmail2.setText("Código Postal:");
        panelEtiquetas.add(etiquetaEmail2);

        panelCentroCampos.add(panelEtiquetas, java.awt.BorderLayout.WEST);

        panelCampos.setBorder(javax.swing.BorderFactory.createLineBorder(getBackground(), 5));
        panelCampos.setLayout(new java.awt.GridLayout(8, 1, 0, 15));

        campoCompania.setColumns(20);
        panelCampos.add(campoCompania);

        campoEmail.setColumns(20);
        panelCampos.add(campoEmail);

        campoTelefono.setColumns(20);
        panelCampos.add(campoTelefono);

        campoCalle.setColumns(20);
        panelCampos.add(campoCalle);

        campoColonia.setColumns(20);
        panelCampos.add(campoColonia);

        campoEstado.setColumns(20);
        panelCampos.add(campoEstado);

        campoCiudad.setColumns(20);
        panelCampos.add(campoCiudad);

        campoCodigoPostal.setColumns(20);
        panelCampos.add(campoCodigoPostal);

        panelCentroCampos.add(panelCampos, java.awt.BorderLayout.CENTER);

        panelBorderLine.add(panelCentroCampos, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelBorderLine, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setTelefonoCopia(long telefono) {
        this.telefono = telefono;
    }
    
    public long getTelefonoCopia() {
        return telefono;
    }
    
    /**
     * @return the campoApellidoM
     */
    public javax.swing.JTextField getCampoTelefono() {
        return campoTelefono;
    }

    /**
     * @return the campoApellidoP
     */
    public javax.swing.JTextField getCampoEmail() {
        return campoEmail;
    }

    /**
     * @return the campoEmail
     */
    public javax.swing.JTextField getCampoColonia() {
        return campoColonia;
    }

    /**
     * @return the campoNombre
     */
    public javax.swing.JTextField getCampoCompania() {
        return campoCompania;
    }

    /**
     * @return the campoTelefono
     */
    public javax.swing.JTextField getCampoCalle() {
        return campoCalle;
    }

    /**
     * @return the tablaClientes
     */
    public JTable getTablaProveedores() {
        return tablaProveedores;
    }

    /**
     * @param tablaProveedores the tablaClientes to set
     */
    public void setTablaProveedores(JTable tablaProveedores) {
        this.tablaProveedores = tablaProveedores;
    }

    /**
     * @return the campoClientes
     */
    public JTextField getCampoProveedor() {
        return campoProveedores;
    }

    /**
     * @param campoProveedores the campoClientes to set
     */
    public void setCampoProveedor(JTextField campoProveedores) {
        this.campoProveedores = campoProveedores;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonGuardarCambiosProveedor;
    private javax.swing.JTextField campoCalle;
    private javax.swing.JTextField campoCiudad;
    private javax.swing.JTextField campoCodigoPostal;
    private javax.swing.JTextField campoColonia;
    private javax.swing.JTextField campoCompania;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoEstado;
    private javax.swing.JTextField campoTelefono;
    private javax.swing.JLabel etiquetaApellidoM;
    private javax.swing.JLabel etiquetaApellidoP;
    private javax.swing.JLabel etiquetaEmail;
    private javax.swing.JLabel etiquetaEmail1;
    private javax.swing.JLabel etiquetaEmail2;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JLabel etiquetaSexo;
    private javax.swing.JLabel etiquetaTelefono;
    private javax.swing.JPanel panelBorderLine;
    private javax.swing.JPanel panelCampos;
    private javax.swing.JPanel panelCentroCampos;
    private javax.swing.JPanel panelEtiquetas;
    private javax.swing.JPanel panelSurBotones;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the campoCiudad
     */
    public javax.swing.JTextField getCampoCiudad() {
        return campoCiudad;
    }

    /**
     * @return the campoCodigoPostal
     */
    public javax.swing.JTextField getCampoCodigoPostal() {
        return campoCodigoPostal;
    }

    /**
     * @return the campoEstado
     */
    public javax.swing.JTextField getCampoEstado() {
        return campoEstado;
    }

}
