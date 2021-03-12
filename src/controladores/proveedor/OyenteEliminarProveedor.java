/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.proveedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import Modelo.Proveedor;
import BaseDatos.SQLProveedores;
import vistas.proveedor.*;

/**
 *
 * @author jarv
 */
public class OyenteEliminarProveedor implements ActionListener, DocumentListener {

    private SQLProveedores sqlProveedores;
    private PanelEliminarProveedor panelEliminarProveedor;

    public OyenteEliminarProveedor(SQLProveedores sqlClientes, PanelEliminarProveedor panelEliminarProveedor) {
        this.sqlProveedores = sqlClientes;
        this.panelEliminarProveedor = panelEliminarProveedor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable tabla = panelEliminarProveedor.getTablaProveedor();
        int numFila = tabla.getSelectedRow();
        if (numFila >= 0) {
            long numeroTelefono = (long) tabla.getValueAt(numFila, 2);
            Proveedor proveedor = sqlProveedores.getProveedor(numeroTelefono);
            proveedor.setTelefono(numeroTelefono);
            sqlProveedores.eliminarProveedor(numeroTelefono);
            buscarProveedor();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        buscarProveedor();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        buscarProveedor();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        buscarProveedor();
    }
    
    public void buscarProveedor() {
        DefaultTableModel modeloTabla = (DefaultTableModel) panelEliminarProveedor.getTablaProveedor().getModel();
        String textoBuscar = panelEliminarProveedor.getCampoProveedor().getText();
        sqlProveedores.buscarProveedor(textoBuscar, modeloTabla);
    }

}
