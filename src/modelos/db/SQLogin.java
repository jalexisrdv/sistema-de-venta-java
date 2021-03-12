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
import modelos.Usuario;
import vistas.PanelLogin;

/**
 *
 * @author jarv
 */
public class SQLogin {
    
    private static int idUsuario;

    public Usuario validarLogin(String idUsuario, String contrasena) {
        try {
            Connection conexion = Conexion.getConexion();
            String sqlUsuario = "SELECT ID_USUARIO, NICK_USUARIO, CONTRASENA_USUARIO FROM USUARIOS "
                    + "WHERE NICK_USUARIO=? AND CONTRASENA_USUARIO=?";
            PreparedStatement consulta = conexion.prepareStatement(sqlUsuario);
            consulta.setString(1, idUsuario);
            consulta.setString(2, contrasena);
            ResultSet recorre = consulta.executeQuery();
            if(recorre.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(recorre.getInt(1));
                usuario.setNombre(recorre.getString(2));
                usuario.setContrasena(recorre.getString(3));
                setIdUsuario(recorre.getInt(1));
                PanelLogin.setUsuario("USUARIO: " + usuario.getNombre());
                return usuario;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void setIdUsuario(int idUsuario) {
        SQLogin.idUsuario = idUsuario;
    }
    
    public static int getIdUsuario() {
        return idUsuario;
    }

}
