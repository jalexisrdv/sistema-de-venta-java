/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.g.ControladorC;
import controladores.g.ControladorCatalogo;
import controladores.g.ControladorES;
import controladores.g.ControladorElimina;
import controladores.g.ControladorMS;
import controladores.g.ControladorModifica;
import controladores.g.ControladorNP;
import controladores.g.ControladorPrincipal;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelos.db.SQLUsuario;
import utilidades.UtilVentanas;
import vistas.clientes.PanelClientes;
import vistas.PanelSecciones;
import vistas.g.VCatalogo;
import vistas.g.VCategoria;
import vistas.g.VElimina;
import vistas.g.VEliminaS;
import vistas.g.VModifica;
import vistas.g.VModificaS;
import vistas.g.VNuevo;
import vistas.g.VPrincipal;
import vistas.proveedor.PanelProveedores;
import vistas.reportes.PanelReportes;
import vistas.usuarios.PanelUsuarios;
import vistas.ventas.PanelVentas;

/**
 *
 * @author jarv
 */
public class OyenteBotonesSecciones implements ActionListener {

    private PanelSecciones panelSecciones;
    private PanelVentas panelVentas;
    private PanelClientes panelClientes;
    private PanelUsuarios panelUsuarios;
    private PanelReportes panelReportes;
    private PanelProveedores panelProveedores;

    public OyenteBotonesSecciones(PanelSecciones panelSecciones) {
        this.panelSecciones = panelSecciones;
        panelVentas = new PanelVentas();
        panelClientes = new PanelClientes();
        panelUsuarios = new PanelUsuarios();
        panelReportes = new PanelReportes();
        panelProveedores = new PanelProveedores();
        panelSecciones.getPanelCentro().add(panelVentas);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component[] botones = panelSecciones.getPanelSurBotones().getComponents();
        JPanel panelCentro = panelSecciones.getPanelCentro();
        JButton boton = (JButton) e.getSource();
        if (!boton.getName().equals("botonSalir")) {
            panelCentro.removeAll();
        }
        switch (boton.getName()) {
            case "seccionVentas":
                panelCentro.add(panelVentas);
                break;
            case "seccionClientes":
                panelCentro.add(panelClientes);
                break;
            case "seccionProductos":
                VNuevo vistaN = new VNuevo();
                VPrincipal vistaP = new VPrincipal(vistaN);

                VModifica vistaM = new VModifica();
                VModificaS vistaMS = new VModificaS();
                VCategoria vistaC = new VCategoria();
                VCatalogo vistaCT = new VCatalogo();
                VElimina vistaE = new VElimina();
                VEliminaS vistaES = new VEliminaS();

                ControladorPrincipal controladorP = new ControladorPrincipal(vistaP, vistaN, vistaM, vistaC, vistaCT, vistaE);

                ControladorNP controladorNP = new ControladorNP(vistaN);
                ControladorModifica controladorM = new ControladorModifica(vistaM, vistaP, vistaMS);
                ControladorC controladorC = new ControladorC(vistaC);
                ControladorCatalogo controladorTC = new ControladorCatalogo(vistaCT);
                ControladorES controladores = new ControladorES(vistaES, vistaP, vistaE);
                ControladorMS controladorMS = new ControladorMS(vistaMS, vistaP, vistaM);
                ControladorElimina controladorE = new ControladorElimina(vistaE, vistaP, vistaES);

                vistaP.addEventos(controladorP);
                vistaN.addEvents(controladorNP);
                vistaM.addEventos(controladorM);
                vistaMS.addEvents(controladorMS);
                vistaC.addEventos(controladorC);
                vistaCT.addEventos(controladorTC);
                vistaE.addEventos(controladorE);
                vistaES.addEvents(controladores);
                panelCentro.add(vistaP);
                break;
            case "seccionReportes":
                panelCentro.add(panelReportes);
                break;
            case "seccionUsuarios":
                panelCentro.add(panelUsuarios);
                break;
            case "seccionProveedores":
                panelCentro.add(panelProveedores);
                break;
            case "botonSalir":
                int opcion = JOptionPane.showConfirmDialog(UtilVentanas.getVentanaPadre(),
                        "¿Desea Salir del Programa?", "Saliendo del Sistema",
                        JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    System.exit(0);
                }
        }
        if (!boton.getName().equals("botonSalir")) {
            boton.setEnabled(false);
            for (int i = 0; i < botones.length; i++) {
                if (!botones[i].getName().equals(boton.getName())) {
                    botones[i].setEnabled(true);
                }
            }
            panelCentro.revalidate();
            panelCentro.repaint();
        }
    }

}
