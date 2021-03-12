/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelos.db.SQLVentas;
import vistas.ventas.VentanaBuscarProducto;

/**
 *
 * @author jarv
 */
public class OyenteBuscarProducto extends KeyAdapter implements ActionListener, DocumentListener {

    private SQLVentas sqlVentas;
    private VentanaBuscarProducto ventanaBuscarProducto;
    private int numFilaSeleccionada;

    public OyenteBuscarProducto(SQLVentas sqlVentas, VentanaBuscarProducto ventanaBuscarProducto) {
        this.sqlVentas = sqlVentas;
        this.ventanaBuscarProducto = ventanaBuscarProducto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        switch (boton.getName()) {
            case "botonModificar":
                System.out.println("modificar");
                break;
            case "botonEliminar":
                System.out.println("eliminar");
            case "botonAceptar":
                agregarProductoBuscadoAVentas();
                break;
            case "botonCancelar":
                ventanaBuscarProducto.setVisible(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                agregarProductoBuscadoAVentas();
                break;
            case KeyEvent.VK_ESCAPE:
                ventanaBuscarProducto.setVisible(false);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String descripcion = ventanaBuscarProducto.getCampoBuscarProducto().getText();
        sqlVentas.buscarProducto(descripcion);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String descripcion = ventanaBuscarProducto.getCampoBuscarProducto().getText();
        sqlVentas.buscarProducto(descripcion);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String descripcion = ventanaBuscarProducto.getCampoBuscarProducto().getText();
        sqlVentas.buscarProducto(descripcion);
    }

    public void agregarProductoBuscadoAVentas() {
        DefaultTableModel modeloTablaBuscarProducto = (DefaultTableModel) ventanaBuscarProducto.getTablaBuscarProducto().getModel();
        DefaultTableModel modeloTablaVenta = sqlVentas.getDefaultModeloTablaVenta();
        numFilaSeleccionada = ventanaBuscarProducto.getTablaBuscarProducto().getSelectedRow();
        if (numFilaSeleccionada >= 0) {
            long codigoBarras = (long) modeloTablaBuscarProducto.getValueAt(numFilaSeleccionada, 0);
            boolean validaExistente = sqlVentas.validarFilasExistentes(codigoBarras);
            if (!validaExistente) {
                int existente = (int) modeloTablaBuscarProducto.getValueAt(numFilaSeleccionada, 4);
                existente--;
                Object[] producto = {
                    modeloTablaBuscarProducto.getValueAt(numFilaSeleccionada, 0),
                    modeloTablaBuscarProducto.getValueAt(numFilaSeleccionada, 1),
                    modeloTablaBuscarProducto.getValueAt(numFilaSeleccionada, 2),
                    1,
                    existente
                };
                modeloTablaVenta.addRow(producto);
                sumarPrecioYCantidad();
            }
            ventanaBuscarProducto.setVisible(false);
        }
    }

    public void sumarPrecioYCantidad() {
        DefaultTableModel modeloTablaVentas = sqlVentas.getDefaultModeloTablaVenta();
        int sumCantidadProductos = 0;
        double sumPreciosProductos = 0;
        for (int i = 0; i < modeloTablaVentas.getRowCount(); i++) {
            int cantidadProductos = (int) modeloTablaVentas.getValueAt(i, 3);
            double precioProductos = (double) modeloTablaVentas.getValueAt(i, 2);
            sumPreciosProductos += precioProductos * cantidadProductos;
            sumCantidadProductos += cantidadProductos;
        }
        ventanaBuscarProducto.getEtiquetaMonto().setText("$" + sumPreciosProductos + "");
        ventanaBuscarProducto.getEtiquetaNumeroProducto().setText(sumCantidadProductos + " Productos en la Venta Actual");
    }

}
