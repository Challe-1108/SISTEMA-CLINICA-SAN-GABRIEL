package datos;

import entidades.Auditoria;

import java.sql.*;

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

}
