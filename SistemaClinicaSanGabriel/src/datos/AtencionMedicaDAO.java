package datos;

import entidades.*;
import java.sql.*;

public class AtencionMedicaDAO {

    public static boolean registrarAtencion(AtencionMedica atencion) {

        String sql = "INSERT INTO atencionMedica "
                + "(idAtencion, motivo, antecedentes, idSignosVitales, idDiagnostico, tratamiento, observaciones) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        int filasAfectadas = 0;

        try (Connection cn = ConexionBD.getInstancia().getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, atencion.getIdAtencion());
            ps.setString(2, atencion.getMotivo());
            ps.setString(3, atencion.getAntecedentes());
            ps.setInt(4, atencion.getIdSignosVitales());
            ps.setInt(5, atencion.getIdDiagnostico());
            ps.setString(6, atencion.getTratamiento());
            ps.setString(7, atencion.getObservaciones());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al registrar Atención Médica: " + e.getMessage());
        }

        return filasAfectadas > 0;
    }

    public static AtencionMedica buscarAtencionPorId(int idAtencion) {
        String sql = "SELECT * FROM atencionMedica WHERE idAtencion = ?";
        AtencionMedica atencion = null;
        try (Connection cn = ConexionBD.getInstancia().getConexion(); 
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idAtencion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    atencion = buscar(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Atención Médica: " + e.getMessage());
        }
        return atencion;
    }

    private static AtencionMedica buscar(ResultSet rs) throws SQLException {
        return new AtencionMedica.Builder()
                .idAtencion(rs.getInt("idAtencion"))
                .motivo(rs.getString("motivo"))
                .antecedentes(rs.getString("antecedentes"))
                .signos(rs.getInt("idSignosVitales"))
                .diagnostico(rs.getInt("idDiagnostico"))
                .tratamiento(rs.getString("tratamiento"))
                .observaciones(rs.getString("observaciones"))
                .build();
    }
}
