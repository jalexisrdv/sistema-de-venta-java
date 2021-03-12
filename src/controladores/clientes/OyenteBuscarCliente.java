/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Venta;
import modelos.db.SQLVentas;
import vistas.clientes.VentanaBuscarCliente;

/**
 *
 * @author jarv
 */
public class OyenteBuscarCliente extends KeyAdapter implements ActionListener, DocumentListener {

    private SQLVentas sqlVentas;
    private VentanaBuscarCliente ventanaBuscarCliente;
    private int numFilaSeleccionada;

    public OyenteBuscarCliente(SQLVentas sqlVentas, VentanaBuscarCliente ventanaBuscarCliente) {
        this.sqlVentas = sqlVentas;
        this.ventanaBuscarCliente = ventanaBuscarCliente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        switch (boton.getName()) {
            case "botonAceptar":
                agregarNombreClienteBuscado();
                break;
            case "botonCancelar":
                ventanaBuscarCliente.setVisible(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                agregarNombreClienteBuscado();
                break;
            case KeyEvent.VK_ESCAPE:
                ventanaBuscarCliente.setVisible(false);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String descripcion = ventanaBuscarCliente.getCampoBuscarCliente().getText();
        sqlVentas.buscarCliente(descripcion);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String descripcion = ventanaBuscarCliente.getCampoBuscarCliente().getText();
        sqlVentas.buscarCliente(descripcion);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String descripcion = ventanaBuscarCliente.getCampoBuscarCliente().getText();
        sqlVentas.buscarCliente(descripcion);
    }

    public void agregarNombreClienteBuscado() {
        DefaultTableModel modeloTablaBuscarCliente = (DefaultTableModel) ventanaBuscarCliente.getTablaBuscarCliente().getModel();
        numFilaSeleccionada = ventanaBuscarCliente.getTablaBuscarCliente().getSelectedRow();
        if (numFilaSeleccionada >= 0) {
            String nombre = modeloTablaBuscarCliente.getValueAt(numFilaSeleccionada, 0).toString();
            ventanaBuscarCliente.getEtiquetaNombreCliente().setText(nombre);
            ventanaBuscarCliente.getCliente().setTelefono((long) modeloTablaBuscarCliente.getValueAt(numFilaSeleccionada, 3));
            ventanaBuscarCliente.setVisible(false);
        }
    }

}
