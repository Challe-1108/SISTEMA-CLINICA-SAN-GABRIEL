/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yordi
 */
public class UsuarioDAO {

    public boolean registrarUsuario(Usuario usuario){
        String sql = "INSERT INTO Usuarios (username, password, rol, estado) VALUES (?, ?, ?, ?)";
        int filasAfectadas = 0;

        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol());
            ps.setBoolean(4, usuario.isEstado());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e){
            System.err.println("Error al registrar Usuario: " + e.getMessage());
        }

        if(filasAfectadas > 0) return true;
        return false;
    }

    public Boolean validarInicioSesion(String username, String password){
        String sql = "SELECT * FROM Usuarios WHERE (username = ? AND password = ?)";

        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){

            ps.setString(1, username);
            ps.setString(2, password);

            return ps.executeQuery().next();

        } catch (SQLException e){
            System.err.println("Error al buscar un usuario: " + e.getMessage());
        }

        return false;
    }
}
