/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import Modelo.Proveedor;

/**
 *
 * @author jarv
 */
public class SQLProveedores {

    public void buscarProveedor(String textoBuscar, DefaultTableModel modeloTabla) {
        Connection conexion = null;
        try {
            //utilizando este while ya no se repite el producto buscado
            //utilizando for si se repite
            while (modeloTabla.getRowCount() != 0) {
                modeloTabla.removeRow(0);
            }
            Proveedor proveedor = new Proveedor();
            conexion = Conexion.getConexion();
            String sqlProveedor = "SELECT NOMBRE_PROVEEDOR, CORREO_PROVEEDOR, TELEFONO_PROVEEDOR,\n" +
"ESTADO_PROVEEDOR, CIUDAD_PROVEEDOR"
                    + " FROM PROVEEDORES WHERE NOMBRE_PROVEEDOR LIKE ? OR TELEFONO_PROVEEDOR LIKE ?";

            PreparedStatement consultaBusqueda = conexion.prepareStatement(sqlProveedor);
            consultaBusqueda.setString(1, "%" + textoBuscar + "%");
            consultaBusqueda.setString(2, "%" + textoBuscar + "%");

            ResultSet recorreBusqueda = consultaBusqueda.executeQuery();

            while (recorreBusqueda.next()) {
//                proveedor.setIdProveedor(recorreBusqueda.getInt(1));
                proveedor.setNombreCompania(recorreBusqueda.getString(1));
                proveedor.setEmail(recorreBusqueda.getString(2));
                proveedor.setTelefono(recorreBusqueda.getLong(3));
                proveedor.setEstado(recorreBusqueda.getString(4));
                proveedor.setCiudad(recorreBusqueda.getString(5));

                Object[] camposProveedor = {
                    proveedor.getNombreCompania(),
                    proveedor.getEmail(),
                    proveedor.getTelefono(),
                    proveedor.getEstado(),
                    proveedor.getCiudad()
                };
                modeloTabla.addRow(camposProveedor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
//            conexion.close();
//            Conexion.cerrarConexion();
        }
    }

    public Proveedor getProveedor(long telefonoProveedor) {
        try {
            Connection conexion = Conexion.getConexion();
            String sqlProveedor = "SELECT * FROM PROVEEDORES WHERE TELEFONO_PROVEEDOR=?";
            PreparedStatement consulta = conexion.prepareStatement(sqlProveedor);
            consulta.setLong(1, telefonoProveedor);
            ResultSet recorre = consulta.executeQuery();
            if (recorre.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setNombreCompania(recorre.getString(2));
                proveedor.setEmail(recorre.getString(3));
                proveedor.setTelefono((long) recorre.getLong(4));
                proveedor.setCalle(recorre.getString(5));
                proveedor.setColonia(recorre.getString(6));
                proveedor.setEstado(recorre.getString(7));
                proveedor.setCiudad(recorre.getString(8));
                proveedor.setCodigoPostal(recorre.getInt(9));
                return proveedor;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void eliminarProveedor(long telefonoProveedor) {
        try {
            Connection conexion = Conexion.getConexion();
            String sqlProveedor = "DELETE FROM PROVEEDORES WHERE TELEFONO_PROVEEDOR=?";
            PreparedStatement consulta = conexion.prepareStatement(sqlProveedor);
            consulta.setLong(1, telefonoProveedor);
            consulta.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void registrarProveedor(Proveedor proveedor) {
        try {
            Connection conexion = Conexion.getConexion();
            String sqlProveedor = "INSERT INTO PROVEEDORES(NOMBRE_PROVEEDOR, CORREO_PROVEEDOR, TELEFONO_PROVEEDOR, CALLE_PROVEEDOR, COLONIA_PROVEEDOR,\n" +
"ESTADO_PROVEEDOR, CIUDAD_PROVEEDOR, CODIGO_POSTAL_PROVEEDOR) VALUES  (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement consulta = conexion.prepareStatement(sqlProveedor);
            consulta.setString(1, proveedor.getNombreCompania());
            consulta.setString(2, proveedor.getEmail());
            consulta.setLong(3, proveedor.getTelefono());
            consulta.setString(4, proveedor.getCalle());
            consulta.setString(5, proveedor.getColonia());
            consulta.setString(6, proveedor.getEstado());
            consulta.setString(7, proveedor.getCiudad());
            consulta.setInt(8, proveedor.getCodigoPostal());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void modificarProveedor(Proveedor proveedor, long telefonoCopia) {
        try {
            Connection conexion = Conexion.getConexion();
            String sqlProveedor = "UPDATE PROVEEDORES SET NOMBRE_PROVEEDOR=?, CORREO_PROVEEDOR=?, TELEFONO_PROVEEDOR=?, "
                + "CALLE_PROVEEDOR=?, COLONIA_PROVEEDOR=?, ESTADO_PROVEEDOR=?, CIUDAD_PROVEEDOR=?, CODIGO_POSTAL_PROVEEDOR=? WHERE TELEFONO_PROVEEDOR=?";
            PreparedStatement consulta = conexion.prepareStatement(sqlProveedor);
            consulta.setString(1, proveedor.getNombreCompania());
            consulta.setString(2, proveedor.getEmail());
            consulta.setLong(3, proveedor.getTelefono());
            consulta.setString(4, proveedor.getCalle());
            consulta.setString(5, proveedor.getColonia());
            consulta.setString(6, proveedor.getEstado());
            consulta.setString(7, proveedor.getCiudad());
            consulta.setInt(8, proveedor.getCodigoPostal());
            consulta.setLong(9, telefonoCopia);
            consulta.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
