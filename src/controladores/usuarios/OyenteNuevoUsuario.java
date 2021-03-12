/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.usuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import modelos.Usuario;
import modelos.db.SQLUsuario;
import vistas.usuarios.PanelNuevoUsuario;

/**
 *
 * @author Bomby007
 */
public class OyenteNuevoUsuario implements ActionListener{

    private SQLUsuario sqlUsuario;
    private PanelNuevoUsuario panelNuevoUsuario;
    
    public OyenteNuevoUsuario(SQLUsuario sqlUsuario, PanelNuevoUsuario panelNuevoUsuario ){
     this.sqlUsuario = sqlUsuario;
        this.panelNuevoUsuario = panelNuevoUsuario;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton origen = (JButton) ae.getSource();
        switch (origen.getName()){
            case "guardar":
                agregarUsuario();
                break;
            case "cancelar":
                limpiarCampos();
                break;
        }
    }
    
    
    
    public void limpiarCampos() {
        panelNuevoUsuario.getCampoNombre().setText("");
        panelNuevoUsuario.getCampoApellidoP().setText("");
        panelNuevoUsuario.getCampoApellidoM().setText("");
        panelNuevoUsuario.getCampoContrseña().setText("");
        panelNuevoUsuario.getCampoUsuario().setText("");
    }

    private void agregarUsuario() {
         Usuario usuario = new Usuario();
                usuario.setNombre(panelNuevoUsuario.getCampoNombre().getText());
                usuario.setApellidoPaterno(panelNuevoUsuario.getCampoApellidoP().getText());
                usuario.setApellidoMaterno(panelNuevoUsuario.getCampoApellidoM().getText());
                usuario.setContrasena(panelNuevoUsuario.getCampoContrseña().getText());
                usuario.setNick(panelNuevoUsuario.getCampoUsuario().getText());
                usuario.setIdCargo(panelNuevoUsuario.getComboCargo().getSelectedIndex());
                sqlUsuario.registrarUsuario(usuario);
                limpiarCampos();
    }
    
    
    
}
