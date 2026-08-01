package datos;

import entidades.*;
import java.util.*;
import java.sql.*;

public class RecetaDAO {
    public static boolean registrarReceta(RecetaMedica rec){
        String sql = "INSERT INTO recetaMedica(idReceta, fechaemision, idPaciente, descripcion, idMedicamento, cantidad)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        int filasAfectadas=0;
        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setInt(1, rec.getIdReceta());
            ps.setDate(2, java.sql.Date.valueOf(rec.getFechaemision()));
            ps.setInt(3, rec.getIdPaciente());
            ps.setString(4, rec.getDescripcion());
            ps.setInt(5, rec.getIdMedicamento());
            ps.setFloat(6, rec.getCantidad());
            
        } catch (SQLException e){
            System.err.println("Error al emitir Receta Medica: " + e.getMessage());
        }

        return filasAfectadas>0;
    }
    
    public static RecetaMedica buscarRecetaPorId(int idReceta) {
        String sql = "SELECT * FROM recetaMedica WHERE idReceta = ?";
        RecetaMedica rm = null;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idReceta);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rm = buscar(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Receta Medica: " + e.getMessage());
        }

        return rm;
    }
    
    private static RecetaMedica buscar(ResultSet rs) throws SQLException {
        return new RecetaMedica(
                rs.getInt("idReceta"),
                rs.getDate("fechaemision") != null ? rs.getDate("fechaemision").toLocalDate() : null,
                rs.getInt("idPaciente"),
                rs.getString("descripcion"),
                rs.getInt("idMedicamento"),
                rs.getFloat("cantidad")
        );
    }
}
