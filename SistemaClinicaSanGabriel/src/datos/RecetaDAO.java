package datos;

import entidades.RecetaMedica;
import java.sql.*;

public class RecetaDAO {

    public static void registrarReceta(RecetaMedica receta, Connection cn) throws SQLException {
        String sqlReceta = "INSERT INTO recetas_medicas (idAtencion) VALUES (?)";
        String sqlDetalle = "INSERT INTO detalle_receta (idReceta, idMedicamento, cantidad, indicacion) VALUES (?, ?, ?, ?)";

        int idRecetaGenerado = -1;
        try (PreparedStatement ps = cn.prepareStatement(sqlReceta, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, receta.getIdAtencion());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idRecetaGenerado = rs.getInt(1);
            }
        }

        try (PreparedStatement ps = cn.prepareStatement(sqlDetalle)) {
            for (RecetaMedica.DetalleReceta item : receta.getDetalles()) {
                ps.setInt(1, idRecetaGenerado);
                ps.setInt(2, item.getIdMedicamento());
                ps.setInt(3, item.getCantidad());
                ps.setString(4, item.getIndicacion());
                ps.executeUpdate();
            }
        }
    }
}