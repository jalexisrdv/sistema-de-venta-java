/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.clientes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import modelos.db.SQLClientes;
import vistas.clientes.PanelClientes;
import vistas.clientes.PanelEliminarCliente;
import vistas.clientes.PanelModificarCliente;
import vistas.clientes.PanelNuevoCliente;

/**
 *
 * @author jarv
 */
public class OyenteBotonesClientes implements ActionListener {

    private PanelClientes panelClientes;
    private SQLClientes sqlClientes;
    private PanelNuevoCliente panelNuevoCliente;
    private PanelModificarCliente panelModificarCliente;
    private PanelEliminarCliente panelEliminarCliente;

    public OyenteBotonesClientes(SQLClientes sqlClientes, PanelClientes panelClientes) {
        this.panelClientes = panelClientes;
        this.sqlClientes = sqlClientes;
        panelNuevoCliente = new PanelNuevoCliente(sqlClientes);
        panelModificarCliente = new PanelModificarCliente(sqlClientes);
        panelEliminarCliente = new PanelEliminarCliente(sqlClientes);
        panelClientes.getPanelCentro().add(panelNuevoCliente);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component[] botones = panelClientes.getPanelNorte().getComponents();
        JPanel panelCentro = panelClientes.getPanelCentro();
        JButton boton = (JButton) e.getSource();
        panelCentro.removeAll();
        switch (boton.getName()) {
            case "nuevoCliente":
                panelCentro.add(panelNuevoCliente);
                break;
            case "modificarCliente":
                panelCentro.add(panelModificarCliente);
                break;
            case "eliminarCliente":
                panelCentro.add(panelEliminarCliente);
        }
        boton.setEnabled(false);
        for (int i = 0; i < botones.length; i++) {
            if(!botones[i].getName().equals(boton.getName())) {
                botones[i].setEnabled(true);
            }
        }
        panelCentro.revalidate();
        panelCentro.repaint();
    }

}
