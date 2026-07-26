/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Rol;
import entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author yordi
 */
public class UsuarioDAO {

    public static boolean registrarUsuario(Usuario usuario){
        String sql = "INSERT INTO Usuarios (username, password, rol, estado) VALUES (?, ?, ?, ?)";
        int filasAfectadas = 0;

        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol().name());
            ps.setBoolean(4, usuario.isEstado());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e){
            System.err.println("Error al registrar Usuario: " + e.getMessage());
        }

        return filasAfectadas>0;
    }

    public static Usuario buscarUsuario(String username, String password){
        String sql = "SELECT * FROM Usuarios WHERE (username = ? AND password = ?)";
        Usuario usuarioEncontrado = null;

        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                usuarioEncontrado = new Usuario();
                usuarioEncontrado.setIdUsuario(rs.getInt("idUsuario"));
                usuarioEncontrado.setUsername(rs.getString("username"));
                usuarioEncontrado.setPassword(rs.getString("password"));
                usuarioEncontrado.setRol(Rol.valueOf(rs.getString("rol")));
                usuarioEncontrado.setEstado(rs.getBoolean("estado"));
            }

        } catch (SQLException e){
            System.err.println("Error al buscar un usuario: " + e.getMessage());
        }

        return usuarioEncontrado;
    }

    public static ArrayList<Usuario> listarUsuarios(){
        String sql = "SELECT * FROM Usuarios";
        ArrayList<Usuario> lista = new ArrayList<>();

        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(Rol.valueOf(rs.getString("rol")));
                usuario.setEstado(rs.getBoolean("estado"));

                lista.add(usuario);
            }

        } catch (SQLException e){
            System.err.println("Error al listar llos usuarios: " + e.getMessage());
        }

        return lista;
    }

    public static boolean actualizarUsuario(Usuario usuario){
        String sql = "UPDATE Usuarios SET username = ?, password = ?, rol = ?, estado = ? WHERE idUsuario = ?";
        int filasAfectadas = 0;

        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol().name());
            ps.setBoolean(4, usuario.isEstado());
            ps.setInt(5, usuario.getIdUsuario());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e){
            System.err.println("Error al actualizar Usuario: " + e.getMessage());
        }

        return filasAfectadas>0;
    }

    public static boolean cambiarPassword(int idUsuario, String nuevaPassword){
        String sql = "UPDATE Usuarios SET password = ? WHERE idUsuario = ?";
        int filasAfectadas = 0;

        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){

            ps.setInt(1, idUsuario);
            ps.setString(2, nuevaPassword);

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e){
            System.err.println("Error al cambiar la password: " + e.getMessage());
        }

        return filasAfectadas>0;
    }

    public static boolean cambiarRol(int idUsuario, Rol nuevoRol){
        String sql = "UPDATE Usuarios SET rol = ? WHERE idUsuario = ?";
        int filasAfectadas = 0;

        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){

            ps.setInt(1, idUsuario);
            ps.setString(2, nuevoRol.name());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e){
            System.err.println("Error al cambiar el rol: " + e.getMessage());
        }

        return filasAfectadas>0;
    }

    public static boolean cambiarEstado(int idUsuario, Boolean nuevoEstado){
        String sql = "UPDATE Usuarios SET estado = ? WHERE idUsuario = ?";
        int filasAfectadas = 0;

        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){

            ps.setInt(1, idUsuario);
            ps.setBoolean(2, nuevoEstado);

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e){
            System.err.println("Error al cambiar el estado: " + e.getMessage());
        }

        return filasAfectadas>0;
    }

    public static boolean eliminarUsuario(int idUsuario){
        String sql = "DELETE FROM Usuarios WHERE idUsuario = ?";
        int filasAfectadas = 0;

        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){

            ps.setInt(1, idUsuario);

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e){
            System.err.println("Error al eliminar un usuario: " + e.getMessage());
        }

        return filasAfectadas>0;
    }
}
