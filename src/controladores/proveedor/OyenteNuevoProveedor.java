/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.proveedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import Modelo.Proveedor;
import BaseDatos.SQLProveedores;
import vistas.proveedor.*;

/**
 *
 * @author jarv
 */
public class OyenteNuevoProveedor implements ActionListener {

    private SQLProveedores sqlProveedores;
    private PanelNuevoProveedor panelNuevoProveedor;

    public OyenteNuevoProveedor(SQLProveedores sqlProveedores, PanelNuevoProveedor panelNuevoProveedor) {
        this.sqlProveedores = sqlProveedores;
        this.panelNuevoProveedor = panelNuevoProveedor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        //String texto=" ";
        switch (boton.getName()) {
            case "botonGuardar":
                Proveedor proveedor = new Proveedor();
                /*if(panelNuevoProveedor.getCampoCompania().equals(texto) && panelNuevoProveedor.getCampoEmail().equals(texto)
                        && panelNuevoProveedor.getCampoCalle().equals(texto) && panelNuevoProveedor.getCampoColonia().equals(texto)
                        && panelNuevoProveedor.getCampoEstado().equals(texto) && panelNuevoProveedor.getCampoCiudad().equals(texto)
                        && panelNuevoProveedor.getCampoCodigoPostal().equals(texto) &&  panelNuevoProveedor.getCampoTelefono().equals(texto)){*/

                    proveedor.setNombreCompania(panelNuevoProveedor.getCampoCompania().getText());
                    proveedor.setEmail(panelNuevoProveedor.getCampoEmail().getText());
                    proveedor.setCalle(panelNuevoProveedor.getCampoCalle().getText());
                    proveedor.setColonia(panelNuevoProveedor.getCampoColonia().getText());
                    proveedor.setEstado(panelNuevoProveedor.getCampoEstado().getText());
                    proveedor.setCiudad(panelNuevoProveedor.getCampoCiudad().getText());
                    proveedor.setCodigoPostal(Integer.parseInt(panelNuevoProveedor.getCampoCodigoPostal().getText()));
                    proveedor.setTelefono(Long.parseLong(panelNuevoProveedor.getCampoTelefono().getText()));
                    sqlProveedores.registrarProveedor(proveedor);
                    limpiarCampos();
               /* }else{
                    JOptionPane.showMessageDialog(null,"Faltan datos","WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                    panelNuevoProveedor.getCampoCompania().requestFocus();
                }*/
                break;
            case "botonCancelar":
                limpiarCampos();
        }
    }

    public void limpiarCampos() {
        panelNuevoProveedor.getCampoCompania().setText("");
        panelNuevoProveedor.getCampoEmail().setText("");
        panelNuevoProveedor.getCampoCalle().setText("");
        panelNuevoProveedor.getCampoColonia().setText("");
        panelNuevoProveedor.getCampoEstado().setText("");
        panelNuevoProveedor.getCampoCiudad().setText("");
        panelNuevoProveedor.getCampoCodigoPostal().setText("");
        panelNuevoProveedor.getCampoTelefono().setText("");
    }

}
