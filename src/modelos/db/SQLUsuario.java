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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Producto;
import modelos.Usuario;
import modelos.Venta;

/**
 *
 * @author Bomby007
 */
public class SQLUsuario {
    
    private Usuario usuario;
    private DefaultTableModel modeloTablaBuscarUsuario;
    private JTable tablaVentasProductos;

//    public void buscarUsuario(String textoBuscar) {
//        try {
//            //utilizando este while ya no se repite el producto buscado
//            //utilizando for si se repite
//            while (modeloTablaBuscarUsuario.getRowCount() != 0) {
//                modeloTablaBuscarUsuario.removeRow(0);
//            }
//            Usuario usuario = new Usuario();
//            Connection conexion = Conexion.getConexion();
//            String sqlUsuario = "SELECT ID_USUARIO, NOMBRE_USUARIO, APELLIDO_P_USUARIO, APELLIDO_M_USUARIO,"
//                    + " NICK_USUARIO, ID_CARGO_CARGO"
//                    + " FROM USUARIO WHERE NOMBRE_USUARIO LIKE ? OR NICK_USUARIO ?";
//
//            PreparedStatement consultaBusqueda = conexion.prepareStatement(sqlUsuario);
//            consultaBusqueda.setString(1, "%" + textoBuscar + "%");
//            consultaBusqueda.setString(2, "%" + textoBuscar + "%");
//            ResultSet recorreBusqueda = consultaBusqueda.executeQuery();
//
//            while (recorreBusqueda.next()) {
//                usuario.setNombre(recorreBusqueda.getString(1));
//                usuario.setApellidoPaterno(recorreBusqueda.getString(2));
//                usuario.setApellidoMaterno(recorreBusqueda.getString(3));
//                usuario.setNick(recorreBusqueda.getString(4));
//                usuario.setIdCargo(recorreBusqueda.getInt(5));
//                Object[] camposUsuario = {
//                    usuario.getNombre(),
//                    usuario.getApellidoPaterno(),
//                    usuario.getApellidoMaterno(),
//                    usuario.getNick(),
//                    usuario.getIdCargo()
//                };
//                modeloTablaBuscarUsuario.addRow(camposUsuario);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            Conexion.cerrarConexion();
//        }
//    }
//    
    public void buscarUsuario(String textoBuscar, DefaultTableModel modeloTabla) {
        try {
            //utilizando este while ya no se repite el producto buscado
            //utilizando for si se repite
            while (modeloTabla.getRowCount() != 0) {
                modeloTabla.removeRow(0);
            }
            Usuario usuario = new Usuario();
            Connection conexion = Conexion.getConexion();
             String sqlUsuario = "SELECT ID_USUARIO, NOMBRE_USUARIO, APELLIDO_P_USUARIO, APELLIDO_M_USUARIO,"
                    + " NICK_USUARIO, ID_CARGO_CARGO"
                    + " FROM USUARIOS WHERE NOMBRE_USUARIO LIKE ? OR NICK_USUARIO LIKE ?";

             
            PreparedStatement consultaBusqueda = conexion.prepareStatement(sqlUsuario);
            consultaBusqueda.setString(1, "%" + textoBuscar + "%");
            consultaBusqueda.setString(2, "%" + textoBuscar + "%");
            ResultSet recorreBusqueda = consultaBusqueda.executeQuery();

            while (recorreBusqueda.next()) {
                usuario.setIdUsuario(recorreBusqueda.getInt(1));
                usuario.setNombre(recorreBusqueda.getString(2));
                usuario.setApellidoPaterno(recorreBusqueda.getString(3));
                usuario.setApellidoMaterno(recorreBusqueda.getString(4));
                 usuario.setNick(recorreBusqueda.getString(5));
                usuario.setIdCargo(recorreBusqueda.getInt(6));

                Object[] camposUsuario = {
                    usuario.getNombre(),
                    usuario.getApellidoPaterno(),
                    usuario.getApellidoMaterno(),
                    usuario.getNick(),
                    usuario.getIdUsuario(),
                    usuario.getIdCargo()
                };
                modeloTabla.addRow(camposUsuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
    }
    
    public void registrarUsuario(Usuario usuario) {
        try {
            Connection conexion = Conexion.getConexion();
            String sql = "INSERT INTO CLIENTES(NICK_USUARIO, NOMBRE_USUARIO, "
                    + "APELLIDO_P_USUARIO, APELLIDO_M_USUARIO, "
                    + "CONTRASENA_USUARIO, ID_CARGO_CARGO) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement consulta = conexion.prepareStatement(sql);
            consulta.setString(1, usuario.getNick());
            consulta.setString(2, usuario.getNombre());
            consulta.setString(3, usuario.getApellidoPaterno());
            consulta.setString(4, usuario.getApellidoMaterno());
            consulta.setString(5, usuario.getContrasena());
            consulta.setInt(6, usuario.getIdCargo());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Usuario getUsuario(String nick) {
        try {
            Connection conexion = Conexion.getConexion();
            String sql = "SELECT * FROM USUARIOS WHERE NICK_USUARIO=?";
            PreparedStatement consulta = conexion.prepareStatement(sql);
            consulta.setString(1, nick);
            ResultSet recorre = consulta.executeQuery();
            if (recorre.next()) {
                Usuario usuario = new Usuario();
                usuario.setNombre(recorre.getString(2));
                usuario.setApellidoPaterno(recorre.getString(3));
                usuario.setApellidoMaterno(recorre.getString(4));
                usuario.setNick(recorre.getString(5));
                usuario.setIdUsuario(recorre.getInt(6));
                usuario.setIdCargo(recorre.getInt(7));
                return usuario;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void modificarUsuario(Usuario usuario, String nick) {
        try {
            Connection conexion = Conexion.getConexion();
            String sqlUsuario = "UPDATE USUARIOS SET NICK_USUARIO=?, NOMBRE_USUARIO=?, APELLIDO_P_USUARIO=?, APELLIDO_M_USUARIO=?, "
                    + "CONTRASENA_USUARIO=?, ID_CARGO_CARGO=? WHERE NICK_USUARIO=?";
            PreparedStatement consulta = conexion.prepareStatement(sqlUsuario);
            consulta.setString(1, usuario.getNick());
            consulta.setString(2, usuario.getNombre());
            consulta.setString(3, usuario.getApellidoPaterno());
            consulta.setString(4, usuario.getApellidoMaterno());
            consulta.setString(5, usuario.getContrasena());
            consulta.setInt(6, usuario.getIdCargo());
            
            consulta.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void eliminarUsuario(String nick) {
        try {
            Connection conexion = Conexion.getConexion();
            String sqlUsuario = "DELETE FROM USUARIOS WHERE NICK_USUARIO=?";
            PreparedStatement consulta = conexion.prepareStatement(sqlUsuario);
            consulta.setString(1, nick);
            consulta.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}