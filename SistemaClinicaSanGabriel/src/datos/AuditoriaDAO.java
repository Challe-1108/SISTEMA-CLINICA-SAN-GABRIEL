package datos;

import entidades.Auditoria;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AuditoriaDAO {

    public static boolean registrarAuditoria(Auditoria auditoria) {
        String sql = "INSERT INTO Auditorias (idUsuario, fecha, hora, modulo, operacion) VALUES (?, ?, ?, ?, ?)";
        int filasAfectadas = 0;

        // ConexionBD debe implementar el patrón Singleton (RN-49)
        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, auditoria.getIdUsuario());
            ps.setDate(2, Date.valueOf(auditoria.getFecha()));
            ps.setTime(3, Time.valueOf(auditoria.getHora()));
            ps.setString(4, auditoria.getModulo());
            ps.setString(5, auditoria.getOperacion());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al registrar auditoría: " + e.getMessage());
        }

        return filasAfectadas > 0;
    }

    public static ArrayList<Auditoria> listarAuditorias(){
        String sql = "SELECT * FROM Auditorias ORDER BY idAuditoria DESC";

        ArrayList<Auditoria> lista = new ArrayList<>();

        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Auditoria au = new Auditoria();
                au.setIdAuditoria(rs.getInt("idAuditoria"));
                au.setIdUsuario(rs.getInt("idUsuario"));
                au.setFecha(rs.getDate("fecha").toLocalDate());
                au.setHora(rs.getTime("hora").toLocalTime());
                au.setModulo(rs.getString("modulo"));
                au.setOperacion(rs.getString("operacion"));

                lista.add(au);
            }

        } catch (SQLException e){
            System.err.println("Error al listar las auditorias: " + e.getMessage());
        }

        return lista;
    }

}
