package datos;


import entidades.DetalleReceta;
import entidades.RecetaMedica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecetaDAO {

    /**
     * Inserta la receta y sus detalles dentro de una conexión con transacción activa.
     * @param receta Objeto RecetaMedica con sus detalles cargados.
     * @param cn Conexión SQL proveniente del AtencionMedicaDAO (gestora de la transacción).
     * @throws SQLException Si ocurre algún error en la inserción.
     */
    public static void registrarReceta(RecetaMedica receta, Connection cn) throws SQLException {
        String sqlReceta = "INSERT INTO recetas_medicas (idAtencion) VALUES (?)";
        String sqlDetalle = "INSERT INTO detalle_receta (idReceta, idMedicamento, cantidad, indicacion) VALUES (?, ?, ?, ?)";

        int idRecetaGenerado = -1;

        // 1. Insertar la cabecera de la Receta
        try (PreparedStatement psReceta = cn.prepareStatement(sqlReceta, Statement.RETURN_GENERATED_KEYS)) {
            psReceta.setInt(1, receta.getIdAtencion());
            psReceta.executeUpdate();

            try (ResultSet rs = psReceta.getGeneratedKeys()) {
                if (rs.next()) {
                    idRecetaGenerado = rs.getInt(1);
                    receta.setIdReceta(idRecetaGenerado);
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para la receta.");
                }
            }
        }

        // 2. Insertar los detalles/medicamentos de la Receta y descontar stock (RN-28)
        if (receta.getDetalles() != null && !receta.getDetalles().isEmpty()) {
            try (PreparedStatement psDetalle = cn.prepareStatement(sqlDetalle)) {
                for (DetalleReceta detalle : receta.getDetalles()) {
                    psDetalle.setInt(1, idRecetaGenerado);
                    psDetalle.setInt(2, detalle.getIdMedicamento());
                    psDetalle.setInt(3, detalle.getCantidad());
                    psDetalle.setString(4, detalle.getIndicacion());
                    psDetalle.executeUpdate();

                    MedicamentoDAO.descontarStock(detalle.getIdMedicamento(), detalle.getCantidad(), cn);
                }
            }
        }
    }
}