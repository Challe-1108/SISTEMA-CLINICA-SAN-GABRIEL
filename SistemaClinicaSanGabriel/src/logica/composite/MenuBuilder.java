/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.composite;

import entidades.Rol;

import java.awt.event.ActionListener;

/**
 *
 * @author yordi
 */
public class MenuBuilder {
    
    private CategoriaMenu menuContenedor;
    private CategoriaMenu menuActual;

    private MenuBuilder() {
        this.menuContenedor = new CategoriaMenu("Contenedor");
    }

    public static MenuBuilder crear(){
        return new MenuBuilder();
    }

    public MenuBuilder agregarCategoria(String titulo){
        this.menuActual = new CategoriaMenu(titulo);
        this.menuContenedor.agregarElementoHijo(this.menuActual);
        return this;
    }

    public MenuBuilder agregarItem(String texto, ActionListener accion, Rol... rolesPermitidos){
        if(this.menuActual != null){
            this.menuActual.agregarElementoHijo(new ItemMenu(texto, accion, rolesPermitidos));
        }
        return this;
    }

    public CategoriaMenu build(){
        return this.menuContenedor;
    }
}
