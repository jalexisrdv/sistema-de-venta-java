/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;

/**
 *
 * @author jarv
 */
public class SQLClientes {

    public void buscarCliente(String textoBuscar, DefaultTableModel modeloTabla) {
        try {
            //utilizando este while ya no se repite el producto buscado
            //utilizando for si se repite
            while (modeloTabla.getRowCount() != 0) {
                modeloTabla.removeRow(0);
            }
            Cliente cliente = new Cliente();
            Connection conexion = Conexion.getConexion();
            String sqlCliente = "SELECT ID_CLIENTE, NOMBRE_CLIENTE, APELLIDO_P_CLIENTE, APELLIDO_M_CLIENTE, "
                    + "TELEFONO_CLIENTE"
                    + " FROM CLIENTES WHERE NOMBRE_CLIENTE LIKE ? OR TELEFONO_CLIENTE LIKE ?";

            PreparedStatement consultaBusqueda = conexion.prepareStatement(sqlCliente);
            consultaBusqueda.setString(1, "%" + textoBuscar + "%");
            consultaBusqueda.setString(2, "%" + textoBuscar + "%");
            ResultSet recorreBusqueda = consultaBusqueda.executeQuery();

            while (recorreBusqueda.next()) {
                cliente.setIdCliente(recorreBusqueda.getInt(1));
                cliente.setNombre(recorreBusqueda.getString(2));
                cliente.setApellidoPaterno(recorreBusqueda.getString(3));
                cliente.setApellidoMaterno(recorreBusqueda.getString(4));
                cliente.setTelefono(recorreBusqueda.getLong(5));

                Object[] camposCliente = {
                    cliente.getNombre(),
                    cliente.getApellidoPaterno(),
                    cliente.getApellidoMaterno(),
                    cliente.getTelefono()
                };
                modeloTabla.addRow(camposCliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public Cliente getCliente(long telefonoCliente) {
        try {
            Connection conexion = Conexion.getConexion();
            String sqlCliente = "SELECT * FROM CLIENTES WHERE TELEFONO_CLIENTE=?";
            PreparedStatement consulta = conexion.prepareStatement(sqlCliente);
            consulta.setLong(1, telefonoCliente);
            ResultSet recorre = consulta.executeQuery();
            if (recorre.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombre(recorre.getString(2));
                cliente.setApellidoPaterno(recorre.getString(3));
                cliente.setApellidoMaterno(recorre.getString(4));
                cliente.setSexo(recorre.getString(5));
                cliente.setEmail(recorre.getString(6));
                cliente.setTelefono((long) recorre.getLong(7));
                return cliente;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void eliminarCliente(long telefonoCliente) {
        try {
            Connection conexion = Conexion.getConexion();
            String sqlCliente = "DELETE FROM CLIENTES WHERE TELEFONO_CLIENTE=?";
            PreparedStatement consulta = conexion.prepareStatement(sqlCliente);
            consulta.setLong(1, telefonoCliente);
            consulta.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void registrarCliente(Cliente cliente) {
        try {
            Connection conexion = Conexion.getConexion();
            String sqlCliente = "INSERT INTO CLIENTES(NOMBRE_CLIENTE, APELLIDO_P_CLIENTE, APELLIDO_M_CLIENTE, "
                    + "SEXO_CLIENTE, EMAIL_CLIENTE, TELEFONO_CLIENTE) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement consulta = conexion.prepareStatement(sqlCliente);
            consulta.setString(1, cliente.getNombre());
            consulta.setString(2, cliente.getApellidoPaterno());
            consulta.setString(3, cliente.getApellidoMaterno());
            consulta.setString(4, cliente.getSexo());
            consulta.setString(5, cliente.getEmail());
            consulta.setLong(6, cliente.getTelefono());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void modificarCliente(Cliente cliente, long telefonoCopia) {
        try {
            Connection conexion = Conexion.getConexion();
            String sqlCliente = "UPDATE CLIENTES SET NOMBRE_CLIENTE=?, APELLIDO_P_CLIENTE=?, APELLIDO_M_CLIENTE=?, "
                    + "SEXO_CLIENTE=?, EMAIL_CLIENTE=?, TELEFONO_CLIENTE=? WHERE TELEFONO_CLIENTE=?";
            PreparedStatement consulta = conexion.prepareStatement(sqlCliente);
            consulta.setString(1, cliente.getNombre());
            consulta.setString(2, cliente.getApellidoPaterno());
            consulta.setString(3, cliente.getApellidoMaterno());
            consulta.setString(4, cliente.getSexo());
            consulta.setString(5, cliente.getEmail());
            consulta.setLong(6, cliente.getTelefono());
            consulta.setLong(7, telefonoCopia);
            consulta.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
