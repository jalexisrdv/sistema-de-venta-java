/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.db.SQLClientes;
import vistas.clientes.PanelEliminarCliente;

/**
 *
 * @author jarv
 */
public class OyenteEliminarCliente implements ActionListener, DocumentListener {

    private SQLClientes sqlClientes;
    private PanelEliminarCliente panelEliminarCliente;

    public OyenteEliminarCliente(SQLClientes sqlClientes, PanelEliminarCliente panelEliminarCliente) {
        this.sqlClientes = sqlClientes;
        this.panelEliminarCliente = panelEliminarCliente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable tabla = panelEliminarCliente.getTablaClientes();
        int numFila = tabla.getSelectedRow();
        if (numFila >= 0) {
            long numeroTelefono = (long) tabla.getValueAt(numFila, 3);
            Cliente cliente = sqlClientes.getCliente(numeroTelefono);
            cliente.setTelefono(numeroTelefono);
            sqlClientes.eliminarCliente(numeroTelefono);
            buscarCliente();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        buscarCliente();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        buscarCliente();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        buscarCliente();
    }
    
    public void buscarCliente() {
        DefaultTableModel modeloTabla = (DefaultTableModel) panelEliminarCliente.getTablaClientes().getModel();
        String textoBuscar = panelEliminarCliente.getCampoCliente().getText();
        sqlClientes.buscarCliente(textoBuscar, modeloTabla);
    }

}
