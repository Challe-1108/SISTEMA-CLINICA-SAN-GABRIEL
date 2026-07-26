package presentacion;

import datos.UsuarioDAO;
import entidades.Rol;
import entidades.Usuario;

public class ClasePruebaUsuarios {

    static void main() {

        System.out.println(UsuarioDAO.buscarUsuario("teffo", "87654321"));

        UsuarioDAO.registrarUsuario(new Usuario(12, "Jeyson", "123456", Rol.MEDICO, true));

    }

}
