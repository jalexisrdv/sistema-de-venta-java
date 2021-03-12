/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.usuarios;

import controladores.clientes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Usuario;
import modelos.db.SQLClientes;
import modelos.db.SQLUsuario;
import utilidades.UtilVentanas;
import vistas.clientes.PanelEliminarCliente;
import vistas.usuarios.PanelEliminarUsuario;
import vistas.usuarios.VentanaCamposModificarUsuario;

/**
 *
 * @author jarv
 */
public class OyenteEliminarUsuario implements ActionListener, DocumentListener {

    private final SQLUsuario sqlUsuario;
    private final PanelEliminarUsuario panelEliminarUsuario;

    public OyenteEliminarUsuario(SQLUsuario sqlUsuario, PanelEliminarUsuario panelEliminarUsuario) {
        this.sqlUsuario = sqlUsuario;
        this.panelEliminarUsuario = panelEliminarUsuario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable tabla = panelEliminarUsuario.getTablaUsuario();
        int numFila = tabla.getSelectedRow();
        if (numFila >= 0) {
            String nick = (String) tabla.getValueAt(numFila, 3);
            Usuario cliente = sqlUsuario.getUsuario(nick);
            sqlUsuario.eliminarUsuario(nick);
            buscarUsuario();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        buscarUsuario();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        buscarUsuario();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        buscarUsuario();
    }
    
    public void buscarUsuario() {
        DefaultTableModel modeloTabla = (DefaultTableModel) panelEliminarUsuario.getTablaUsuario().getModel();
        String textoBuscar = panelEliminarUsuario.getCampoUsuario().getText();
        sqlUsuario.buscarUsuario(textoBuscar, modeloTabla);
    }

}
