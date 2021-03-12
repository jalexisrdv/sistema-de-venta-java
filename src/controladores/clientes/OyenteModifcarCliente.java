/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.db.SQLClientes;
import utilidades.UtilVentanas;
import vistas.clientes.PanelModificarCliente;
import vistas.clientes.VentanaCamposModificarCliente;

/**
 *
 * @author jarv
 */
public class OyenteModifcarCliente implements ActionListener, DocumentListener {

    private SQLClientes sqlClientes;
    private PanelModificarCliente panelModificarCliente;

    public OyenteModifcarCliente(SQLClientes sqlClientes, PanelModificarCliente panelModificarCliente) {
        this.sqlClientes = sqlClientes;
        this.panelModificarCliente = panelModificarCliente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable tabla = panelModificarCliente.getTablaClientes();
        int numFila = tabla.getSelectedRow();
        if (numFila >= 0) {
            long numeroTelefono = (long) tabla.getValueAt(numFila, 3);
            Cliente cliente = sqlClientes.getCliente(numeroTelefono);
            if (cliente != null) {
                JFrame ventanaPadre = UtilVentanas.getVentanaPadre();
                VentanaCamposModificarCliente ventanaCamposModificar
                        = new VentanaCamposModificarCliente(sqlClientes, ventanaPadre, true);
                UtilVentanas.abrirVentanaDialogo(ventanaPadre, ventanaCamposModificar, 450, 400);
                ventanaCamposModificar.setTelefonoCopia(numeroTelefono);
                ventanaCamposModificar.setCampoCliente(panelModificarCliente.getCampoCliente());
                ventanaCamposModificar.setTablaClientes(panelModificarCliente.getTablaClientes());
                ventanaCamposModificar.getCampoNombre().setText(cliente.getNombre());
                ventanaCamposModificar.getCampoApellidoP().setText(cliente.getApellidoPaterno());
                ventanaCamposModificar.getCampoApellidoM().setText(cliente.getApellidoMaterno());
                switch (cliente.getSexo()) {
                    case "M":
                        ventanaCamposModificar.getRadioBotonM().setSelected(true);
                        break;
                    case "F":
                        ventanaCamposModificar.getRadioBotonF().setSelected(true);
                        break;
                    case "OTRO":
                        ventanaCamposModificar.getRadioBotonOtro().setSelected(true);

                }
                ventanaCamposModificar.getCampoEmail().setText(cliente.getEmail());
                ventanaCamposModificar.getCampoTelefono().setText(String.valueOf(cliente.getTelefono()));
                ventanaCamposModificar.setVisible(true);
            }
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
        DefaultTableModel modeloTabla = (DefaultTableModel) panelModificarCliente.getTablaClientes().getModel();
        String textoBuscar = panelModificarCliente.getCampoCliente().getText();
        sqlClientes.buscarCliente(textoBuscar, modeloTabla);
    }

}
