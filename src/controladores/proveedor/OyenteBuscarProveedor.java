/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.proveedor;

import vistas.proveedor.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arian
 */
public class OyenteBuscarProveedor  extends KeyAdapter implements ActionListener, DocumentListener{
    
    private VentanaBuscarProveedor ventanaBuscarProveedor;
    private int numFilaSeleccionada;

    public OyenteBuscarProveedor(VentanaBuscarProveedor ventanaBuscarProveedor) {
        
        this.ventanaBuscarProveedor = ventanaBuscarProveedor;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        switch (boton.getName()) {
            case "botonAceptar":
                agregarNombreProveedorBuscado();
                break;
            case "botonCancelar":
                ventanaBuscarProveedor.setVisible(false);
        }
 
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String descripcion = ventanaBuscarProveedor.getCampoBuscarProveedor().getText();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
       String descripcion = ventanaBuscarProveedor.getCampoBuscarProveedor().getText();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String descripcion = ventanaBuscarProveedor.getCampoBuscarProveedor().getText();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                agregarNombreProveedorBuscado();
                break;
            case KeyEvent.VK_ESCAPE:
                ventanaBuscarProveedor.setVisible(false);
        }
    }
    public void agregarNombreProveedorBuscado() {
        DefaultTableModel modeloTablaBuscarProveedor = (DefaultTableModel) ventanaBuscarProveedor.getTablaBuscarProveedor().getModel();
        numFilaSeleccionada = ventanaBuscarProveedor.getTablaBuscarProveedor().getSelectedRow();
        if (numFilaSeleccionada >= 0) {
            String nombre = modeloTablaBuscarProveedor.getValueAt(numFilaSeleccionada, 0).toString();
            ventanaBuscarProveedor.getEtiquetaNombreProveedor().setText(nombre);
            ventanaBuscarProveedor.getProveedor().setTelefono((long) modeloTablaBuscarProveedor.getValueAt(numFilaSeleccionada, 3));
            ventanaBuscarProveedor.setVisible(false);
        }
    }
 
}
