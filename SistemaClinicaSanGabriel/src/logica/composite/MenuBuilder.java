/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.composite;

import entidades.Rol;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author yordi
 */
public class MenuBuilder {
    
    private JMenuBar barraContenedora;
    private CategoriaMenu menuActual;
    private ArrayList<CategoriaMenu> listaCategorias = new ArrayList<>();

    private MenuBuilder() {
        this.barraContenedora = new JMenuBar();
    }

    public static MenuBuilder crear(){
        return new MenuBuilder();
    }

    public MenuBuilder agregarCategoria(String titulo){
        this.menuActual = new CategoriaMenu(titulo);
        listaCategorias.add(this.menuActual);
        return this;
    }

    public MenuBuilder agregarItem(String texto, ActionListener accion, Rol... rolesPermitidos){
        if(this.menuActual != null){
            this.menuActual.agregarElementoHijo(new ItemMenu(texto, accion, rolesPermitidos));
        }
        return this;
    }

    public JMenuBar build(Rol rolUsuario){
        JMenuBar barraMenu = new JMenuBar();

        for(CategoriaMenu cm : listaCategorias){
            if(cm != null){

                JComponent componente = cm.obtenerComponenteSwing(rolUsuario);

                if(componente != null){
                    barraMenu.add(componente);
                }
            }
        }

        return barraMenu;
    }
}
