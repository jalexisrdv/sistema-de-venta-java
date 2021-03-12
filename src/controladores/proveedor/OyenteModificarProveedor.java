/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.proveedor;;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import Modelo.Proveedor;
import BaseDatos.SQLProveedores;
import utilidades.UtilVentanas;
import vistas.proveedor.*;
import javax.swing.JPanel;

/**
 *
 * @author jarv
 */
public class OyenteModificarProveedor implements ActionListener, DocumentListener {

    private SQLProveedores sqlProveedores;
    private PanelModificarProveedor panelModificarProveedor;

    public OyenteModificarProveedor(SQLProveedores sqlProveedores, PanelModificarProveedor panelModificarProveedor) {
        this.sqlProveedores = sqlProveedores;
        this.panelModificarProveedor = panelModificarProveedor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable tabla = panelModificarProveedor.getTablaProveedores();
        int numFila = tabla.getSelectedRow();
        if (numFila >= 0) {
            long numeroTelefono = (long) tabla.getValueAt(numFila, 2);
            
            Proveedor proveedor = sqlProveedores.getProveedor(numeroTelefono);
            if (proveedor != null) {
                JFrame ventanaPadre = UtilVentanas.getVentanaPadre();//VENTANA PADRE!!!!!
                VentanaCamposModificarProveedor ventanaCamposModificar
                        = new VentanaCamposModificarProveedor(sqlProveedores, ventanaPadre, true);
                UtilVentanas.abrirVentanaDialogo(ventanaPadre, ventanaCamposModificar, 450, 400);
                ventanaCamposModificar.setTelefonoCopia(numeroTelefono);
                ventanaCamposModificar.setCampoProveedor(panelModificarProveedor.getCampoProveedor());
                ventanaCamposModificar.setTablaProveedores(panelModificarProveedor.getTablaProveedores());
                ventanaCamposModificar.getCampoCompania().setText(proveedor.getNombreCompania());
                ventanaCamposModificar.getCampoEmail().setText(proveedor.getEmail());
                ventanaCamposModificar.getCampoCalle().setText(proveedor.getCalle());
                ventanaCamposModificar.getCampoColonia().setText(proveedor.getColonia());
                ventanaCamposModificar.getCampoEstado().setText(proveedor.getEstado());
                ventanaCamposModificar.getCampoCiudad().setText(proveedor.getCiudad());
                ventanaCamposModificar.getCampoTelefono().setText(String.valueOf(proveedor.getTelefono()));
                ventanaCamposModificar.getCampoCodigoPostal().setText(String.valueOf(proveedor.getCodigoPostal()));
                ventanaCamposModificar.setVisible(true);
            }
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
        DefaultTableModel modeloTabla = (DefaultTableModel) panelModificarProveedor.getTablaProveedores().getModel();
        String textoBuscar = panelModificarProveedor.getCampoProveedor().getText();
        sqlProveedores.buscarProveedor(textoBuscar, modeloTabla);
    }

}
