/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import modelos.Cliente;
import modelos.db.SQLClientes;
import vistas.clientes.PanelNuevoCliente;

/**
 *
 * @author jarv
 */
public class OyenteNuevoCliente implements ActionListener {

    private SQLClientes sqlClientes;
    private PanelNuevoCliente panelNuevoCliente;

    public OyenteNuevoCliente(SQLClientes sqlClientes, PanelNuevoCliente panelNuevoCliente) {
        this.sqlClientes = sqlClientes;
        this.panelNuevoCliente = panelNuevoCliente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        switch (boton.getName()) {
            case "botonGuardar":
                Cliente cliente = new Cliente();
                cliente.setNombre(panelNuevoCliente.getCampoNombre().getText());
                cliente.setApellidoPaterno(panelNuevoCliente.getCampoApellidoP().getText());
                cliente.setApellidoMaterno(panelNuevoCliente.getCampoApellidoM().getText());
                cliente.setSexo(panelNuevoCliente.getBotonGrupoSexo().getSelection().getActionCommand().toUpperCase());
                cliente.setEmail(panelNuevoCliente.getCampoEmail().getText());
                cliente.setTelefono(Long.parseLong(panelNuevoCliente.getCampoTelefono().getText()));
                sqlClientes.registrarCliente(cliente);
                limpiarCampos();
                break;
            case "botonCancelar":
                limpiarCampos();
        }
    }

    public void limpiarCampos() {
        panelNuevoCliente.getCampoNombre().setText("");
        panelNuevoCliente.getCampoApellidoP().setText("");
        panelNuevoCliente.getCampoApellidoM().setText("");
        panelNuevoCliente.getBotonGrupoSexo().clearSelection();
        panelNuevoCliente.getCampoEmail().setText("");
        panelNuevoCliente.getCampoTelefono().setText("");
    }

}
