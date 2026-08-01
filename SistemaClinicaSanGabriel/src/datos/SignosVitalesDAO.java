package datos;

import entidades.*;
import java.util.*;
import java.sql.*;

public class SignosVitalesDAO {
    public static boolean registrar(SignosVitales sig){
        String sql = "INSERT INTO signosVitales (temperatura, pulso, presion, respiracion)"
                + "VALUES (?, ?, ?, ?)";
        int filasAfectadas=0;
        
        try(Connection cn = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setFloat(1, sig.getTemperatura());
            ps.setInt(2, sig.getPulso());
            ps.setInt(3, sig.getPresion());
            ps.setInt(4, sig.getRespiracion());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e){
            System.err.println("Error al registrar Signos Vitales: " + e.getMessage());
        }

        return filasAfectadas>0;
            
        }
}

