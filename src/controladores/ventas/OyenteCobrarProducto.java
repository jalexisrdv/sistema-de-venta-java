/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Venta;
import modelos.db.SQLVentas;
import utilidades.UtilVentanas;
import vistas.clientes.VentanaBuscarCliente;
import vistas.ventas.VentanaCobrarProducto;

/**
 *
 * @author jarv
 */
public class OyenteCobrarProducto extends WindowAdapter implements ActionListener, DocumentListener, KeyListener {

    private SQLVentas sqlVentas;
    private VentanaCobrarProducto ventanaCobrarProducto;
    private double sumPreciosProductos = 0;
    private VentanaBuscarCliente ventanaBuscarCliente;
    private Cliente cliente;

    public OyenteCobrarProducto(SQLVentas sqlVentas, VentanaCobrarProducto ventanaCobrarProducto) {
        this.sqlVentas = sqlVentas;
        this.ventanaCobrarProducto = ventanaCobrarProducto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent componente = (JComponent) e.getSource();
        switch (componente.getName()) {
            case "cliente":
                JLabel etiquetaNombreCliente = ventanaCobrarProducto.getEtiquetaNombreCliente();
                etiquetaNombreCliente.setText("CLIENTE");
                JFrame ventanaPadre = UtilVentanas.getVentanaPadre();
                ventanaBuscarCliente = new VentanaBuscarCliente(sqlVentas, ventanaPadre, true);
                ventanaBuscarCliente.setEtiquetaNombreCliente(etiquetaNombreCliente);
                cliente = new Cliente();
                ventanaBuscarCliente.setCliente(cliente);
                UtilVentanas.abrirVentanaDialogo(ventanaPadre,
                        ventanaBuscarCliente, 800, 450);
                ventanaBuscarCliente.setVisible(true);
                break;
            case "publicoGeneral":
                etiquetaNombreCliente = ventanaCobrarProducto.getEtiquetaNombreCliente();
                etiquetaNombreCliente.setText("PUBLICO EN GENERAL");
                break;
            case "botonCobrar":
                cobrarProductos();
                break;
            case "botonCancelar":
                ventanaCobrarProducto.setVisible(false);
        }
        ventanaCobrarProducto.getCampoCantidadPago().requestFocus();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        DefaultTableModel modeloTablaVentas = sqlVentas.getDefaultModeloTablaVenta();
        int sumCantidadProductos = 0;
        for (int i = 0; i < modeloTablaVentas.getRowCount(); i++) {
            int cantidadProductos = (int) modeloTablaVentas.getValueAt(i, 3);
            double precioProductos = (double) modeloTablaVentas.getValueAt(i, 2);
            sumPreciosProductos += precioProductos * cantidadProductos;
            sumCantidadProductos += cantidadProductos;
        }
        ventanaCobrarProducto.getEtiquetaCantidadMonto().setText("$" + sumPreciosProductos + "");
        ventanaCobrarProducto.getEtiquetaNumeroArticulos().setText(sumCantidadProductos + "");
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        operacionCambioEfectivo();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        operacionCambioEfectivo();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        operacionCambioEfectivo();
    }

    public void operacionCambioEfectivo() {
        try {
            double cambio;
            String dineroRecibido = ventanaCobrarProducto.getCampoCantidadPago().getText();
            if (!dineroRecibido.isEmpty()) {
                double cantidadPago = Double.parseDouble(dineroRecibido);
                cambio = cantidadPago == 0 ? 0 : cantidadPago - sumPreciosProductos;
            } else {
                cambio = 0;
            }
            ventanaCobrarProducto.getEtiquetaCantidadCambio().setText("$" + Math.abs(cambio) + "");
        } catch (NumberFormatException e) {

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_F2:
                cobrarProductos();
                break;
            case KeyEvent.VK_ESCAPE:
                ventanaCobrarProducto.setVisible(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void cobrarProductos() {
        String cantidadMonto = ventanaCobrarProducto.getEtiquetaCantidadMonto().getText();
        if (!cantidadMonto.equals("$0.0")) {
            DefaultTableModel modeloTablaVentas = sqlVentas.getDefaultModeloTablaVenta();
            String tipoCliente = ventanaCobrarProducto.getEtiquetaNombreCliente().getText();
            Venta venta = new Venta();
            //SE AGREGO ESTA LINEA PARA COMPROBAR QUE EL MONTO NO SEA MENOR AL SALDO QUE SE DEBE PAGAR
            String cantidadPago = cantidadMonto.replaceAll("\\$", "");
            double saldoPagar = Double.parseDouble(cantidadPago);
            if (tipoCliente.equalsIgnoreCase("PUBLICO EN GENERAL") || !tipoCliente.equalsIgnoreCase("CLIENTE")) {
                if (!ventanaCobrarProducto.getCampoCantidadPago().getText().isEmpty()) {
                    double cantidadPagoIngresado = Double.parseDouble(ventanaCobrarProducto.getCampoCantidadPago().getText());
                    if (saldoPagar > cantidadPagoIngresado) {
                        JOptionPane.showMessageDialog(ventanaCobrarProducto, "El monto ingresado no puede ser menor\na la cantidad a pagar",
                            "Cantidad del Pago", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        for (int i = 0; i < sqlVentas.getTablaVentasProductos().getRowCount(); i++) {
                            venta.setUnidadesVenta((int) modeloTablaVentas.getValueAt(i, 3));
                            venta.setPrecioVenta((double) modeloTablaVentas.getValueAt(i, 2));
                            venta.setIdProducto((long) modeloTablaVentas.getValueAt(i, 0));
                            sqlVentas.registrarVenta(tipoCliente, venta, cliente);
                        }
                        limpiarTablaVenta();
                        ventanaCobrarProducto.getEtiquetaMontoVentas().setText("$0.0");
                        ventanaCobrarProducto.getEtiquetaNumeroProductosVentas().setText("0 Productos en la Venta Actual");
                        ventanaCobrarProducto.getBotonCobrarProductos().setEnabled(false);
                        ventanaCobrarProducto.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(ventanaCobrarProducto, "Debes Ingresar la Cantidad del Pago",
                            "Cantidad del Pago", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (tipoCliente.equalsIgnoreCase("CLIENTE")) {
                JOptionPane.showMessageDialog(ventanaCobrarProducto, "Debes Seleccionar un Cliente",
                        "Seleccionar Cliente", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void limpiarTablaVenta() {
        DefaultTableModel modeloTablaVentas = sqlVentas.getDefaultModeloTablaVenta();
        while (modeloTablaVentas.getRowCount() != 0) {
            modeloTablaVentas.removeRow(0);
        }
    }

}
