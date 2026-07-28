/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */

public class CitaDAO {

    public static boolean registrarCita(Cita cita){
        String sql = "INSERT INTO Citas (codigo, idMedico, numeroHistoriaClinica, fecha, hora, estado, observaciones) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        int filasAfectadas = 0;
        Connection cn = ConexionBD.getInstancia().getConexion();
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            int idMedico = MedicoDAO.obtenerIdMedico(cita.getMedico().getCodigo());
            ps.setString(1, cita.getCodigo());
            ps.setInt(2, idMedico);
            ps.setString(3, cita.getNumeroHistoriaClinica());
            ps.setString(4, cita.getFecha());
            ps.setString(5, cita.getHora());
            ps.setString(6, cita.getEstado());
            ps.setString(7, cita.getObservaciones());
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error al registrar cita: " + e.getMessage());
        }
        return filasAfectadas > 0;
    }

    // RN-15 / RN-17: true si el medico ya tiene una cita activa en esa fecha y hora
    public static boolean medicoOcupado(Medico medico, String fecha, String hora){
        String sql = "SELECT COUNT(*) AS ocupado FROM Citas "
                    + "WHERE idMedico = ? AND fecha = ? AND hora = ? AND estado <> 'Cancelada'";
        Connection cn = ConexionBD.getInstancia().getConexion();
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            int idMedico = MedicoDAO.obtenerIdMedico(medico.getCodigo());
            ps.setInt(1, idMedico);
            ps.setString(2, fecha);
            ps.setString(3, hora);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("ocupado") > 0;
            }
        } catch (SQLException e){
            System.err.println("Error al verificar disponibilidad del medico: " + e.getMessage());
        }
        return false;
    }

    public static ArrayList<Cita> listarCitas(){
        String sql = "SELECT c.*, m.codigo AS codigoMedico, m.nombres, m.apellidos "
                    + "FROM Citas c INNER JOIN Medicos m ON c.idMedico = m.idMedico";
        ArrayList<Cita> lista = new ArrayList<>();
        Connection cn = ConexionBD.getInstancia().getConexion();
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Medico medico = new Medico();
                medico.setCodigo(rs.getString("codigoMedico"));
                medico.setNombres(rs.getString("nombres"));
                medico.setApellidos(rs.getString("apellidos"));

                Cita cita = new Cita.CitaBuilder()
                        .setCodigo(rs.getString("codigo"))
                        .setMedico(medico)
                        .setNumeroHistoriaClinica(rs.getString("numeroHistoriaClinica"))
                        .setFecha(rs.getString("fecha"))
                        .setHora(rs.getString("hora"))
                        .setEstado(rs.getString("estado"))
                        .setObservaciones(rs.getString("observaciones"))
                        .build();
                lista.add(cita);
            }
        } catch (SQLException e){
            System.err.println("Error al listar citas: " + e.getMessage());
        }
        return lista;
    }

    // RN-15 / RN-20 / RN-21
    public static boolean actualizarEstadoCita(String codigoCita, String nuevoEstado){
        String sql = "UPDATE Citas SET estado = ? WHERE codigo = ?";
        int filasAfectadas = 0;
        Connection cn = ConexionBD.getInstancia().getConexion();
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setString(1, nuevoEstado);
            ps.setString(2, codigoCita);
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error al actualizar estado de cita: " + e.getMessage());
        }
        return filasAfectadas > 0;
    }

    // RN-22
    public static boolean reprogramarCita(String codigoCita, String nuevaFecha, String nuevaHora){
        String sql = "UPDATE Citas SET fecha = ?, hora = ?, estado = 'Programada' WHERE codigo = ?";
        int filasAfectadas = 0;
        Connection cn = ConexionBD.getInstancia().getConexion();
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setString(1, nuevaFecha);
            ps.setString(2, nuevaHora);
            ps.setString(3, codigoCita);
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error al reprogramar cita: " + e.getMessage());
        }
        return filasAfectadas > 0;
    }

    public static Cita buscarPorCodigo(String codigo){
        String sql = "SELECT c.*, m.codigo AS codigoMedico, m.nombres, m.apellidos "
                    + "FROM Citas c INNER JOIN Medicos m ON c.idMedico = m.idMedico "
                    + "WHERE c.codigo = ?";
        Cita cita = null;
        Connection cn = ConexionBD.getInstancia().getConexion();
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Medico medico = new Medico();
                medico.setCodigo(rs.getString("codigoMedico"));
                medico.setNombres(rs.getString("nombres"));
                medico.setApellidos(rs.getString("apellidos"));

                cita = new Cita.CitaBuilder()
                        .setCodigo(rs.getString("codigo"))
                        .setMedico(medico)
                        .setNumeroHistoriaClinica(rs.getString("numeroHistoriaClinica"))
                        .setFecha(rs.getString("fecha"))
                        .setHora(rs.getString("hora"))
                        .setEstado(rs.getString("estado"))
                        .setObservaciones(rs.getString("observaciones"))
                        .build();
            }
        } catch (SQLException e){
            System.err.println("Error al buscar cita por codigo: " + e.getMessage());
        }
        return cita;
    }
}
