/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author jarv
 */
public class UtilVentanas {

    private static JFrame ventana;

    public static void abrirVentanaDialogo(JFrame ventana, JDialog ventanaDialogo, int ancho, int alto) {
        ventanaDialogo.setSize(ancho, alto);
        int cx = ventana.getWidth() / 2;
        int cy = ventana.getHeight() / 2;
        int x1 = cx - ventanaDialogo.getWidth() / 2;
        int y1 = cy - ventanaDialogo.getHeight() / 2;
        ventanaDialogo.setLocation(ventana.getX() + x1, ventana.getY() + y1);
    }

    public static void setVentanaPadre(JFrame ventana) {
        UtilVentanas.ventana = ventana;
    }

    public static JFrame getVentanaPadre() {
        return ventana;
    }

}
