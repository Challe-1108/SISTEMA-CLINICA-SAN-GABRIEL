package datos;

import entidades.SeguroMedico;
import java.sql.*;

public class SeguroDAO {

    public static boolean insertar(SeguroMedico seguro) {
        String sql = "INSERT INTO seguro_medico (compania, numero_poliza, tipo_cobertura, estado) VALUES (?, ?, ?, ?)";
        int filasAfectadas = 0;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, seguro.getCompania());
            ps.setString(2, seguro.getNumeroPoliza());
            ps.setString(3, seguro.getTipoCobertura());
            ps.setBoolean(4, seguro.isEstado());
            filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        seguro.setIdSeguro(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar seguro: " + e.getMessage());
        }

        return filasAfectadas > 0;
    }

    public static boolean actualizar(SeguroMedico seguro) {
        String sql = "UPDATE seguro_medico SET compania=?, numero_poliza=?, tipo_cobertura=? WHERE id_seguro=?";
        int filasAfectadas = 0;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, seguro.getCompania());
            ps.setString(2, seguro.getNumeroPoliza());
            ps.setString(3, seguro.getTipoCobertura());
            ps.setInt(4, seguro.getIdSeguro());
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar seguro: " + e.getMessage());
        }

        return filasAfectadas > 0;
    }

    public static SeguroMedico buscarPorId(int idSeguro) {
        String sql = "SELECT * FROM seguro_medico WHERE id_seguro = ?";
        SeguroMedico seguro = null;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idSeguro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    seguro = mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar seguro: " + e.getMessage());
        }

        return seguro;
    }

    public static boolean eliminarLogico(int idSeguro) {
        String sql = "UPDATE seguro_medico SET estado = false WHERE id_seguro = ?";
        int filasAfectadas = 0;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idSeguro);
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al inactivar seguro: " + e.getMessage());
        }

        return filasAfectadas > 0;
    }

    private static SeguroMedico mapear(ResultSet rs) throws SQLException {
        return new SeguroMedico(
                rs.getInt("id_seguro"),
                rs.getString("compania"),
                rs.getString("numero_poliza"),
                rs.getString("tipo_cobertura"),
                rs.getBoolean("estado")
        );
    }
}
