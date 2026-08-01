package datos;

import entidades.*;
import java.util.*;
import java.sql.*;

public class DiagnosticoDAO {
    
    public static boolean registrarDiagnostico(Diagnostico dg){
        String sql = "INSERT INTO diagnostico(idDiagnostico, idPaciente, fecha, tipo, descripcion, observaciones)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        int filasAfectadas=0;
        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setInt(1, dg.getIdDiagnostico());
            ps.setInt(2, dg.getIdPaciente());
            ps.setDate(3, java.sql.Date.valueOf(dg.getFecha()));
            ps.setString(4, dg.getTipo());
            ps.setString(5, dg.getDescripcion());
            ps.setString(6, dg.getObservaciones());
            
        } catch (SQLException e){
            System.err.println("Error al registrar Diagnostico: " + e.getMessage());
        }

        return filasAfectadas>0;
    }
    
    public static Diagnostico buscarDiagnosticoPorId(int idDiagnostico) {
        String sql = "SELECT * FROM diagnostico WHERE idDiagnostico = ?";
        Diagnostico diag = null;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idDiagnostico);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    diag = buscar(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Diagnostico: " + e.getMessage());
        }

        return diag;
    }
    
    private static Diagnostico buscar(ResultSet rs) throws SQLException {
        return new Diagnostico(
                rs.getInt("idDiagnostico"),
                rs.getInt("idPaciente"),
                rs.getDate("fechaemision") != null ? rs.getDate("fecha").toLocalDate() : null,
                rs.getString("tipo"),
                rs.getString("descripcion"),
                rs.getString("observaciones")
        );
    }
    
}
