package datos;

import entidades.*;
import java.util.*;
import java.sql.*;

public class RecetaDAO {
    public static boolean registrar(RecetaMedica rec){
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
}
