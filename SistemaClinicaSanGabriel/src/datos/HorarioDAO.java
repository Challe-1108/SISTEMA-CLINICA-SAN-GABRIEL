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

//@Harold
 
public class HorarioDAO {

    public static boolean registrarHorario(HorarioMedico horario){
        String sql = "INSERT INTO Horarios_Medicos (idMedico, diaSemana, horaInicio, horaFin) "
                    + "VALUES (?, ?, ?, ?)";
        int filasAfectadas = 0;
        Connection cn = ConexionBD.getInstancia().getConexion();
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            int idMedico = MedicoDAO.obtenerIdMedico(horario.getMedico().getCodigo());
            ps.setInt(1, idMedico);
            ps.setString(2, horario.getDiaSemana());
            ps.setString(3, horario.getHoraInicio());
            ps.setString(4, horario.getHoraFin());
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error al registrar horario: " + e.getMessage());
        }
        return filasAfectadas > 0;
    }

    public static ArrayList<HorarioMedico> listarHorariosPorMedico(Medico medico){
        String sql = "SELECT * FROM Horarios_Medicos WHERE idMedico = ?";
        ArrayList<HorarioMedico> lista = new ArrayList<>();
        Connection cn = ConexionBD.getInstancia().getConexion();
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            int idMedico = MedicoDAO.obtenerIdMedico(medico.getCodigo());
            ps.setInt(1, idMedico);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HorarioMedico horario = new HorarioMedico();
                horario.setCodigo(String.valueOf(rs.getInt("idHorario")));
                horario.setMedico(medico);
                horario.setDiaSemana(rs.getString("diaSemana"));
                horario.setHoraInicio(rs.getString("horaInicio"));
                horario.setHoraFin(rs.getString("horaFin"));
                lista.add(horario);
            }
        } catch (SQLException e){
            System.err.println("Error al listar horarios del medico: " + e.getMessage());
        }
        return lista;
    }

    public static ArrayList<HorarioMedico> listarHorarios(){
        String sql = "SELECT h.*, m.codigo AS codigoMedico, m.nombres, m.apellidos "
                    + "FROM Horarios_Medicos h INNER JOIN Medicos m ON h.idMedico = m.idMedico";
        ArrayList<HorarioMedico> lista = new ArrayList<>();
        Connection cn = ConexionBD.getInstancia().getConexion();
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Medico medico = new Medico();
                medico.setCodigo(rs.getString("codigoMedico"));
                medico.setNombres(rs.getString("nombres"));
                medico.setApellidos(rs.getString("apellidos"));

                HorarioMedico horario = new HorarioMedico();
                horario.setCodigo(String.valueOf(rs.getInt("idHorario")));
                horario.setMedico(medico);
                horario.setDiaSemana(rs.getString("diaSemana"));
                horario.setHoraInicio(rs.getString("horaInicio"));
                horario.setHoraFin(rs.getString("horaFin"));
                lista.add(horario);
            }
        } catch (SQLException e){
            System.err.println("Error al listar horarios: " + e.getMessage());
        }
        return lista;
    }

    public static boolean eliminarHorario(String idHorario){
        String sql = "DELETE FROM Horarios_Medicos WHERE idHorario = ?";
        int filasAfectadas = 0;
        Connection cn = ConexionBD.getInstancia().getConexion();
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setInt(1, Integer.parseInt(idHorario));
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException e){
            System.err.println("Error al eliminar horario: " + e.getMessage());
        }
        return filasAfectadas > 0;
    }
}
