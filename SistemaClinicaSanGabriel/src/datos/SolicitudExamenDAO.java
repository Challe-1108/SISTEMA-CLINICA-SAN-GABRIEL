package datos;

import entidades.*;
import java.util.*;
import java.sql.*;

public class SolicitudExamenDAO {
    
    public static boolean registrar(SolicitudExamenLab soli){
        String sql = "INSERT INTO solicitudExamenLab (idSolicitud, idPaciente, fecha, descripcion)"
                + "VALUES (?, ?, ?, ?)";
        int filasAfectadas=0;
        
        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setInt(1, soli.getIdSolicitud());
            ps.setInt(2, soli.getIdPaciente());
            ps.setDate(3, java.sql.Date.valueOf(soli.getFecha()));
            ps.setString(4, soli.getDescripcion());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e){
            System.err.println("Error al Solicitar Examen de Laboratorio: " + e.getMessage());
        }

        return filasAfectadas>0;
            
    }
    
    public static SolicitudExamenLab buscarPorId(int idSolicitud) {
        String sql = "SELECT * FROM solicitudExamenLab WHERE idSolicitud = ?";
        SolicitudExamenLab soli = null;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idSolicitud);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    soli = buscar(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Solicitud de Examen Medico: " + e.getMessage());
        }

        return soli;
    }
    
    private static SolicitudExamenLab buscar(ResultSet rs) throws SQLException {
        return new SolicitudExamenLab(
                rs.getInt("idSolicitud"),
                rs.getInt("idPaciente"),
                rs.getDate("fecha") != null ? rs.getDate("fecha").toLocalDate() : null,
                rs.getString("descripcion"));
    }
}
