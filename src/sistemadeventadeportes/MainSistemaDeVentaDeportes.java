/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadeventadeportes;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import utilidades.UtilVentanas;
import vistas.PanelLogin;
import vistas.PanelSecciones;
import vistas.VentanaSistema;

/**
 *
 * @author jarv
 */
public class MainSistemaDeVentaDeportes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        VentanaSistema f = new VentanaSistema();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamañoPantalla = pantalla.getScreenSize();
        int x = (tamañoPantalla.width / 2) - 225;
        int y = (tamañoPantalla.height / 2) - 225;
        f.setBounds(0, 0, 1200, 800);
        //f.setMinimumSize(new Dimension(450, 450));
        //f.setMaximumSize(new Dimension(450, 450));
        UtilVentanas.setVentanaPadre(f);
        f.add(new PanelSecciones());
        f.setVisible(true);
    }
    
}
