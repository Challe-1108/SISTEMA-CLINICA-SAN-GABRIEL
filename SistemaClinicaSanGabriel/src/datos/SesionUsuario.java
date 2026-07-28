/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Usuario;

/**
 *
 * @author yordi
 */
public class SesionUsuario {

    private static SesionUsuario instancia;
    private Usuario usuarioLogueado;

    private SesionUsuario(){}

    public static SesionUsuario getInstance(){
        if(instancia == null){
            instancia = new SesionUsuario();
        }

        return instancia;
    }

    public void iniciarSesion(Usuario u){
        this.usuarioLogueado = u;
    }

    public void cerrarSesion(){
        this.usuarioLogueado = null;
    }

    public Usuario getUsuario(){
        return usuarioLogueado;
    }

    public int getIdUsuario(){
        return (usuarioLogueado != null) ? usuarioLogueado.getIdUsuario() : -1;
    }

}
