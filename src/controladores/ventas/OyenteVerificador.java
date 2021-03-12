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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import modelos.db.SQLVentas;
import vistas.ventas.VentanaVerificador;

/**
 *
 * @author jarv
 */
public class OyenteVerificador extends KeyAdapter implements ActionListener {

    private SQLVentas sqlVentas;
    VentanaVerificador ventanaVerificador;

    public OyenteVerificador(SQLVentas sqlVentas, VentanaVerificador ventanaVerificador) {
        this.ventanaVerificador = ventanaVerificador;
        this.sqlVentas = sqlVentas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent componente = (JComponent) e.getSource();
        switch (componente.getName()) {
            case "campoVerificarPrecio":
                Object[] descripcionYPrecio = sqlVentas.consultarPrecioProducto(ventanaVerificador.getCampoVerificarPrecio().getText());
                if (descripcionYPrecio != null) {
                    ventanaVerificador.getEtiquetaDescripcionProducto().setText(descripcionYPrecio[0].toString());
                    ventanaVerificador.getEtiquetaPrecio().setText(descripcionYPrecio[1].toString());
                }
                break;
            case "botonAgregarVenta":
                sqlVentas.agregarProductoTabla(ventanaVerificador.getCampoVerificarPrecio().getText());
                sumarPrecioYCantidad();
                ventanaVerificador.setVisible(false);
                break;
            case "botonCancelar":
                ventanaVerificador.setVisible(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_F1:
                String etiquetaPrecio = ventanaVerificador.getEtiquetaPrecio().getText();
                if (!etiquetaPrecio.equals("$0.0")) {
                    sqlVentas.agregarProductoTabla(ventanaVerificador.getCampoVerificarPrecio().getText());
                    sumarPrecioYCantidad();
                    ventanaVerificador.setVisible(false);
                }
                break;
            case KeyEvent.VK_ESCAPE:
                ventanaVerificador.setVisible(false);
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
        ventanaVerificador.getEtiquetaMonto().setText("$" + sumPreciosProductos + "");
        ventanaVerificador.getEtiquetaNumeroProducto().setText(sumCantidadProductos + " Productos en la Venta Actual");
    }

}
