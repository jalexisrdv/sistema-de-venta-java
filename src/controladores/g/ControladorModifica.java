package controladores.g;

import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import modelos.*;
import modelos.g.*;
import vistas.g.*;

public class ControladorModifica extends KeyAdapter implements ActionListener{
    
    private final VModifica ventanaM;
    private final VPrincipal ventanaP;
    private final VModificaS ventanaMS;
    private CargarProductos cargaP;
    
    
    public ControladorModifica(VModifica ventanaM,VPrincipal ventanaP,VModificaS ventanaMS){
        this.ventanaM =ventanaM;
        this.ventanaP =ventanaP;
        this.ventanaMS = ventanaMS;
        cargaP = new CargarProductos();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonOrigen =(JButton)e.getSource();
        
        switch(botonOrigen.getName()){
          
            case "aceptar":
                abrirVentanaSecundaria();
                }      
    }
    
    public void cargarProducto(Productos producto,Compras compra){ 
        ventanaMS.getCampoP1().setText(String.valueOf(producto.getId()));
        ventanaMS.getCampoP2().setText(producto.getColor());
        ventanaMS.getCampoP3().setText(producto.getTamanio());
        ventanaMS.getCampoP4().setText(producto.getMarca());
        ventanaMS.getCampoP5().setText(producto.getDescripcion());
        ventanaMS.getSpinnerPorcentaje().setValue(producto.getGanacia());
        ventanaMS.getCampoP10().setText(String.valueOf(producto.getExistencia()));
        ventanaMS.getComboCategoriasP().setSelectedIndex(producto.getCategoria());
        ventanaMS.getCampoP6().setText(String.valueOf(compra.getCostoCompra()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER:
                abrirVentanaSecundaria();
                break;
        }
        
        
       
    }

    public void abrirVentanaSecundaria() {
     String v="";
                if(!(ventanaM.getCampoModifica().getText().equalsIgnoreCase(v))){
                    
                    Productos producto = new Productos();
                    producto.setId(Integer.parseInt(ventanaM.getCampoModifica().getText()));
                    producto = cargaP.verificarProducto(producto);
                
                    Compras compra = new Compras();
                    compra.setId_producto(Integer.parseInt(ventanaM.getCampoModifica().getText()));
                    compra = cargaP.verificaCompra(compra);
                    
                    if(cargaP.isVv()==true){               
                        cargarProducto(producto,compra);
                        ventanaP.getPanelCentral().removeAll();
                        ventanaP.getPanelCentral().add(ventanaMS);
                        ventanaP.getPanelCentral().revalidate();
                        ventanaP.getPanelCentral().repaint(); 
                        cargaP.setVv(false);
                    }else{
                    JOptionPane.showMessageDialog(null,"No se ecnontro un producto con ese ID");
                    }   
                }else{
                JOptionPane.showMessageDialog(null,"Ingrese el ID");
                } 
    }
    
    
    
}
