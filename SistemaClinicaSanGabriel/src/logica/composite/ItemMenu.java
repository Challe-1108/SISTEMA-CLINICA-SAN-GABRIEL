/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.composite;

import entidades.Rol;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 *
 * @author yordi
 */
public class ItemMenu implements ElementoMenu{

    private String texto;
    private Rol[] rolesPermitidos;
    private ActionListener accion;


    public ItemMenu(String texto,ActionListener accion, Rol... rolesPermitidos ) {
        this.texto = texto;
        this.rolesPermitidos = rolesPermitidos;
        this.accion = accion;
    }

    @Override
    public JComponent obtenerComponenteSwing(Rol rolUsuario) {

        for(Rol r : rolesPermitidos){
            if(r == rolUsuario || r == Rol.ADMINISTRADOR){
                JMenuItem mniItem = new JMenuItem(texto);
                mniItem.addActionListener(accion);
                return mniItem;
            }
        }
        return null;
    }
}
