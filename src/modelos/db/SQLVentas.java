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
import modelos.Venta;

/**
 *
 * @author jarv
 */
public class SQLVentas {

    private DefaultTableModel modeloTablaVenta;
    private DefaultTableModel modeloTablaBuscarProducto;
    private DefaultTableModel modeloTablaBuscarCliente;
    private JTable tablaVentasProductos;

    public Producto agregarProductoTabla(String idProducto) {
        boolean productoExistente = validarFilasExistentes(Long.parseLong(idProducto));
        Producto producto = new Producto();
        Connection conexion = Conexion.getConexion();
        String sqlProducto = "SELECT ID_PRODUCTO, DESCRIPCION_PRODUCTO, EXISTENCIA_PRODUCTO, MARCO_GANANCIA_PRODUCTO"
                + " FROM PRODUCTOS WHERE ID_PRODUCTO=?";

        String sqlCompra = "SELECT COSTO_COMPRA FROM COMPRAS WHERE ID_PRODUCTO_PRODUCTO=?";
        PreparedStatement consulta = null;
        try {
            consulta = conexion.prepareStatement(sqlProducto);
            consulta.setString(1, idProducto);
            ResultSet recorre = consulta.executeQuery();
            if (recorre.next()) {
                producto.setIdProducto(recorre.getLong(1));
                producto.setDescripcionProducto(recorre.getString(2));
                producto.setExistenciaProducto(recorre.getInt(3));
                producto.setMarcoGanancia(recorre.getDouble(4));
            }
            consulta = conexion.prepareStatement(sqlCompra);
            consulta.setString(1, idProducto);
            recorre = consulta.executeQuery();
            double precioVenta = 0;
            if (recorre.next()) {
                double precioCostoCompra = recorre.getDouble(1);
                precioVenta = (precioCostoCompra + (precioCostoCompra * (producto.getMarcoGanancia() / 100)));
                int existencia = producto.getExistenciaProducto();
                if (!productoExistente) {
                    Object[] camposProducto = {
                        producto.getIdProducto(),
                        producto.getDescripcionProducto(),
                        precioVenta,
                        1,
                        existencia - 1
                    };
                    modeloTablaVenta.addRow(camposProducto);
                    return producto;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
        return null;
    }

    public void buscarProducto(String descripcion) {
        try {
            //utilizando este while ya no se repite el producto buscado
            //utilizando for si se repite
            while (modeloTablaBuscarProducto.getRowCount() != 0) {
                modeloTablaBuscarProducto.removeRow(0);
            }
            Producto producto = new Producto();
            Connection conexion = Conexion.getConexion();
            String sqlProducto = "SELECT ID_PRODUCTO, DESCRIPCION_PRODUCTO, EXISTENCIA_PRODUCTO, MARCO_GANANCIA_PRODUCTO, "
                    + "ID_CATEGORIA_CATEGORIA"
                    + " FROM PRODUCTOS WHERE DESCRIPCION_PRODUCTO LIKE ?";
            String sqlCompra = "SELECT COSTO_COMPRA FROM COMPRAS WHERE ID_PRODUCTO_PRODUCTO=?";
            String sqlCategoria = "SELECT NOMBRE_CATEGORIA FROM CATEGORIAS WHERE ID_CATEGORIA=?";

            PreparedStatement consultaBusqueda = conexion.prepareStatement(sqlProducto);
            consultaBusqueda.setString(1, "%" + descripcion + "%");
            ResultSet recorreBusqueda = consultaBusqueda.executeQuery();

            PreparedStatement consulta;
            ResultSet recorre;
            double precioVenta = 0;
            String nombreCategoria = null;
            while (recorreBusqueda.next()) {
                producto.setIdProducto(recorreBusqueda.getLong(1));
                producto.setDescripcionProducto(recorreBusqueda.getString(2));
                producto.setExistenciaProducto(recorreBusqueda.getInt(3));
                producto.setMarcoGanancia(recorreBusqueda.getDouble(4));
                producto.setIdCategoria(recorreBusqueda.getInt(5));

                /*RECUPERANDO COSTO_PRECIO y haciendo operaciones para obtener precio venta*/
                consulta = conexion.prepareStatement(sqlCompra);
                consulta.setLong(1, producto.getIdProducto());
                recorre = consulta.executeQuery();
                if (recorre.next()) {
                    double precioCostoCompra = recorre.getDouble(1);
                    precioVenta = (precioCostoCompra + (precioCostoCompra * (producto.getMarcoGanancia() / 100)));
                }

                /*RECUPERANDO CATEGORIA*/
                consulta = conexion.prepareStatement(sqlCategoria);
                consulta.setInt(1, producto.getIdCategoria());
                recorre = consulta.executeQuery();
                if (recorre.next()) {
                    nombreCategoria = recorre.getString(1);
                }

                Object[] camposProducto = {producto.getIdProducto(), producto.getDescripcionProducto(),
                    precioVenta, nombreCategoria, producto.getExistenciaProducto()};
                modeloTablaBuscarProducto.addRow(camposProducto);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public Object[] consultarPrecioProducto(String idProducto) {
        Producto producto = new Producto();
        Connection conexion = Conexion.getConexion();
        String sqlProducto = "SELECT DESCRIPCION_PRODUCTO, MARCO_GANANCIA_PRODUCTO FROM PRODUCTOS WHERE ID_PRODUCTO=?";
        String sqlCompra = "SELECT COSTO_COMPRA FROM COMPRAS WHERE ID_PRODUCTO_PRODUCTO=?";
        PreparedStatement consulta = null;
        try {
            consulta = conexion.prepareStatement(sqlProducto);
            consulta.setString(1, idProducto);
            ResultSet recorre = consulta.executeQuery();
            if (recorre.next()) {
                producto.setDescripcionProducto(recorre.getString(1));
                producto.setMarcoGanancia(recorre.getDouble(2));
            }
            consulta = conexion.prepareStatement(sqlCompra);
            consulta.setString(1, idProducto);
            recorre = consulta.executeQuery();
            double precioVenta = 0;
            if (recorre.next()) {
                double precioCostoCompra = recorre.getDouble(1);
                precioVenta = (precioCostoCompra + (precioCostoCompra * (producto.getMarcoGanancia() / 100)));
                Object[] descripcionYPrecio = {producto.getDescripcionProducto(), precioVenta};
                return descripcionYPrecio;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
        return null;
    }

    public void buscarCliente(String textoBuscar) {
        try {
            //utilizando este while ya no se repite el producto buscado
            //utilizando for si se repite
            while (modeloTablaBuscarCliente.getRowCount() != 0) {
                modeloTablaBuscarCliente.removeRow(0);
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
                modeloTablaBuscarCliente.addRow(camposCliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public void registrarVenta(String tipoCliente, Venta venta, Cliente cliente) {
        try {

            String sqlVenta = "INSERT INTO VENTAS(UNIDADES_VENTA, FECHA_VENTA,"
                    + "PRECIO_VENTA, UTILIDAD_VENTA, ID_PRODUCTO_PRODUCTO,"
                    + "ID_USUARIO_USUARIO, ID_CLIENTE_CLIENTE) VALUES(?, NOW(), ?, ?, ?, ?, ?)";
            Connection conexion = Conexion.getConexion();

            PreparedStatement consultaVenta = conexion.prepareStatement(sqlVenta);
            switch (tipoCliente) {
                case "PUBLICO EN GENERAL":
                    consultaVenta.setInt(6, 1);//EL PRIMER CLIENTE REGISTRADO ES PARA PUBLICO EN GENERAL
                    break;
                default://SI ENTRA AL VALOR POR DEFECTO QUIERE DECIR QUE UN CLIENTE REALIZA LA COMPRA

                    //CONSULTANDO ID_CLIENTE MEDIANTE EL NUMERO DE TELEFONO
                    String sqlCliente = "SELECT ID_CLIENTE, TELEFONO_CLIENTE FROM CLIENTES "
                            + "WHERE TELEFONO_CLIENTE=?";
                    PreparedStatement consultaCliente = conexion.prepareStatement(sqlCliente);
                    consultaCliente.setLong(1, cliente.getTelefono());
                    ResultSet recorreCliente = consultaCliente.executeQuery();
                    int idCliente = 0;
                    if (recorreCliente.next()) {
                        idCliente = recorreCliente.getInt(1);
                    }

                    consultaVenta.setInt(6, idCliente);
            }

            //CONSULTANDO COSTO COMPRA PARA OBTENER LA UTILIDAD Y REGISTRARLA EN VENTA
            String sqlCompra = "SELECT COSTO_COMPRA, ID_PRODUCTO_PRODUCTO"
                    + " FROM COMPRAS WHERE ID_PRODUCTO_PRODUCTO=?";
            PreparedStatement consultaCompra = conexion.prepareStatement(sqlCompra);
            consultaCompra.setLong(1, venta.getIdProducto());
            ResultSet recorreCompra = consultaCompra.executeQuery();
            double costoCompra = 0;
            if (recorreCompra.next()) {
                costoCompra = recorreCompra.getDouble(1);
            }

            //REGISTRANDO VENTA
            consultaVenta.setInt(1, venta.getUnidadesVenta());
            consultaVenta.setDouble(2, venta.getPrecioVenta());
            double utilidadCompra = venta.getPrecioVenta() - costoCompra;
            consultaVenta.setDouble(3, utilidadCompra);
            consultaVenta.setLong(4, venta.getIdProducto());
            consultaVenta.setInt(5, SQLogin.getIdUsuario());//FALTA PONER ID DE USUARIO DINAMICAMENTE
            consultaVenta.executeUpdate();

            //ACTUALIZANDO EXISTENCIA DEL PRODUCTO
            String sqlProducto = "UPDATE PRODUCTOS SET EXISTENCIA_PRODUCTO=EXISTENCIA_PRODUCTO - ? "
                    + "WHERE ID_PRODUCTO=?";
            PreparedStatement consultaProducto = conexion.prepareStatement(sqlProducto);
            consultaProducto.setInt(1, venta.getUnidadesVenta());
            consultaProducto.setLong(2, venta.getIdProducto());
            consultaProducto.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
    }

    /*Si existen productos agregados a la tabla solo se incrementara la cantidad en uno*/
    public boolean validarFilasExistentes(long codigoBarrasNuevo) {
        int numeroFilas = tablaVentasProductos.getRowCount();
        for (int i = 0; i < numeroFilas; i++) {
            long codigoBarras = (long) modeloTablaVenta.getValueAt(i, 0);
            if (codigoBarras == codigoBarrasNuevo) {
                int cantidad = (int) modeloTablaVenta.getValueAt(i, 3);
                int existencia = (int) modeloTablaVenta.getValueAt(i, 4);
                cantidad++;
                modeloTablaVenta.setValueAt(cantidad, i, 3);
                existencia--;
                modeloTablaVenta.setValueAt(existencia, i, 4);
                return true;
            }
        }
        return false;
    }

    public void setTablaVentasProductos(JTable tablaVentasProductos) {
        this.tablaVentasProductos = tablaVentasProductos;
    }

    public JTable getTablaVentasProductos() {
        return tablaVentasProductos;
    }

    public final void setDefaultModeloTablaVenta(DefaultTableModel modeloTablaVenta) {
        this.modeloTablaVenta = modeloTablaVenta;
    }

    public DefaultTableModel getDefaultModeloTablaVenta() {
        return modeloTablaVenta;
    }

    public final void setDefaultModeloTablaBuscarProducto(DefaultTableModel modeloTablaBuscarProducto) {
        this.modeloTablaBuscarProducto = modeloTablaBuscarProducto;
    }

    public DefaultTableModel getDefaultModeloTablaBuscarProducto() {
        return modeloTablaBuscarProducto;
    }

    public final void setDefaultModeloTablaBuscarCliente(DefaultTableModel modeloTablaBuscarCliente) {
        this.modeloTablaBuscarCliente = modeloTablaBuscarCliente;
    }

    public DefaultTableModel getDefaultModeloTablaBuscarCliente() {
        return modeloTablaBuscarCliente;
    }

}
