/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.usuarios;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import modelos.db.SQLUsuario;
import vistas.usuarios.PanelEliminarUsuario;
import vistas.usuarios.PanelModificarUsuario;
import vistas.usuarios.PanelNuevoUsuario;
import vistas.usuarios.PanelUsuarios;

/**
 *
 * @author jarv
 */
public class OyenteBotonesUsuario implements ActionListener {

    private final PanelUsuarios panelUsuario;
    private final SQLUsuario sqlUsuario;
    private final PanelNuevoUsuario panelNuevoUsuario;
    private final PanelModificarUsuario panelModificarUsuario;
    private final PanelEliminarUsuario panelEliminarUsuario;

    public OyenteBotonesUsuario(SQLUsuario sqlUsuario, PanelUsuarios panelUsuario) {
        this.panelUsuario = panelUsuario;
        this.sqlUsuario = sqlUsuario;
        panelNuevoUsuario = new PanelNuevoUsuario(sqlUsuario);
        panelModificarUsuario = new PanelModificarUsuario(sqlUsuario);
        panelEliminarUsuario = new PanelEliminarUsuario(sqlUsuario);
        panelUsuario.getPanelCentro().add(panelNuevoUsuario);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component[] botones = panelUsuario.getComponents();
        JPanel panelCentro = panelUsuario.getPanelCentro();
        JButton boton = (JButton) e.getSource();
       panelCentro.removeAll();
        switch (boton.getName()) {
            case "nuevoUsuario":
                panelCentro.add(panelNuevoUsuario);
                break;
            case "modificarUsuario":
                panelCentro.add(panelModificarUsuario);
                break;
            case "eliminarUsuario":
                panelCentro.add(panelEliminarUsuario);
        }
//        boton.setEnabled(false);
//        for (int i = 0; i < botones.length; i++) {
//            if(!botones[i].getName().equals(boton.getName())) {
//                botones[i].setEnabled(true);
//            }
//        }
        
        panelCentro.revalidate();
        panelCentro.repaint();
    }

}
