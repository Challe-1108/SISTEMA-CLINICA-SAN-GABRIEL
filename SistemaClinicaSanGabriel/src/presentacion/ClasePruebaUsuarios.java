package presentacion;

import datos.UsuarioDAO;
import entidades.Rol;
import entidades.Usuario;
import logica.UsuarioLOG;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClasePruebaUsuarios {

    static void main() {

        System.out.println();

    }

    static void cargarDatosPrueba(){
        UsuarioLOG.registrarUsuario("admin", "%Admin2026", Rol.ADMINISTRADOR, true);
        UsuarioLOG.registrarUsuario("mrodriguez", "#ModAdmin2", Rol.ADMINISTRADOR, false); // Inactivo

        UsuarioLOG.registrarUsuario("mgarcia", "$Recep123", Rol.RECEPCIONISTA, true);
        UsuarioLOG.registrarUsuario("cpenai", "!Recep456", Rol.RECEPCIONISTA, true);
        UsuarioLOG.registrarUsuario("svaldez", "&Recep789", Rol.RECEPCIONISTA, false); // Inactivo

        UsuarioLOG.registrarUsuario("jandrade", "*Doctor12", Rol.MEDICO, true);
        UsuarioLOG.registrarUsuario("sromero", "%Doctor34", Rol.MEDICO, true);
        UsuarioLOG.registrarUsuario("vhurtado", "?Doctor56", Rol.MEDICO, false); // Inactivo

        UsuarioLOG.registrarUsuario("lflores", ".Enfer901", Rol.ENFERMERA, true);
        UsuarioLOG.registrarUsuario("rtorres", "+Enfer234", Rol.ENFERMERA, true);

        UsuarioLOG.registrarUsuario("pcastro", "-Lab2026!", Rol.LABORATORISTA, true);
        UsuarioLOG.registrarUsuario("knoa", "_Lab2026?", Rol.LABORATORISTA, false); // Inactivo

        UsuarioLOG.registrarUsuario("dchavez", "Farma#123", Rol.FARMACEUTICO, true);
        UsuarioLOG.registrarUsuario("yquispe", "Farma$456", Rol.FARMACEUTICO, true);

        UsuarioLOG.registrarUsuario("Luis", "%123456Lu", Rol.CAJERO, true);
        UsuarioLOG.registrarUsuario("aespinoza", "Caja%7890", Rol.CAJERO, true);
        UsuarioLOG.registrarUsuario("fcampos", "Caja!1122", Rol.CAJERO, false); // Inactivo

        UsuarioLOG.registrarUsuario("director", "Direct#10", Rol.DIRECTOR_MEDICO, true);
    }

}
