package datos;

import entidades.*;
import java.util.*;
import java.sql.*;

public class DiagnosticoDAO {
    
    public static boolean registrar(Diagnostico dg){
        String sql = "INSERT INTO diagnostico(idDiagnostico, idPaciente, fecha, tipo, desctripcion, observaciones)"
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
    
}
