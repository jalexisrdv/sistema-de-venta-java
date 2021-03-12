/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.g;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import modelos.g.*;
import vistas.g.*;

/**
 *
 * @author GenaroLM
 */
public class ControladorElimina extends KeyAdapter implements ActionListener{
    private final VElimina ventanaE;
    private final VPrincipal ventanaP;
    private final VEliminaS ventanaES;
    private CargarProductos cargaP;
//    private CargarCategorias cargaC;
    
    public ControladorElimina(VElimina ventanaE,VPrincipal ventanaP,VEliminaS ventanaES){
        this.ventanaE = ventanaE;
        this.ventanaP = ventanaP;
        this.ventanaES = ventanaES;
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
    public void cargarProducto(Productos producto){ 
        ventanaES.getCampoP1().setText(String.valueOf(producto.getId()));
        ventanaES.getCampoP2().setText(producto.getColor());
        ventanaES.getCampoP3().setText(producto.getTamanio());
        ventanaES.getCampoP4().setText(producto.getMarca());
        ventanaES.getCampoP5().setText(producto.getDescripcion());
        ventanaES.getCampoP6().setText(String.valueOf(producto.getGanacia()));
        ventanaES.getCampoP7().setText(String.valueOf(producto.getExistencia()));
        ventanaES.getCampoP8().setText(String.valueOf(producto.getCategoria()));         
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
                if(!(ventanaE.getCampoModifica().getText().equalsIgnoreCase(v))){
                    
                    Productos producto = new Productos();
                    producto.setId(Integer.parseInt(ventanaE.getCampoModifica().getText()));
                    producto = cargaP.verificarProducto(producto);
                
                    if(cargaP.isVv()==true){               
                        cargarProducto(producto);
                        ventanaP.getPanelCentral().removeAll();
                        ventanaP.getPanelCentral().add(ventanaES);
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
