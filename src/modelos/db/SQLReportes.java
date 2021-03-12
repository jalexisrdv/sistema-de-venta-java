package modelos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SQLReportes {

    public void cargaComboFiltros(JComboBox comboTablas) {
        String[] tablas = {
            "LISTADO DE PROVEEDORES, COMPRAS Y PRODUCTOS",
            "EL PRODUCTO MAS VENDIDO",
            "EL CLIENTE QUE MAS COMPRA",
            "EL PROVEEDOR QUE OFRECE MEJOR COSTO COMPRA",
            "MOSTRAR EL MEJOR VENDEDOR",
            "TOTAL DE PRODUCTOS COMPRADOS EN UN MES EN ESPECIFICO",
            "LISTADO DE PRODUCTOS VENTAS Y CLIENTES DE UN MES"
        };
        for (int i = 0; i < tablas.length; i++) {
            comboTablas.addItem(tablas[i]);
        }
    }

    public void estableceFiltros(JTable tabla, int tipo, String fecha) {
        System.out.println("tipo: " + tipo);
        switch (tipo) {
            case 0:
                this.listadoProveedoresComprasProductos(tabla);
                break;
            case 1:
                this.productoMasvendido(tabla);
                break;
            case 2:
                this.clienteMasCompra(tabla);
                break;
            case 3:
                this.proveedorMejorPrecio(tabla);
                break;
            case 4:
                this.mejorVendedor(tabla);
                break;
            case 5:
                this.productosCompradosMesEspecifico(tabla, fecha);
                break;
            case 6:
                this.productosVentasClientesMes(tabla, fecha);
                break;
        }
    }

    public void listadoProveedoresComprasProductos(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        try {
            String sql = "SELECT NOMBRE_PROVEEDOR AS PROVEEDOR, FECHA_COMPRA AS 'FECHA DE COMPRA', \n"
                    + "DESCRIPCION_PRODUCTO AS DESCRIPCION "
                    + "FROM PRODUCTOS, PROVEEDORES INNER JOIN COMPRAS\n"
                    + "ON COMPRAS.ID_PROVEEDOR_PROVEEDOR=PROVEEDORES.ID_PROVEEDOR \n"
                    + "WHERE COMPRAS.ID_PRODUCTO_PRODUCTO=PRODUCTOS.ID_PRODUCTO";
            PreparedStatement consulta = conexion.prepareStatement(sql);
            ResultSet resultados = consulta.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            modelo.setColumnCount(0);
            modelo.addColumn("PROVEEDOR");
            modelo.addColumn("FECHA DE COMPRA");
            modelo.addColumn("DESCRIPCION DEL PRODUCTO");
            while (resultados.next()) {
                Object[] object = {
                    resultados.getString(1),
                    resultados.getString(2),
                    resultados.getString(3)
                };
                modelo.addRow(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void productoMasvendido(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        try {
            String sql = "SELECT DESCRIPCION_PRODUCTO, NOMBRE_CATEGORIA, SUM(UNIDADES_VENTA) AS PRODUCTO_MAS_VENDIDO \n"
                    + "FROM CATEGORIAS, PRODUCTOS INNER JOIN VENTAS ON ID_PRODUCTO=ID_PRODUCTO_PRODUCTO\n"
                    + "WHERE CATEGORIAS.ID_CATEGORIA = PRODUCTOS.ID_CATEGORIA_CATEGORIA\n"
                    + "GROUP BY DESCRIPCION_PRODUCTO, NOMBRE_CATEGORIA ORDER BY PRODUCTO_MAS_VENDIDO DESC";
            PreparedStatement consulta = conexion.prepareStatement(sql);
            ResultSet resultados = consulta.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            modelo.setColumnCount(0);
            modelo.addColumn("DESCRIPCION DEL PRODUCTO");
            modelo.addColumn("CATEGORIA");
            modelo.addColumn("UNIDADES VENDIDAS");
            while (resultados.next()) {
                Object[] object = {
                    resultados.getString(1),
                    resultados.getString(2),
                    resultados.getString(3)
                };
                modelo.addRow(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clienteMasCompra(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        try {
            String sql = "SELECT NOMBRE_CLIENTE, SUM(PRECIO_VENTA) AS TOTAL_COMPRAS_UNIDADES FROM \n"
                    + "VENTAS INNER JOIN CLIENTES\n"
                    + "ON VENTAS.ID_CLIENTE_CLIENTE=CLIENTES.ID_CLIENTE \n"
                    + "GROUP BY NOMBRE_CLIENTE ORDER BY TOTAL_COMPRAS_UNIDADES DESC";
            PreparedStatement consulta = conexion.prepareStatement(sql);
            ResultSet resultados = consulta.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            modelo.setColumnCount(0);
            modelo.addColumn("NOMBRE");
            modelo.addColumn("SALDO");
            while (resultados.next()) {
                Object[] object = {
                    resultados.getString(1),
                    resultados.getString(2)
                };
                modelo.addRow(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void proveedorMejorPrecio(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        try {
            String sql = "SELECT DESCRIPCION_PRODUCTO, NOMBRE_PROVEEDOR, MEJOR_COSTO FROM (SELECT PRODUCTOS.DESCRIPCION_PRODUCTO,\n"
                    + "MIN(COMPRAS.COSTO_COMPRA) AS MEJOR_COSTO\n"
                    + "FROM \n"
                    + "PRODUCTOS, PROVEEDORES INNER JOIN COMPRAS ON ID_PROVEEDOR=ID_PROVEEDOR_PROVEEDOR\n"
                    + "WHERE ID_PRODUCTO=ID_PRODUCTO_PRODUCTO \n"
                    + "GROUP BY PRODUCTOS.DESCRIPCION_PRODUCTO) AS PROVEE, \n"
                    + "(SELECT PROVEEDORES.NOMBRE_PROVEEDOR, PRODUCTOS.DESCRIPCION_PRODUCTO AS DESCRIPCION_PRODUCTO_2,\n"
                    + "MIN(COMPRAS.COSTO_COMPRA) AS MEJOR_COSTO_2\n"
                    + "FROM\n"
                    + "PRODUCTOS, PROVEEDORES INNER JOIN COMPRAS ON ID_PROVEEDOR=ID_PROVEEDOR_PROVEEDOR\n"
                    + "WHERE ID_PRODUCTO=ID_PRODUCTO_PRODUCTO \n"
                    + "GROUP BY PRODUCTOS.DESCRIPCION_PRODUCTO, PROVEEDORES.NOMBRE_PROVEEDOR) AS PROVEE_2 \n"
                    + "WHERE PROVEE.MEJOR_COSTO = PROVEE_2.MEJOR_COSTO_2";
            PreparedStatement consulta = conexion.prepareStatement(sql);
            ResultSet resultados = consulta.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            modelo.setColumnCount(0);
            modelo.addColumn("DESCRIPCION DEL PRODUCTO");
            modelo.addColumn("PROVEEDOR");
            modelo.addColumn("COSTO");
            while (resultados.next()) {
                Object[] object = {
                    resultados.getString(1),
                    resultados.getString(2),
                    resultados.getString(3)
                };
                modelo.addRow(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mejorVendedor(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        try {
            String sql = "SELECT NOMBRE_USUARIO, SUM(PRECIO_VENTA) \n"
                    + "AS VENTAS_TOTALES_PRECIO_USUARIOS FROM VENTAS INNER JOIN USUARIOS \n"
                    + "ON VENTAS.ID_USUARIO_USUARIO=USUARIOS.ID_USUARIO\n"
                    + "GROUP BY NOMBRE_USUARIO ORDER BY VENTAS_TOTALES_PRECIO_USUARIOS DESC";
            PreparedStatement consulta = conexion.prepareStatement(sql);
            ResultSet resultados = consulta.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            modelo.setColumnCount(0);
            modelo.addColumn("NOMBRE");
            modelo.addColumn("SALDO");
            while (resultados.next()) {
                Object[] object = {
                    resultados.getString(1),
                    resultados.getString(2)
                };
                modelo.addRow(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void productosCompradosMesEspecifico(JTable tabla, String mes) {
        Connection conexion = Conexion.getConexion();
        try {
            String sql = "SELECT COMPRAS.ID_PRODUCTO_PRODUCTO, DESCRIPCION_PRODUCTO, \n"
                    + "DATE_FORMAT(FECHA_COMPRA, '%Y-%m-%d'), \n"
                    + "SUM(UNIDADES_COMPRA) AS TOTAL_PRODUCTOS_COMPRADOS\n"
                    + "FROM COMPRAS INNER JOIN PRODUCTOS WHERE DATE_FORMAT(FECHA_COMPRA, '%Y-%m')=?\n"
                    + "GROUP BY ID_PRODUCTO, DESCRIPCION_PRODUCTO, ID_PRODUCTO_PRODUCTO, \n"
                    + "FECHA_COMPRA HAVING PRODUCTOS.ID_PRODUCTO=COMPRAS.ID_PRODUCTO_PRODUCTO\n"
                    + "AND FECHA_COMPRA \n"
                    + "ORDER BY TOTAL_PRODUCTOS_COMPRADOS DESC";
            PreparedStatement consulta = conexion.prepareStatement(sql);
            consulta.setString(1, mes);
            ResultSet resultados = consulta.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            modelo.setColumnCount(0);
            modelo.addColumn("ID DEL PRODUCTO");
            modelo.addColumn("DESCRIPCION DEL PRODUCTO");
            modelo.addColumn("FECHA DE COMPRA");
            modelo.addColumn("CANTIDAD COMPRADA");
            while (resultados.next()) {
                Object[] object = {
                    resultados.getString(1),
                    resultados.getString(2),
                    resultados.getString(3),
                    resultados.getString(4)
                };
                modelo.addRow(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarMesesCompras(JComboBox comboFehcas) {
        Connection conexion = Conexion.getConexion();
        comboFehcas.removeAllItems();
        try {
            String sql = "SELECT DATE_FORMAT(FECHA_COMPRA, '%Y-%m') AS FECHA FROM COMPRAS \n"
                    + "GROUP BY FECHA ORDER BY FECHA DESC;";
            PreparedStatement consulta = conexion.prepareStatement(sql);
            ResultSet resultados = consulta.executeQuery();
            while (resultados.next()) {
                comboFehcas.addItem(resultados.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarMesesVentas(JComboBox comboFehcas) {
        Connection conexion = Conexion.getConexion();
        comboFehcas.removeAllItems();
        try {
            String sql = "SELECT DATE_FORMAT(FECHA_VENTA, '%Y-%m') AS FECHA FROM VENTAS \n"
                    + "GROUP BY FECHA ORDER BY FECHA DESC";
            PreparedStatement consulta = conexion.prepareStatement(sql);
            ResultSet resultados = consulta.executeQuery();
            while (resultados.next()) {
                comboFehcas.addItem(resultados.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void productosVentasClientesMes(JTable tabla, String mes) {
        Connection conexion = Conexion.getConexion();
        try {
            String sql = "SELECT NOMBRE_CLIENTE, FECHA_VENTA, DESCRIPCION_PRODUCTO \n"
                    + "FROM CLIENTES, PRODUCTOS INNER JOIN VENTAS\n"
                    + "ON PRODUCTOS.ID_PRODUCTO=VENTAS.ID_PRODUCTO_PRODUCTO \n"
                    + "WHERE VENTAS.ID_CLIENTE_CLIENTE=CLIENTES.ID_CLIENTE\n"
                    + "AND DATE_FORMAT(FECHA_VENTA, '%Y-%m')=?";
            PreparedStatement consulta = conexion.prepareStatement(sql);
            consulta.setString(1, mes);
            ResultSet resultados = consulta.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            modelo.setColumnCount(0);
            modelo.addColumn("NOMBRE DEL CLIENTE");
            modelo.addColumn("FECHA DE VENTA");
            modelo.addColumn("DESCRIPCION DEL PRODUCTO");
            while (resultados.next()) {
                Object[] object = {
                    resultados.getString(1),
                    resultados.getString(2),
                    resultados.getString(3)
                };
                modelo.addRow(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ventasMesEspecifico(JTable tabla, String mes) {
        Connection conexion = Conexion.getConexion();
        try {
            String sql = "SELECT NOMBRE_CLIENTE, DATE_FORMAT(FECHA_VENTA, '%Y-%m-%d'), DESCRIPCION_PRODUCTO \n"
                    + "FROM CLIENTES, PRODUCTOS INNER JOIN VENTAS\n"
                    + "ON PRODUCTOS.ID_PRODUCTO=VENTAS.ID_PRODUCTO_PRODUCTO \n"
                    + "WHERE VENTAS.ID_CLIENTE_CLIENTE=CLIENTES.ID_CLIENTE\n"
                    + "AND DATE_FORMAT(FECHA_VENTA, '%Y-%m')=?";
            PreparedStatement consulta = conexion.prepareStatement(sql);
            consulta.setString(1, mes);
            ResultSet resultados = consulta.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            modelo.setColumnCount(0);
            modelo.addColumn("NOMBRE DEL CLIENTE");
            modelo.addColumn("FECHA DE VENTA");
            modelo.addColumn("DESCRIPCION DEL PRODUCTO");
            while (resultados.next()) {
                Object[] object = {
                    resultados.getString(1),
                    resultados.getString(2),
                    resultados.getString(3)
                };
                modelo.addRow(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
