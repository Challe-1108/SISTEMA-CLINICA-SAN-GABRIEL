/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.composite;

import entidades.Rol;

import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author yordi
 */
public class CategoriaMenu implements ElementoMenu {
    private String titulo;
    private ArrayList<ElementoMenu> elementosHijos;

    public CategoriaMenu(String titulo) {
        this.titulo = titulo;
        elementosHijos = new ArrayList<>();
    }

    public void agregarElementoHijo(ElementoMenu elemento){
        elementosHijos.add(elemento);
    }


    @Override
    public JComponent obtenerComponenteSwing(Rol rolUsuario) {

        JMenu menuCategoria = new JMenu(titulo);
        int elementosVisibles = 0;

        for(ElementoMenu h : elementosHijos){
            JComponent compHijo = h.obtenerComponenteSwing(rolUsuario);

            if(compHijo != null){
                menuCategoria.add(compHijo);
                elementosVisibles++;
            }
        }

        if(elementosVisibles == 0) return null;

        return menuCategoria;
    }
}
