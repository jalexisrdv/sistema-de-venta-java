/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.db.SQLClientes;
import vistas.clientes.VentanaCamposModificarCliente;

/**
 *
 * @author jarv
 */
public class OyenteCamposModificarCliente implements ActionListener {

    private SQLClientes sqlClientes;
    private VentanaCamposModificarCliente ventanaCamposModificar;

    public OyenteCamposModificarCliente(SQLClientes sqlClientes,
            VentanaCamposModificarCliente ventanaCamposModificar) {
        this.sqlClientes = sqlClientes;
        this.ventanaCamposModificar = ventanaCamposModificar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        switch (boton.getName()) {
            case "botonGuardarCambios":
                Cliente cliente = new Cliente();
                cliente.setNombre(ventanaCamposModificar.getCampoNombre().getText());
                cliente.setApellidoPaterno(ventanaCamposModificar.getCampoApellidoP().getText());
                cliente.setApellidoMaterno(ventanaCamposModificar.getCampoApellidoM().getText());
                cliente.setSexo(ventanaCamposModificar.getGrupoSexos().getSelection().getActionCommand().toUpperCase());
                cliente.setEmail(ventanaCamposModificar.getCampoEmail().getText());
                cliente.setTelefono(Long.parseLong(ventanaCamposModificar.getCampoTelefono().getText()));
                //ESTA VARIABLE ALMACENA EL TELEFONO ORIGINAL ANTES DE SER MODIFICADO
                //DE ESTA FORMA PUEDO BUSCAR EL CLIENTE Y ACTUALIZAR DATOS
                long telefonoCopia = ventanaCamposModificar.getTelefonoCopia();
                sqlClientes.modificarCliente(cliente, telefonoCopia);
                buscarCliente();
                ventanaCamposModificar.setVisible(false);
                break;
            case "botonCancelar":
                ventanaCamposModificar.setVisible(false);
        }
    }
    
    public void buscarCliente() {
        DefaultTableModel modeloTabla = (DefaultTableModel) ventanaCamposModificar.getTablaClientes().getModel();
        String textoBuscar = ventanaCamposModificar.getCampoCliente().getText();
        sqlClientes.buscarCliente(textoBuscar, modeloTabla);
    }

}
