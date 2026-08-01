package datos;

import entidades.*;
import java.util.*;
import java.sql.*;

public class SignosVitalesDAO {
    public static boolean registrarSignos(SignosVitales sig){
        String sql = "INSERT INTO signosVitales (idSignosVitales, temperatura, pulso, presion, respiracion)"
                + "VALUES (?, ?, ?, ?, ?)";
        int filasAfectadas=0;
        
        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setInt(1, sig.getIdSignosVitales());
            ps.setFloat(2, sig.getTemperatura());
            ps.setInt(3, sig.getPulso());
            ps.setInt(4, sig.getPresion());
            ps.setInt(5, sig.getRespiracion());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e){
            System.err.println("Error al registrar Signos Vitales: " + e.getMessage());
        }

        return filasAfectadas>0;
            
    }
    
    public static SignosVitales buscarSignosPorId(int idSignosVitales) {
        String sql = "SELECT * FROM signosVitales WHERE idSignosVitales = ?";
        SignosVitales sig = null;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idSignosVitales);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sig = buscar(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Signos Vitales: " + e.getMessage());
        }

        return sig;
    }
    
    private static SignosVitales buscar(ResultSet rs) throws SQLException {
        return new SignosVitales(
                rs.getInt("idSignosVitales"),
                rs.getFloat("temperatura"),
                rs.getInt("pulso"),
                rs.getInt("presion"),
                rs.getInt("respiracion")
        );
    }
}

