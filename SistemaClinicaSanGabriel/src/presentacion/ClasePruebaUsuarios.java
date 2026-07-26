package presentacion;

import datos.UsuarioDAO;
import entidades.Rol;
import entidades.Usuario;
import logica.UsuarioLOG;

public class ClasePruebaUsuarios {

    static void main() {

       // UsuarioLOG.registrarUsuario("Yordin_CR", "%Yordin60", Rol.ADMINISTRADOR, true);

        System.out.println(UsuarioLOG.buscarUsuario("Yordin_CR", "%Yordin60"));

    }

}
