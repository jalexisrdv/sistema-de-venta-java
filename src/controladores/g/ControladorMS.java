package controladores.g;

import java.awt.event.*;
import javax.swing.*;
import modelos.g.*;
import vistas.g.*;

public class ControladorMS extends KeyAdapter implements ActionListener{
    private final VModificaS ventanaMS;
    private CargarProductos cargarP;
    private VPrincipal ventanaP;
    private VModifica ventanaM;
    
    public ControladorMS(VModificaS ventanaMS,VPrincipal ventanaP,VModifica ventanaM){
        this.ventanaMS = ventanaMS;
        cargarP = new CargarProductos();
        this.ventanaP = ventanaP;
        this.ventanaM = ventanaM;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonOrigen = (JButton)e.getSource(); 
        
        switch(botonOrigen.getName()){
            case "guardar":
                abrirVentanaSecundaria();
                break;
            case "cancelar":
            ventanaP.getPanelCentral().removeAll();
            ventanaP.getPanelCentral().add(ventanaM);
            ventanaP.getPanelCentral().revalidate();
            ventanaP.getPanelCentral().repaint();   
        }
    }
    
    public void limpiarCampos(){
        ventanaMS.getCampoP1().setText("");
        ventanaMS.getCampoP2().setText("");
        ventanaMS.getCampoP3().setText("");
        ventanaMS.getCampoP4().setText("");
        ventanaMS.getCampoP5().setText("");
        ventanaMS.getCampoP10().setText("");
        ventanaMS.getSpinnerPorcentaje().setValue(0);
        ventanaMS.getComboCategoriasP().setSelectedIndex(0);
        ventanaMS.getCampoP6().setText("");
    }

   

    @Override
    public void keyPressed(KeyEvent e) {
        abrirVentanaSecundaria();
    }

    public void abrirVentanaSecundaria() {
        Productos producto = new Productos();
                Compras compra = new Compras();
                
                producto.setId(Integer.parseInt(ventanaMS.getCampoP1().getText()));
                producto.setColor(ventanaMS.getCampoP2().getText());
                producto.setTamanio(ventanaMS.getCampoP3().getText());
                producto.setMarca(ventanaMS.getCampoP4().getText());
                producto.setDescripcion(ventanaMS.getCampoP5().getText());
                producto.setGanacia((int)(ventanaMS.getSpinnerPorcentaje().getValue()));
                producto.setCategoria(ventanaMS.getComboCategoriasP().getSelectedIndex());
                producto.setExistencia(Integer.parseInt(ventanaMS.getCampoP10().getText()));
                compra.setCostoCompra(Float.valueOf(ventanaMS.getCampoP6().getText()));
                int codigo = Integer.parseInt(ventanaMS.getCampoP1().getText());
                int r = cargarP.registrarProducto(producto,codigo);
                int c =cargarP.actualizarCompra(compra,codigo);
                
                if(r>0 & c>0){
                    limpiarCampos();
                    JOptionPane.showMessageDialog(null,"El producto se modifico Correctamente");
                }else{
                    JOptionPane.showMessageDialog(null,"El producto no se pudo modificar");
                }
    }

    
    
}
