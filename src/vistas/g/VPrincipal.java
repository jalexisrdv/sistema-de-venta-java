package vistas.g;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import modelos.g.CargarCategorias;

public class VPrincipal extends javax.swing.JPanel {
    
    private CargarCategorias cargaC;
    private VNuevo vnuevo;
    public VPrincipal(VNuevo vnuevo) {
        initComponents();
        this.vnuevo = vnuevo;
        this.getPanelCentral().add(vnuevo);
    }

    public void addEventos(ActionListener e){
        botonNuevo.addActionListener(e);
        botonModificar.addActionListener(e);
        botonEliminar.addActionListener(e);
        botonDepartamento.addActionListener(e);
//        ventasPeriodo.addActionListener(e);
//        botonPromociones.addActionListener(e);
        botonCatalogo.addActionListener(e);
        
        botonNuevo.setName("nuevo");
        botonModificar.setName("modificar");
        botonEliminar.setName("eliminar");
        botonDepartamento.setName("departamento");
//        ventasPeriodo.setName("periodo");
//        botonPromociones.setName("promociones");
        botonCatalogo.setName("catalogo");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelOpciones = new javax.swing.JPanel();
        botonNuevo = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonDepartamento = new javax.swing.JButton();
        botonCatalogo = new javax.swing.JButton();
        panelCentral = new javax.swing.JPanel();

        setBackground(new java.awt.Color(178, 230, 123));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gill Sans MT", 0, 24))); // NOI18N
        setLayout(new java.awt.BorderLayout());

        panelOpciones.setBackground(new java.awt.Color(178, 230, 123));
        panelOpciones.setBorder(javax.swing.BorderFactory.createLineBorder(getBackground(), 10));
        panelOpciones.setLayout(new java.awt.GridLayout(1, 0, 3, 0));

        botonNuevo.setBackground(new java.awt.Color(255, 195, 66));
        botonNuevo.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        botonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/futbol.png"))); // NOI18N
        botonNuevo.setText("Nuevo");
        botonNuevo.setPreferredSize(new java.awt.Dimension(150, 30));
        panelOpciones.add(botonNuevo);

        botonModificar.setBackground(new java.awt.Color(255, 195, 66));
        botonModificar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        botonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar (2).png"))); // NOI18N
        botonModificar.setText("Modificar");
        botonModificar.setPreferredSize(new java.awt.Dimension(150, 30));
        panelOpciones.add(botonModificar);

        botonEliminar.setBackground(new java.awt.Color(255, 195, 66));
        botonEliminar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar.png"))); // NOI18N
        botonEliminar.setText("Eliminar");
        botonEliminar.setPreferredSize(new java.awt.Dimension(150, 30));
        panelOpciones.add(botonEliminar);

        botonDepartamento.setBackground(new java.awt.Color(255, 195, 66));
        botonDepartamento.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        botonDepartamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/departamento.png"))); // NOI18N
        botonDepartamento.setText("Departamento");
        botonDepartamento.setPreferredSize(new java.awt.Dimension(150, 30));
        panelOpciones.add(botonDepartamento);

        botonCatalogo.setBackground(new java.awt.Color(255, 195, 66));
        botonCatalogo.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        botonCatalogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/catalago.png"))); // NOI18N
        botonCatalogo.setText("Catalogo");
        botonCatalogo.setPreferredSize(new java.awt.Dimension(150, 30));
        panelOpciones.add(botonCatalogo);

        add(panelOpciones, java.awt.BorderLayout.NORTH);

        panelCentral.setBackground(new java.awt.Color(211, 249, 170));
        add(panelCentral, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCatalogo;
    private javax.swing.JButton botonDepartamento;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonNuevo;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JPanel panelOpciones;
    // End of variables declaration//GEN-END:variables

    public JPanel getPanelCentral() {
        return panelCentral;
    }
}
