/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.composite;

import entidades.Rol;

import javax.swing.*;

/**
 *
 * @author yordi
 */
public interface ElementoMenu {
    public JComponent obtenerComponenteSwing(Rol rolUsuario);
}
