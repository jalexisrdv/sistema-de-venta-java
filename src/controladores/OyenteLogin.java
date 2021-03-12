/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelos.Usuario;
import modelos.db.SQLogin;
import utilidades.UtilVentanas;
import vistas.PanelSecciones;
import vistas.PanelLogin;
import vistas.VentanaSistema;

/**
 *
 * @author jarv
 */
public class OyenteLogin implements ActionListener {

    private SQLogin sqlLogin;
    private PanelLogin panelLogin;

    public OyenteLogin(SQLogin sqlLogin, PanelLogin panelLogin) {
        this.sqlLogin = sqlLogin;
        this.panelLogin = panelLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent componente = (JComponent) e.getSource();
        switch (componente.getName()) {
            case "botonIniciarSesion":
            case "campoContrasena":
            case "campoUsuario":
                String nick = panelLogin.getCampoUsuario().getText();
                String contrasena = panelLogin.getCampoContrasena().getText();
                Usuario usuario = sqlLogin.validarLogin(nick, contrasena);
                if (usuario != null) {
                    UtilVentanas.getVentanaPadre().setVisible(false);
                    VentanaSistema f = new VentanaSistema();
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    Toolkit pantalla = Toolkit.getDefaultToolkit();
                    Dimension tamañoPantalla = pantalla.getScreenSize();
                    int x = (tamañoPantalla.width / 2) - 400;
                    int y = (tamañoPantalla.height / 2) - 300;
                    f.setBounds(x, y, 800, 600);
                    UtilVentanas.setVentanaPadre(f);
                    f.add(new PanelSecciones());
                    f.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(panelLogin, "Contraseña o Usuario Invalido",
                            "Datos incorrectos", JOptionPane.INFORMATION_MESSAGE);
                }
        }
    }

}
