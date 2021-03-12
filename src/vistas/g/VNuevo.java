package vistas.g;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import modelos.g.CargarCategorias;

public class VNuevo extends javax.swing.JPanel {
    
    private CargarCategorias cargaC;

    public VNuevo() {
        initComponents();
    }
    
    public void addEvents(ActionListener e){
        botonGuardar.addActionListener(e);
        botonCancelar.addActionListener(e);
        
        botonGuardar.setName("guardar");
        botonCancelar.setName("cancelar"); 
        
        cargaC = new CargarCategorias();
        cargarCategorias();
    }
    
    public void cargarCategorias() {
        try{
            cargaC.consultaCategorias();
            while(cargaC.getRs().next()){
                this.getComboCategoriasP().addItem(cargaC.getRs().getString("NOMBRE_CATEGORIA"));    
            }
            cargaC.getRs().close();
            
            cargaC.cargarProveedores();
            while(cargaC.getRs().next()){
                this.getComboProveedoresP().addItem(cargaC.getRs().getString("NOMBRE_PROVEEDOR"));    
            }
            cargaC.getRs().close();
            
        }catch(SQLException ex){
            System.out.println("En evento de ventana: "+ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNuevoProducto = new javax.swing.JPanel();
        panelValoresNuevos = new javax.swing.JPanel();
        etiquetaP1 = new javax.swing.JLabel();
        campoP1 = new javax.swing.JTextField();
        etiquetaP2 = new javax.swing.JLabel();
        campoP2 = new javax.swing.JTextField();
        etiquetaP3 = new javax.swing.JLabel();
        campoP3 = new javax.swing.JTextField();
        etiquetaP4 = new javax.swing.JLabel();
        campoP4 = new javax.swing.JTextField();
        etiquetaP5 = new javax.swing.JLabel();
        campoP5 = new javax.swing.JTextField();
        etiquetaP6 = new javax.swing.JLabel();
        campoP6 = new javax.swing.JTextField();
        etiquetaP7 = new javax.swing.JLabel();
        panelInternoPorcentaje = new javax.swing.JPanel();
        spinnerPorcentaje = new javax.swing.JSpinner();
        etiquetaPorcentajeP = new javax.swing.JLabel();
        etiquetaP9 = new javax.swing.JLabel();
        comboCategoriasP = new javax.swing.JComboBox<>();
        etiquetaP10 = new javax.swing.JLabel();
        campoP10 = new javax.swing.JTextField();
        etiqueta10 = new javax.swing.JLabel();
        panelProveedor = new javax.swing.JPanel();
        comboProveedoresP = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        panelSur = new javax.swing.JPanel();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(211, 249, 170));
        setBorder(javax.swing.BorderFactory.createLineBorder(getBackground(), 10));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLayout(new java.awt.BorderLayout());

        panelNuevoProducto.setBackground(new java.awt.Color(211, 249, 170));
        panelNuevoProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Myanmar Text", 1, 24))); // NOI18N
        panelNuevoProducto.setPreferredSize(new java.awt.Dimension(700,450));

        panelValoresNuevos.setBackground(new java.awt.Color(211, 249, 170));
        panelValoresNuevos.setRequestFocusEnabled(false);
        panelValoresNuevos.setLayout(new java.awt.GridLayout(14, 2, 5, 5));

        etiquetaP1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaP1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaP1.setText("Código de Barras:");
        panelValoresNuevos.add(etiquetaP1);

        campoP1.setColumns(10);
        campoP1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panelValoresNuevos.add(campoP1);

        etiquetaP2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaP2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaP2.setText("Color:");
        panelValoresNuevos.add(etiquetaP2);
        panelValoresNuevos.add(campoP2);

        etiquetaP3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaP3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaP3.setText("Tamaño:");
        panelValoresNuevos.add(etiquetaP3);
        panelValoresNuevos.add(campoP3);

        etiquetaP4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaP4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaP4.setText("Marca:");
        panelValoresNuevos.add(etiquetaP4);
        panelValoresNuevos.add(campoP4);

        etiquetaP5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaP5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaP5.setText("Descripción:");
        panelValoresNuevos.add(etiquetaP5);

        campoP5.setColumns(20);
        panelValoresNuevos.add(campoP5);

        etiquetaP6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaP6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaP6.setText("Precio costo:");
        panelValoresNuevos.add(etiquetaP6);

        campoP6.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        campoP6.setToolTipText("");
        panelValoresNuevos.add(campoP6);

        etiquetaP7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaP7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaP7.setText("Ganancia:");
        panelValoresNuevos.add(etiquetaP7);

        panelInternoPorcentaje.setBackground(new java.awt.Color(211, 249, 170));
        panelInternoPorcentaje.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        panelInternoPorcentaje.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        spinnerPorcentaje.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        spinnerPorcentaje.setToolTipText("");
        spinnerPorcentaje.setName(""); // NOI18N
        spinnerPorcentaje.setPreferredSize(new java.awt.Dimension(40,20));
        panelInternoPorcentaje.add(spinnerPorcentaje);

        etiquetaPorcentajeP.setText("%");
        panelInternoPorcentaje.add(etiquetaPorcentajeP);

        panelValoresNuevos.add(panelInternoPorcentaje);

        etiquetaP9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaP9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaP9.setText("Departamento");
        panelValoresNuevos.add(etiquetaP9);

        comboCategoriasP.setEditable(true);
        comboCategoriasP.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        comboCategoriasP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE..." }));
        panelValoresNuevos.add(comboCategoriasP);

        etiquetaP10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiquetaP10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaP10.setText("Hay:");
        panelValoresNuevos.add(etiquetaP10);

        campoP10.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panelValoresNuevos.add(campoP10);

        etiqueta10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        etiqueta10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiqueta10.setText("Proveedor:");
        panelValoresNuevos.add(etiqueta10);

        panelProveedor.setBackground(new java.awt.Color(211, 249, 170));

        comboProveedoresP.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        comboProveedoresP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE..." }));
        panelProveedor.add(comboProveedoresP);

        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton1.setText("AGREGAR PROVEEDOR");
        panelProveedor.add(jButton1);

        panelValoresNuevos.add(panelProveedor);

        javax.swing.GroupLayout panelNuevoProductoLayout = new javax.swing.GroupLayout(panelNuevoProducto);
        panelNuevoProducto.setLayout(panelNuevoProductoLayout);
        panelNuevoProductoLayout.setHorizontalGroup(
            panelNuevoProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoProductoLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(panelValoresNuevos, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        panelNuevoProductoLayout.setVerticalGroup(
            panelNuevoProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoProductoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelValoresNuevos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        add(panelNuevoProducto, java.awt.BorderLayout.CENTER);

        panelSur.setBackground(new java.awt.Color(211, 249, 170));
        panelSur.setPreferredSize(new java.awt.Dimension(550,100));
        panelSur.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 150, 5));

        botonGuardar.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        botonGuardar.setText("Guardar");
        botonGuardar.setPreferredSize(new java.awt.Dimension(150, 30));
        panelSur.add(botonGuardar);

        botonCancelar.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        botonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar.png"))); // NOI18N
        botonCancelar.setPreferredSize(new java.awt.Dimension(150, 30));
        panelSur.add(botonCancelar);

        add(panelSur, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JTextField campoP1;
    private javax.swing.JTextField campoP10;
    private javax.swing.JTextField campoP2;
    private javax.swing.JTextField campoP3;
    private javax.swing.JTextField campoP4;
    private javax.swing.JTextField campoP5;
    private javax.swing.JTextField campoP6;
    private javax.swing.JComboBox<String> comboCategoriasP;
    private javax.swing.JComboBox<String> comboProveedoresP;
    private javax.swing.JLabel etiqueta10;
    private javax.swing.JLabel etiquetaP1;
    private javax.swing.JLabel etiquetaP10;
    private javax.swing.JLabel etiquetaP2;
    private javax.swing.JLabel etiquetaP3;
    private javax.swing.JLabel etiquetaP4;
    private javax.swing.JLabel etiquetaP5;
    private javax.swing.JLabel etiquetaP6;
    private javax.swing.JLabel etiquetaP7;
    private javax.swing.JLabel etiquetaP9;
    private javax.swing.JLabel etiquetaPorcentajeP;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panelInternoPorcentaje;
    private javax.swing.JPanel panelNuevoProducto;
    private javax.swing.JPanel panelProveedor;
    private javax.swing.JPanel panelSur;
    private javax.swing.JPanel panelValoresNuevos;
    private javax.swing.JSpinner spinnerPorcentaje;
    // End of variables declaration//GEN-END:variables

    public JTextField getCampoP1() {
        return campoP1;
    }

    public JTextField getCampoP10() {
        return campoP10;
    }

    public JTextField getCampoP2() {
        return campoP2;
    }

    public JTextField getCampoP3() {
        return campoP3;
    }

    public JTextField getCampoP4() {
        return campoP4;
    }

    public JTextField getCampoP5() {
        return campoP5;
    }

    public JTextField getCampoP6() {
        return campoP6;
    }

//    public JTextField getCampoP8() {
//        return campoP8;
//    }

    public JComboBox<String> getComboCategoriasP() {
        return comboCategoriasP;
    }

    public JSpinner getSpinnerPorcentaje() {
        return spinnerPorcentaje;
    }

    public void setCampoP1(JTextField campoP1) {
        this.campoP1 = campoP1;
    }

    public void setCampoP10(JTextField campoP10) {
        this.campoP10 = campoP10;
    }

    public void setCampoP2(JTextField campoP2) {
        this.campoP2 = campoP2;
    }

    public void setCampoP3(JTextField campoP3) {
        this.campoP3 = campoP3;
    }

    public void setCampoP4(JTextField campoP4) {
        this.campoP4 = campoP4;
    }

    public void setCampoP5(JTextField campoP5) {
        this.campoP5 = campoP5;
    }

//    public void setCampoP8(JTextField campoP8) {
//        this.campoP8 = campoP8;
//    }

    public void setComboCategoriasP(JComboBox<String> comboCategoriasP) {
        this.comboCategoriasP = comboCategoriasP;
    }

    public void setSpinnerPorcentaje(JSpinner spinnerPorcentaje) {
        this.spinnerPorcentaje = spinnerPorcentaje;
    }

    public JComboBox<String> getComboProveedoresP() {
        return comboProveedoresP;
    }

    public JLabel getEtiquetaP10() {
        return etiquetaP10;
    }

    public JPanel getPanelProveedor() {
        return panelProveedor;
    }
    

}
