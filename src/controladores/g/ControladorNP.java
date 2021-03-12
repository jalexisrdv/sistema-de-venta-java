package controladores.g;

import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import modelos.*;
import modelos.g.CargarCategorias;
import modelos.g.CargarProductos;
import modelos.g.Compras;
import modelos.g.Productos;
import vistas.g.VNuevo;

public class ControladorNP  implements ActionListener{
    
    private final VNuevo ventanaN;
    private CargarProductos cargarP;
    
    public ControladorNP(VNuevo ventanaN){
        this.ventanaN = ventanaN;
        cargarP = new CargarProductos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    JButton sourceButton = (JButton)e.getSource();
   
    switch(sourceButton.getName()){     
        case "guardar":
            Productos producto = new Productos();
            Compras compra = new Compras();
            
            producto.setId(Integer.parseInt(ventanaN.getCampoP1().getText()));
            producto.setColor(ventanaN.getCampoP2().getText());
            producto.setTamanio(ventanaN.getCampoP3().getText());
            producto.setMarca(ventanaN.getCampoP4().getText());
            producto.setDescripcion(ventanaN.getCampoP5().getText());
            producto.setGanacia((int)(ventanaN.getSpinnerPorcentaje().getValue()));
            producto.setCategoria(ventanaN.getComboCategoriasP().getSelectedIndex());
            producto.setExistencia(Integer.parseInt(ventanaN.getCampoP10().getText()));
            compra.setCostoCompra(Integer.parseInt(ventanaN.getCampoP6().getText())); //--
            compra.setCantidad(producto.getExistencia());  
            compra.setId_proveedor(ventanaN.getComboProveedoresP().getSelectedIndex());  //falta asignar los proveedores corectamente 
            compra.setId_producto(producto.getId());
            int resultado = cargarP.registrarProductos(producto,compra);
            
            if(resultado>0){
                
                limpiarCampos();
                JOptionPane.showMessageDialog(null,"REGISTRO COMPLETADO CORRECTAMENTE");
                
            }else{
                JOptionPane.showMessageDialog(null,"ERROR AL REGISTRAR");
            }
            
            break;
        case "cancelar":
            limpiarCampos();
    }
    }
    
    public void limpiarCampos(){
        ventanaN.getCampoP1().setText("");
        ventanaN.getCampoP2().setText("");
        ventanaN.getCampoP3().setText("");
        ventanaN.getCampoP4().setText("");
        ventanaN.getCampoP5().setText("");
        ventanaN.getCampoP10().setText("");
        ventanaN.getSpinnerPorcentaje().setValue(0);
        ventanaN.getComboCategoriasP().setSelectedIndex(0);
        ventanaN.getComboProveedoresP().setSelectedIndex(0);
        ventanaN.getCampoP6().setText("");
    }
  
}
