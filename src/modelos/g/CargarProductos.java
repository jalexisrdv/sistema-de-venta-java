package modelos.g;

import java.sql.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import modelos.db.Conexion;


public class CargarProductos {
    private Conexion miConexion;
    private ResultSet rs;
    private PreparedStatement ps;
    private boolean vv;
    public CargarProductos(){
        miConexion = new Conexion();
    }
    
    public int  registrarProductos(Productos producto,Compras compra){
        Connection accesoBD = miConexion.getConexion();
        int resultado=0;
        try{
            ps = accesoBD.prepareStatement("INSERT INTO PRODUCTOS("
            + "ID_PRODUCTO,"
            + "COLOR_PRODUCTO,"
            + "TAMANIO_PRODUCTO,"
            + "MARCA_PRODUCTO,"
            + "DESCRIPCION_PRODUCTO,"
            + "MARCO_GANANCIA_PRODUCTO,"
            + "EXISTENCIA_PRODUCTO,"
            + "ID_CATEGORIA_CATEGORIA) VALUES(?,?,?,?,?,?,?,?)");
            
            ps.setInt(1, producto.getId());
            ps.setString(2, producto.getColor());
            ps.setString(3,producto.getTamanio());
            ps.setString(4, producto.getMarca());
            ps.setString(5,producto.getDescripcion());
            ps.setInt(6, producto.getGanacia());
            ps.setInt(7, producto.getExistencia());
            ps.setInt(8,producto.getCategoria());
            
            resultado = ps.executeUpdate();
            
            accesoBD.close();
//            
//             ps = accesoBD.prepareStatement("SELECT ID_PROVEEDOR_COMPRA,FEHCA_PRODUCTO_COMPRA FROM COMPRAS WHERE ID_PRODUCTO_COMPRA = ?");
//             ps.setInt(1,producto.getId());
//             rs = ps.executeQuery();
//             if(rs.next()){
//                 compra.setId_proveedor(rs.getInt("ID_PROVEEDOR_COMPRA"));
//                 compra.setFecha(rs.getDate("FEHCA_PRODUCTO_COMPRA"));
//             }
//            accesoBD.close();
//            
//            ps = accesoBD.prepareStatement("INSERT INTO COMPRAS("
//                    + "COSTO_COMPRA,"
//                    + "CANTIDAD_PRODUCTO_COMPRA,"
//                    + "FECHA_PRODUCTO_COMPRA,"
//                    + "ID_PROVEEDOR_COMPRA"
//                    + "ID_PRODUCTO_COMPRA) VALUES(?,?,?,?,?)");
//            
//           
//                    
//            ps.setDouble(1, compra.getCostoCompra());
//            ps.setInt(2, compra.cantidad);
//            ps.setDate(3,compra.getFecha() );
//            ps.setInt(4, compra.getId_proveedor());
//            ps.setInt(5, compra.getId_producto());
//            accesoBD.close();
//            
            registrarCompra(compra);
        }catch(SQLException e){
            System.out.println(e);
        }
        return resultado;
    }
    
    public void registrarCompra(Compras compra){
        Connection accesoBD = miConexion.getConexion();
        int resultado =0;
         try{
            ps = accesoBD.prepareStatement("INSERT INTO COMPRAS("
//            + "ID_COMPRA,"
            + "COSTO_COMPRA,"
            + "UNIDADES_COMPRA,"
            + "FECHA_COMPRA,"
            + "ID_PROVEEDOR_PROVEEDOR,"
            + "ID_PRODUCTO_PRODUCTO) VALUES(?,?,NOW(),?,?)");
            
//            ps.setInt(1, compra.getId());
            ps.setFloat(1, compra.getCostoCompra());
            ps.setInt(2,compra.getCantidad());
//            ps.setDate(3,compra.getFecha());
            ps.setInt(3,compra.getId_proveedor());
            ps.setInt(4, compra.getId_producto());
            
            resultado = ps.executeUpdate();
            
            accesoBD.close();
         }catch(SQLException e){
             System.out.println(e);
         }
    }
    public Compras verificaCompra(Compras compra){
        Connection accesoBD = miConexion.getConexion();
        try{
            ps = accesoBD.prepareStatement("SELECT COSTO_COMPRA FROM COMPRAS WHERE ID_PRODUCTO_PRODUCTO = ?");
            ps.setInt(1,compra.getId_producto());
            rs = ps.executeQuery();
            
            if(rs.next()){
                compra.setCostoCompra(rs.getFloat("COSTO_COMPRA"));
            }
            rs.close();
            accesoBD.close();
        }catch(SQLException e){
            System.out.println(e);
            }
        return compra;
    }
    public Productos verificarProducto(Productos producto){
        Connection accesoBD = miConexion.getConexion();
        try{
            ps = accesoBD.prepareStatement("SELECT * FROM PRODUCTOS WHERE ID_PRODUCTO = ?");
            ps.setInt(1,producto.getId());
            rs = ps.executeQuery();
            
            if(rs.next()){
                producto.setColor(rs.getString("COLOR_PRODUCTO"));
                producto.setTamanio(rs.getString("TAMANIO_PRODUCTO"));
                producto.setMarca(rs.getString("MARCA_PRODUCTO"));
                producto.setDescripcion(rs.getString("DESCRIPCION_PRODUCTO"));
                producto.setGanacia(rs.getInt("MARCO_GANANCIA_PRODUCTO"));
                producto.setExistencia(rs.getInt("EXISTENCIA_PRODUCTO"));
                producto.setCategoria(rs.getInt("ID_CATEGORIA_CATEGORIA"));
                vv =true;
            }else{
                vv = false;
            }
            rs.close();
            accesoBD.close();
        }catch(SQLException e){
            System.out.println(e);
            }
        return producto;
    }
    
    public int actualizarCompra(Compras compra,int id){
        int resultado =0;
      Connection accesoBD = miConexion.getConexion();
     try{
            ps = accesoBD.prepareStatement("UPDATE COMPRAS SET"
            + " COSTO_COMPRA = ?"
            + " WHERE ID_PRODUCTO_PRODUCTO = ?"); 
            ps.setFloat(1, compra.getCostoCompra());
            ps.setInt(2,id);
            
            resultado = ps.executeUpdate();
            accesoBD.close();
    }catch(SQLException e){
            System.out.println(e);
        }
     return resultado;
    }
    
    public int registrarProducto(Productos producto,int id){
        int resultado=0;
        Connection accesoBD = miConexion.getConexion();
        try{
            ps = accesoBD.prepareStatement("UPDATE PRODUCTOS SET"
            + " ID_PRODUCTO = ?,"
            + "COLOR_PRODUCTO = ?,"
            + "TAMANIO_PRODUCTO = ?,"
            + "MARCA_PRODUCTO = ?,"
            + "DESCRIPCION_PRODUCTO = ?,"
            + "MARCO_GANANCIA_PRODUCTO = ?,"
            + "EXISTENCIA_PRODUCTO = ?,"
            + "ID_CATEGORIA_CATEGORIA = ? WHERE ID_PRODUCTO = ?");
        
            ps.setInt(1,producto.getId());
            ps.setString(2, producto.getColor());
            ps.setString(3, producto.getTamanio());
            ps.setString(4, producto.getMarca());
            ps.setString(5, producto.getDescripcion());
            ps.setInt(6, producto.getGanacia());
            ps.setInt(7,producto.getExistencia());
            ps.setInt(8,producto.getCategoria());
            ps.setInt(9,id);
        
            resultado = ps.executeUpdate();
        
            accesoBD.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return resultado;
    }
    
    public int registraCategoria(Categorias categoria){
        int resultado =0;
        Connection accesoBD = miConexion.getConexion();
        try{
            ps = accesoBD.prepareStatement("INSERT INTO CATEGORIAS(NOMBRE_CATEGORIA) VALUES(?)");
            ps.setString(1,categoria.getNombre());
            resultado = ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);   
        }
        return resultado;
    }
    
    public int eliminarCategoria(Categorias categoria){
        Connection accesoBD = miConexion.getConexion();
        int resultado = 0;
            try{
            ps = accesoBD.prepareStatement("DELETE FROM CATEGORIAS WHERE NOMBRE_CATEGORIA = ?");
            ps.setString(1, categoria.getNombre());
            resultado = ps.executeUpdate();
            accesoBD.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return resultado;
    }
    
    public int eliminarProducto(int codigo){
      Connection accesoBD = miConexion.getConexion();
      int resultado =0;
      try{
            ps = accesoBD.prepareStatement("DELETE FROM PRODUCTOS WHERE ID_PRODUCTO = ?");
            ps.setInt(1,codigo);
            resultado = ps.executeUpdate();
            accesoBD.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return resultado;
      
    }
    
    public int listarProductos(Categorias categoria){
        Connection accesoBD = miConexion.getConexion();
        int columnas =0;
        try{
           if(categoria.getId()==0){
               ps = accesoBD.prepareStatement("SELECT PRODUCTOS.*, COMPRAS.COSTO_COMPRA, VENTAS.PRECIO_VENTA FROM PRODUCTOS INNER JOIN COMPRAS ON ID_PRODUCTO=ID_PRODUCTO_PRODUCTO INNER JOIN VENTAS ON COMPRAS.ID_PRODUCTO_PRODUCTO=VENTAS.ID_PRODUCTO_PRODUCTO");
           }else{
             ps =accesoBD.prepareStatement("SELECT PRODUCTOS.*, COMPRAS.COSTO_COMPRA,(PRODUCTOS.MARCO_GANANCIA_PRODUCTO*COMPRAS.COSTO_COMPRA)/100 AS PRECIO_VENTA FROM PRODUCTOS INNER JOIN COMPRAS ON ID_PRODUCTO=ID_PRODUCTO_PRODUCTO WHERE ID_CATEGORIA_CATEGORIA = ?");
            ps.setInt(1,categoria.getId());   
           }
            rs = ps.executeQuery();
            ResultSetMetaData rsM = rs.getMetaData();
            columnas = rsM.getColumnCount();
        }catch(Exception e){
            System.out.println(e);
        }
        return columnas;
    }
    
    public int buscarProducto(String c) {
       int columnas =0; 
       try {
           
//            while (modeloTablaBuscarProducto.getRowCount() != 0) {
//                modeloTablaBuscarProducto.removeRow(0);
//            }
            Categorias categoria = new Categorias();
            Connection accesoBD = miConexion.getConexion();
            ps = accesoBD.prepareStatement("SELECT NOMBRE_CATEGORIA FROM CATEGORIAS WHERE NOMBRE_CATEGORIA LIKE ?");         
            ps.setString(1,"%"+c+"%");
            
            rs = ps.executeQuery();
            
            ResultSetMetaData rsM = rs.getMetaData();
            columnas = rsM.getColumnCount();
//            
//            recorreBusqueda = ps.executeQuery();
//            
//            while (recorreBusqueda.next()) {
//                categoria.setNombre(recorreBusqueda.getString("NOMBRE_CATEGORIA"));
//
//                Object[] camposCategoria = {categoria.getNombre()};
//                tabla.addRow(camposCategoria);
//          }
//
        } catch (SQLException ex) {
           System.out.println(ex);
//           
    } 
        return columnas;
    }
    
    public void buscarCategoria(String texto, DefaultListModel modeloLista){
        try{
            while(modeloLista.getSize()!=0){
                modeloLista.remove(0);
            }
            Categorias categoria = new Categorias();
            
            Connection accesoBD = miConexion.getConexion();
            
            String consulta = "SELECT NOMBRE_CATEGORIA FROM CATEGORIAS WHERE NOMBRE_CATEGORIA LIKE ?";
            
            ps = accesoBD.prepareStatement(consulta);
            ps.setString(1,"%"+ texto +"%");
            rs =ps.executeQuery();
            int i=0;
            while(rs.next()){
                categoria.setNombre(rs.getString("NOMBRE_CATEGORIA"));
                
//                Object[] c ={
//                    categoria.getNombre()
//                };
                    
                modeloLista.add(i++,categoria.getNombre());
             
            }
            
            rs.close();
accesoBD.close();
        }catch (SQLException ex) {
           System.out.println(ex);
//           
    } 

    }

    public boolean isVv() {
        return vv;
    }

    public void setVv(boolean vv) {
        this.vv = vv;
    }

    public ResultSet getRs() {
        return rs;
    }
    
    
}
