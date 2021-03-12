package controladores.g;

import java.awt.event.*;
import javax.swing.*;
import modelos.g.*;
import vistas.g.*;


public class ControladorES implements ActionListener{
    private final VEliminaS ventanaES;
    private CargarProductos cargarP;
    private VPrincipal ventanaP;
    private VElimina ventanaE;
    
    public ControladorES(VEliminaS ventanaES,VPrincipal ventanaP,VElimina ventanaE){
        this.ventanaES = ventanaES;
        cargarP = new CargarProductos();
        this.ventanaP = ventanaP;
        this.ventanaE = ventanaE;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonOrigen = (JButton)e.getSource(); 
        
        switch(botonOrigen.getName()){
            case "guardar":
                int codigo = Integer.parseInt(ventanaES.getCampoP1().getText());
               int r = cargarP.eliminarProducto(codigo);
               if(r>0){
                   JOptionPane.showMessageDialog(null,"Se elimino correctamente el producto"); 
               }else{
                 JOptionPane.showMessageDialog(null,"No se puede realizar la eliminación"); 
               }
                
                break;
            case "cancelar":
            ventanaP.getPanelCentral().removeAll();
            ventanaP.getPanelCentral().add(ventanaE);
            ventanaP.getPanelCentral().revalidate();
            ventanaP.getPanelCentral().repaint();  
        }
    }
    
    public void limpiarCampos(){
        ventanaES.getCampoP1().setText("");
        ventanaES.getCampoP2().setText("");
        ventanaES.getCampoP3().setText("");
        ventanaES.getCampoP4().setText("");
        ventanaES.getCampoP5().setText("");
        ventanaES.getCampoP6().setText("");
       ventanaES.getCampoP7().setText("");
       ventanaES.getCampoP8().setText("");
    }
    
}
