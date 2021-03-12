package controladores.g;


import vistas.*;
import java.awt.event.*;
import javax.swing.JButton;
import vistas.g.*;


public class ControladorPrincipal implements ActionListener{

    private final VPrincipal ventanaP;
    private final VNuevo ventanaN;
    private final VModifica ventanaM;
    private final VCategoria ventanaC;
    private final VCatalogo ventanaCT;
    private final VElimina ventanaE;
    
    public ControladorPrincipal(VPrincipal ventanaP,VNuevo ventanaN,VModifica ventanaM,
            VCategoria ventanaC,VCatalogo ventanaCT,VElimina ventanaE){
        this.ventanaP= ventanaP;
        this.ventanaN = ventanaN;
        this.ventanaM = ventanaM;
        this.ventanaC = ventanaC;
        this.ventanaCT = ventanaCT;
        this.ventanaE = ventanaE;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    JButton botonOrigen = (JButton)e.getSource();
        
    switch(botonOrigen.getName()){
        case "nuevo":
            ventanaP.getPanelCentral().removeAll();
            ventanaP.getPanelCentral().add(ventanaN);
            ventanaP.getPanelCentral().revalidate();
            ventanaP.getPanelCentral().repaint();   
        break;
        case "modificar":
            ventanaP.getPanelCentral().removeAll();
            ventanaP.getPanelCentral().add(ventanaM);
            ventanaP.getPanelCentral().revalidate();
            ventanaP.getPanelCentral().repaint();
            break;
        case "eliminar":
            ventanaP.getPanelCentral().removeAll();
            ventanaP.getPanelCentral().add(ventanaE);
            ventanaP.getPanelCentral().revalidate();
            ventanaP.getPanelCentral().repaint();
            break;
        case "departamento":
            ventanaP.getPanelCentral().removeAll();
            ventanaP.getPanelCentral().add(ventanaC);
            ventanaP.getPanelCentral().revalidate();
            ventanaP.getPanelCentral().repaint();
        break; 
        case "catalogo":
            ventanaP.getPanelCentral().removeAll();
            ventanaP.getPanelCentral().add(ventanaCT);
            ventanaP.getPanelCentral().revalidate();
            ventanaP.getPanelCentral().repaint();
        }
    }  
}
