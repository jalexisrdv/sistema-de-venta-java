package modelos.g;

import modelos.db.Conexion;
import java.sql.*;

public class CargarCategorias {
    
    private Conexion miConexion;
    private ResultSet rs;
    private PreparedStatement ps;
    
    public CargarCategorias(){
        miConexion = new Conexion();
    }
        
    public void consultaCategorias(){
        Connection accesoDB = miConexion.getConexion();
        try{
            ps = accesoDB.prepareStatement("SELECT NOMBRE_CATEGORIA FROM CATEGORIAS");
            rs = ps.executeQuery();         
        }catch(SQLException e){
            System.out.println("Cuando Carga Categorias: "+e);
        }
    }

    public ResultSet getRs() {
        return rs;
    } 
    
    public void cargarProveedores(){
        Connection accesoDB = miConexion.getConexion();
        try{
           ps = accesoDB.prepareStatement("SELECT NOMBRE_PROVEEDOR FROM PROVEEDORES");
           rs = ps.executeQuery();
        }catch(SQLException e){
            System.out.println("Cuando Carga PROVEEDORES: "+e);
        }
    }
        
//    public int consultaCategorias(){
//        Productos miProducto = null;
//        Connection accesoDB = miConexion.conecta();
//        try{
//            ps = accesoDB.prepareStatement("SELECT NOMBRE_CATEGORIA FROM CATEGORIAS");
//            rs = ps.executeQuery();
//            
//                miProducto = new Productos();
//                miProducto.setCategoria(rs.getInt("NOMBRE_CATEGORIA"));
//
//            rs.close();
//            
//        }catch(SQLException e){
//            System.out.println("Cuando Carga Categorias: "+e);
//        }
//       return miProducto.getCategoria();
//    }
    
    
}
