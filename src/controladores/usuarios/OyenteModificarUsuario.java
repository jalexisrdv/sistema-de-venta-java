/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.usuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelos.Usuario;
import modelos.db.SQLUsuario;
import utilidades.UtilVentanas;
import vistas.clientes.VentanaCamposModificarCliente;
import vistas.usuarios.PanelModificarUsuario;
import vistas.usuarios.VentanaCamposModificarUsuario;

/**
 *
 * @author Bomby007
 */
public class OyenteModificarUsuario implements ActionListener, DocumentListener {

    private SQLUsuario sqlUsuario;
    private PanelModificarUsuario pmu;

    

    public OyenteModificarUsuario(SQLUsuario sqlUsuario, PanelModificarUsuario pmu) {
        this.sqlUsuario = sqlUsuario;
        this.pmu = pmu;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        JTable tabla = pmu.getTablaUsuario();
        int numFila = tabla.getSelectedRow();
        if (numFila >= 0) {
            String nick = (String) tabla.getValueAt(numFila, 3);
            Usuario cliente = sqlUsuario.getUsuario(nick);
            if (cliente != null) {
                JFrame ventanaPadre = UtilVentanas.getVentanaPadre();
                VentanaCamposModificarUsuario ventanaCamposModificar
                        = new VentanaCamposModificarUsuario(sqlUsuario, ventanaPadre, true);
                UtilVentanas.abrirVentanaDialogo(ventanaPadre, ventanaCamposModificar, 450, 400);
                ventanaCamposModificar.setNick(nick);
                ventanaCamposModificar.setCampoUsuario(pmu.getCampoUsuario());
                ventanaCamposModificar.setTablaUsuario(pmu.getTablaUsuario());
                ventanaCamposModificar.getCampoNombre().setText(cliente.getNombre());
                ventanaCamposModificar.getCampoApellidoP().setText(cliente.getApellidoPaterno());
                ventanaCamposModificar.getCampoApellidoM().setText(cliente.getApellidoMaterno());
                ventanaCamposModificar.getCampoNick().setText(cliente.getNick());
                ventanaCamposModificar.getCampoContrasenal().setText(cliente.getContrasena());
                ventanaCamposModificar.getComboCargo().setSelectedIndex(cliente.getIdCargo());
                ventanaCamposModificar.setVisible(true);
            }
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
        DefaultTableModel modeloTabla = (DefaultTableModel) pmu.getTablaUsuario().getModel();
        String textoBuscar = pmu.getCampoUsuario().getText();
        sqlUsuario.buscarUsuario(textoBuscar, modeloTabla);
    }
}
