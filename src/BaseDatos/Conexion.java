/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jarv
 */
public class Conexion {
    
    private static Connection conexion = null;
    
    public static Connection getConexion() {
        try {
            String ruta = "jdbc:mysql://localhost:3306/SPORT_BUSINESS"
                    + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String pass = "helloalexis";
            conexion = DriverManager.getConnection(ruta, user, pass);
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error al realizar conexion");
        }
        return conexion;
    }
    
    public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
