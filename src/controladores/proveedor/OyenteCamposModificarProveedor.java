/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.proveedor;;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import Modelo.Proveedor;
import BaseDatos.SQLProveedores;
import vistas.proveedor.*;

/**
 *
 * @author jarv
 */
public class OyenteCamposModificarProveedor implements ActionListener {

    private SQLProveedores sqlProveedores;
    private VentanaCamposModificarProveedor ventanaCamposModificarProveedor;

    public OyenteCamposModificarProveedor(SQLProveedores sqlProveedores,
            VentanaCamposModificarProveedor ventanaCamposModificarProveedor) {
        this.sqlProveedores = sqlProveedores;
        this.ventanaCamposModificarProveedor = ventanaCamposModificarProveedor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        switch (boton.getName()) {
            case "botonGuardarCambios":
                Proveedor proveedor = new Proveedor();
                proveedor.setNombreCompania(ventanaCamposModificarProveedor.getCampoCompania().getText());
                proveedor.setCalle(ventanaCamposModificarProveedor.getCampoCalle().getText());
                proveedor.setColonia(ventanaCamposModificarProveedor.getCampoColonia().getText());
                proveedor.setEstado(ventanaCamposModificarProveedor.getCampoEstado().getText());
                proveedor.setCiudad(ventanaCamposModificarProveedor.getCampoCiudad().getText());
                proveedor.setCodigoPostal(Integer.parseInt(ventanaCamposModificarProveedor.getCampoCodigoPostal().getText()));
                proveedor.setEmail(ventanaCamposModificarProveedor.getCampoEmail().getText());
                proveedor.setTelefono(Long.parseLong(ventanaCamposModificarProveedor.getCampoTelefono().getText()));
                //ESTA VARIABLE ALMACENA EL TELEFONO ORIGINAL ANTES DE SER MODIFICADO
                //DE ESTA FORMA PUEDO BUSCAR EL CLIENTE Y ACTUALIZAR DATOS
                long telefonoCopia = ventanaCamposModificarProveedor.getTelefonoCopia();
                sqlProveedores.modificarProveedor(proveedor, telefonoCopia);
                buscarProveedor();
                ventanaCamposModificarProveedor.setVisible(false);
                break;
            case "botonCancelar":
                ventanaCamposModificarProveedor.setVisible(false);
        }
    }
    
    public void buscarProveedor() {
        DefaultTableModel modeloTabla = (DefaultTableModel) ventanaCamposModificarProveedor.getTablaProveedores().getModel();
        String textoBuscar = ventanaCamposModificarProveedor.getCampoProveedor().getText();
        sqlProveedores.buscarProveedor(textoBuscar, modeloTabla);
    }

}
