/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.usuarios;

import controladores.clientes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Usuario;
import modelos.db.SQLClientes;
import modelos.db.SQLUsuario;
import vistas.clientes.VentanaCamposModificarCliente;
import vistas.usuarios.VentanaCamposModificarUsuario;

/**
 *
 * @author jarv
 */
public class OyenteCamposModificarUsuario implements ActionListener {

    private SQLUsuario sqlUsuario;
    private VentanaCamposModificarUsuario ventanaCamposModificar;

    public OyenteCamposModificarUsuario(SQLUsuario sqlUsuario,
            VentanaCamposModificarUsuario ventanaCamposModificar) {
        this.sqlUsuario = sqlUsuario;
        this.ventanaCamposModificar = ventanaCamposModificar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        switch (boton.getName()) {
            case "botonGuardarCambios":
                Usuario cliente = new Usuario();
                cliente.setNombre(ventanaCamposModificar.getCampoNombre().getText());
                cliente.setApellidoPaterno(ventanaCamposModificar.getCampoApellidoP().getText());
                cliente.setApellidoMaterno(ventanaCamposModificar.getCampoApellidoM().getText());
                cliente.setNick(ventanaCamposModificar.getCampoNick().getText());
                cliente.setContrasena(ventanaCamposModificar.getCampoContrasenal().getText());
                cliente.setIdCargo(ventanaCamposModificar.getComboCargo().getSelectedIndex());
                //ESTA VARIABLE ALMACENA EL TELEFONO ORIGINAL ANTES DE SER MODIFICADO
                //DE ESTA FORMA PUEDO BUSCAR EL CLIENTE Y ACTUALIZAR DATOS
                String nick = ventanaCamposModificar.getNick();
                sqlUsuario.modificarUsuario(cliente, nick);
                buscarUsuario();
                ventanaCamposModificar.setVisible(false);
                break;
            case "botonCancelar":
                ventanaCamposModificar.setVisible(false);
        }
    }
    
    public void buscarUsuario() {
        DefaultTableModel modeloTabla = (DefaultTableModel) ventanaCamposModificar.getTablaUsuario().getModel();
        String textoBuscar = ventanaCamposModificar.getCampoUsuario().getText();
        sqlUsuario.buscarUsuario(textoBuscar, modeloTabla);
    }

}
