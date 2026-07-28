/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.SesionUsuario;
import datos.UsuarioDAO;
import entidades.Rol;
import entidades.Usuario;

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.regex.Pattern;

/**
 *
 * @author yordi
 */
public class UsuarioLOG {
    
    private static String cifrarPassword(String passwordSinCifrar) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] passwordBytes = passwordSinCifrar.getBytes();
            byte[] passwordCifradoHash = md.digest(passwordBytes);

            return HexFormat.of().formatHex(passwordCifradoHash);

        } catch (NoSuchAlgorithmException e){
            System.err.println("Error al especificar el tipo de Increptacion: " + e.getMessage());
            return null;
        }
    }

    private static boolean validarPassword(String password) {
        if (password == null || password.length() < 8) {
            JOptionPane.showMessageDialog(null,
                    "Contraseña no válida. Debe tener como mínimo 8 caracteres.",
                    "Validación de datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Pattern patronMayuscula = Pattern.compile(".*[A-Z].*");
        Pattern patronMinuscula = Pattern.compile(".*[a-z].*");
        Pattern patronNumero    = Pattern.compile(".*[0-9].*");
        Pattern patronEspecial = Pattern.compile(".*[@$!%*?&.#_+\\-].*");

        if (!patronMayuscula.matcher(password).matches()) {
            JOptionPane.showMessageDialog(null,
                    "Contraseña no válida. Debe incluir al menos una letra mayúscula.",
                    "Validación de datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!patronMinuscula.matcher(password).matches()) {
            JOptionPane.showMessageDialog(null,
                    "Contraseña no válida. Debe incluir al menos una letra minúscula.",
                    "Validación de datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!patronNumero.matcher(password).matches()) {
            JOptionPane.showMessageDialog(null,
                    "Contraseña no válida. Debe incluir al menos un número.",
                    "Validación de datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!patronEspecial.matcher(password).matches()) {
            JOptionPane.showMessageDialog(null,
                    "Contraseña no válida. Debe incluir al menos un carácter especial (@ $ ! % * ? & . # _ + -).",
                    "Validación de datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static boolean registrarUsuario(String username, String passwordSinCifrar, Rol rol, boolean estado){
        if(username == null || username.equals("")){
            JOptionPane.showMessageDialog(null,
                    "Nombre de Usuario no valido",
                    "Validacion de datos",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(username.length() < 3){
            JOptionPane.showMessageDialog(null,
                    "Nombre de Usuario no valido minimo 3 caracteres",
                    "Validacion de datos",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(passwordSinCifrar == null || passwordSinCifrar.equals("")){
            JOptionPane.showMessageDialog(null,
                    "Contraseña no valido",
                    "Validacion de datos",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!validarPassword(passwordSinCifrar)) return false;

        if(rol == null){
            JOptionPane.showMessageDialog(null,
                    "Rol no valido",
                    "Validacion de datos",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String passwordCifrado = cifrarPassword(passwordSinCifrar);
        Usuario u = new Usuario(0, username, passwordCifrado, rol, estado);

        AuditoriaLOG.registrarAuditoria(SesionUsuario.getInstance().getIdUsuario(), "Usuarios",
                "Registró a " + u.getUsername() + " como " + u.getRol().name());

        return UsuarioDAO.registrarUsuario(u);
    }

    public static Usuario buscarUsuario(String username, String password){
        String passwordCifrada = cifrarPassword(password);

        return UsuarioDAO.buscarUsuario(username, passwordCifrada);
    }

    public static ArrayList<Usuario> listarUsuarios(){
        return UsuarioDAO.listarUsuarios();
    }

    public static ArrayList<Usuario> listarUsuarios(String criterio){
        return UsuarioDAO.listarUsuarios(criterio);
    }

    public static Usuario buscarUsuario(String username){
        return UsuarioDAO.buscarUsuario(username);
    }

    public static Usuario buscarUsuario(int idUsuario){
        return UsuarioDAO.buscarUsuario(idUsuario);
    }

    public static boolean actualizarUsuario(int idUsuario, String username, String passwordSinCifrar, Rol rol, boolean estado){
        if(username == null || username.equals("")){
            JOptionPane.showMessageDialog(null,
                    "Nombre de Usuario no valido",
                    "Validacion de datos",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(username.length() < 3){
            JOptionPane.showMessageDialog(null,
                    "Nombre de Usuario no valido minimo 3 caracteres",
                    "Validacion de datos",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(rol == null){
            JOptionPane.showMessageDialog(null,
                    "Rol no valido",
                    "Validacion de datos",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(passwordSinCifrar == null || passwordSinCifrar.equals("")){
            return UsuarioDAO.actualizarUsuario(new Usuario(idUsuario, username, null, rol, estado));
        }

        if(!validarPassword(passwordSinCifrar)) return false;

        String passwordCifrado = cifrarPassword(passwordSinCifrar);
        Usuario u = new Usuario(idUsuario, username, passwordCifrado, rol, estado);

        return UsuarioDAO.actualizarUsuario(u);
    }

    public static boolean eliminarUsuario(int idUsuario){
        return UsuarioDAO.eliminarUsuario(idUsuario);
    }

}
