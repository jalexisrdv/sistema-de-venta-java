/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.ventas;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.db.SQLVentas;
import utilidades.UtilVentanas;
import vistas.ventas.PanelVentas;
import vistas.ventas.VentanaBuscarProducto;
import vistas.ventas.VentanaCobrarProducto;
import vistas.ventas.VentanaVerificador;

/**
 *
 * @author jarv
 */
public class OyentesMultiplesVenta extends KeyAdapter implements ActionListener {

    private PanelVentas panelVentas;
    private SQLVentas sqlVentas;
    private int existencia;
    private VentanaBuscarProducto ventanaBuscarProducto;
    private VentanaVerificador ventanaVerificador;
    private VentanaCobrarProducto ventanaCobrarProducto;

    public OyentesMultiplesVenta(SQLVentas sqlVentas, PanelVentas panelVentas) {
        this.panelVentas = panelVentas;
        this.sqlVentas = sqlVentas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int numFilas;
        JComponent componente = (JComponent) e.getSource();
        JFrame ventanaPadre = UtilVentanas.getVentanaPadre();
        JTextField campoCodigoBarras = null;
        switch (componente.getName()) {
            case "agregarProducto":
            case "botonAgregarProducto":
                campoCodigoBarras = panelVentas.getCampoCodigoBarras();
                String idProducto = campoCodigoBarras.getText();
                sqlVentas.agregarProductoTabla(idProducto);
                campoCodigoBarras.setText("");
                break;
            case "botonBuscar":
                ventanaBuscarProducto = new VentanaBuscarProducto(sqlVentas, ventanaPadre, true);
                ventanaBuscarProducto.setEtiquetaMonto(panelVentas.getEtiquetaMonto());
                ventanaBuscarProducto.setEtiquetaNumeroProducto(panelVentas.getEtiquetaNumeroProductos());
                UtilVentanas.abrirVentanaDialogo(ventanaPadre,
                        ventanaBuscarProducto, 800, 500);
                ventanaBuscarProducto.setVisible(true);
                break;
            case "botonBorrar":
                numFilas = sqlVentas.getTablaVentasProductos().getRowCount();
                if (numFilas > 0) {
                    int filaSeleccionada = sqlVentas.getTablaVentasProductos().getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        DefaultTableModel modeloTablaVenta = sqlVentas.getDefaultModeloTablaVenta();
                        modeloTablaVenta.removeRow(sqlVentas.getTablaVentasProductos().getSelectedRow());
                    }
                    sumarPrecioYCantidad();
                }
                if (numFilas == 1) {
                    panelVentas.getBotonCobrar().setEnabled(false);
                }
                break;
            case "botonVerificar":
                ventanaVerificador = new VentanaVerificador(sqlVentas, ventanaPadre, true);
                ventanaVerificador.setEtiquetaMonto(panelVentas.getEtiquetaMonto());
                ventanaVerificador.setEtiquetaNumeroProducto(panelVentas.getEtiquetaNumeroProductos());
                UtilVentanas.abrirVentanaDialogo(ventanaPadre,
                        ventanaVerificador, 500, 350);
                ventanaVerificador.setVisible(true);
                break;
            case "botonCobrar":
                ventanaCobrarProducto = new VentanaCobrarProducto(sqlVentas, ventanaPadre, true);
                ventanaCobrarProducto.setEtiquetaMontoVentas(panelVentas.getEtiquetaMonto());
                ventanaCobrarProducto.setEtiquetaNumeroProductosVentas(panelVentas.getEtiquetaNumeroProductos());
                UtilVentanas.abrirVentanaDialogo(ventanaPadre,
                        ventanaCobrarProducto, 550, 450);
                ventanaCobrarProducto.setBotonCobrarProductos(panelVentas.getBotonCobrar());
                ventanaCobrarProducto.setVisible(true);
        }
        if (!componente.getName().equals("agregarProducto")) {
            ventanaPadre.requestFocus();
        }
        if (componente.getName().equals("botonAgregarProducto")) {
            campoCodigoBarras.requestFocus();
        }
        numFilas = sqlVentas.getTablaVentasProductos().getRowCount();
        if (numFilas > 0) {
            sumarPrecioYCantidad();
            panelVentas.getBotonCobrar().setEnabled(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        JFrame ventanaPadre = UtilVentanas.getVentanaPadre();
        /*FALTABA CREAR LAS INSTANCIAS DE LA MISMA FORMA QUE SE HACEN EN EL METODO actionPerformed
        Y AGREGAR EL CODIGO PARA ACTIVAR EL BOTON COBRAR CUANDO HAY PRODUCTOS EN LA TABLA*/
        switch (e.getKeyCode()) {
            case KeyEvent.VK_F10: //Ventana Buscar Producto
                ventanaBuscarProducto = new VentanaBuscarProducto(sqlVentas, ventanaPadre, true);
                ventanaBuscarProducto.setEtiquetaMonto(panelVentas.getEtiquetaMonto());
                ventanaBuscarProducto.setEtiquetaNumeroProducto(panelVentas.getEtiquetaNumeroProductos());
                UtilVentanas.abrirVentanaDialogo(ventanaPadre,
                        ventanaBuscarProducto, 800, 500);
                ventanaBuscarProducto.setVisible(true);
                break;
            case KeyEvent.VK_F9: //Ventana Verificar Precio
                ventanaVerificador = new VentanaVerificador(sqlVentas, ventanaPadre, true);
                ventanaVerificador.setEtiquetaMonto(panelVentas.getEtiquetaMonto());
                ventanaVerificador.setEtiquetaNumeroProducto(panelVentas.getEtiquetaNumeroProductos());
                UtilVentanas.abrirVentanaDialogo(ventanaPadre,
                        ventanaVerificador, 500, 350);
                ventanaVerificador.setVisible(true);
                break;
            case KeyEvent.VK_F12: //Ventana Cobrar Producto
                ventanaCobrarProducto = new VentanaCobrarProducto(sqlVentas, ventanaPadre, true);
                ventanaCobrarProducto.setEtiquetaMontoVentas(panelVentas.getEtiquetaMonto());
                ventanaCobrarProducto.setEtiquetaNumeroProductosVentas(panelVentas.getEtiquetaNumeroProductos());
                UtilVentanas.abrirVentanaDialogo(ventanaPadre,
                        ventanaCobrarProducto, 550, 450);
                ventanaCobrarProducto.setBotonCobrarProductos(panelVentas.getBotonCobrar());
                ventanaCobrarProducto.setVisible(true);
                break;
            case Event.DELETE: //Eliminar Producto de Tabla
                int numFilas = sqlVentas.getTablaVentasProductos().getRowCount();
                if (numFilas > 0) {
                    int filaSeleccionada = sqlVentas.getTablaVentasProductos().getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        DefaultTableModel modeloTablaVenta = sqlVentas.getDefaultModeloTablaVenta();
                        modeloTablaVenta.removeRow(sqlVentas.getTablaVentasProductos().getSelectedRow());
                    }
                    sumarPrecioYCantidad();
                }
                if (numFilas == 1) {
                    panelVentas.getBotonCobrar().setEnabled(false);
                }
        }
        
        
        //ESTE CODIGO VALIDA SI HAY PRODUCTOS EN LA TABLA
        int numFilas = sqlVentas.getTablaVentasProductos().getRowCount();
        
        if (numFilas > 0) {
            sumarPrecioYCantidad();
            panelVentas.getBotonCobrar().setEnabled(true);
        }
        

        //Gestionando eventos para incrementar y disminuir la cantidad del producto a vender
        if (e.getKeyCode() == 93 || e.getKeyCode() == 47) {
            DefaultTableModel modeloTablaVentas = sqlVentas.getDefaultModeloTablaVenta();
            int filaSeleccionada = sqlVentas.getTablaVentasProductos().getSelectedRow();
            int cantidad = (int) modeloTablaVentas.getValueAt(filaSeleccionada, 3);
            int existenciaActual = (int) modeloTablaVentas.getValueAt(filaSeleccionada, 4);;
            existencia = cantidad + existenciaActual;
            switch (e.getKeyCode()) {
                case 93: //PRESIONO TECLA +
                    if (cantidad < existencia) {
                        cantidad++;
                    }
                    break;
                case 47: //PRESIONO TECLA -
                    if (cantidad > 1) {
                        cantidad--;
                    }
            }
            modeloTablaVentas.setValueAt(cantidad, filaSeleccionada, 3);
            modeloTablaVentas.setValueAt(existencia - cantidad, filaSeleccionada, 4);
            sumarPrecioYCantidad();
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
        panelVentas.getEtiquetaMonto().setText("$" + sumPreciosProductos + "");
        panelVentas.getEtiquetaNumeroProductos().setText(sumCantidadProductos + " Productos en la Venta Actual");
    }

}
