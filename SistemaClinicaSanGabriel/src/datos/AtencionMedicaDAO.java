package datos;

import entidades.AtencionMedica;
import entidades.Diagnostico;
import entidades.SignosVitales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AtencionMedicaDAO {

    public static boolean registrarAtencionCompleta(AtencionMedica atencion) {
        // Consultas alineadas con la estructura del script SQL
        String sqlAtencion = "INSERT INTO atenciones_medicas (codigoCita, motivoConsulta, antecedentes, planTratamiento, observaciones) VALUES (?, ?, ?, ?, ?)";
        String sqlSignos = "INSERT INTO signos_vitales (idAtencion, pas, pad, temperatura, peso, talla, fc, fr, imc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlDiag = "INSERT INTO diagnosticos_atencion (idAtencion, descripcion, tipo) VALUES (?, ?, ?)";
        String sqlUpdateCita = "UPDATE Citas SET estado = 'Atendida' WHERE codigo = ?";

        Connection cn = null;

        try {
            // Obtener la conexión e iniciar transacción manual
            cn = ConexionBD.getInstancia().getConexion();
            cn.setAutoCommit(false);

            // 1. Guardar la Atención Médica Principal
            int idAtencionGenerado = -1;
            try (PreparedStatement psAtencion = cn.prepareStatement(sqlAtencion, Statement.RETURN_GENERATED_KEYS)) {
                psAtencion.setString(1, atencion.getCodigoCita());
                psAtencion.setString(2, atencion.getMotivoConsulta());
                psAtencion.setString(3, atencion.getAntecedentes());
                psAtencion.setString(4, atencion.getTratamiento()); // Mapea a planTratamiento en MySQL
                psAtencion.setString(5, atencion.getObservaciones());
                psAtencion.executeUpdate();

                try (ResultSet rs = psAtencion.getGeneratedKeys()) {
                    if (rs.next()) {
                        idAtencionGenerado = rs.getInt(1);
                        atencion.setIdAtencion(idAtencionGenerado);
                    } else {
                        throw new SQLException("No se pudo obtener el ID de la atención generada.");
                    }
                }
            }

            // 2. Guardar Signos Vitales
            if (atencion.getSignosVitales() != null) {
                try (PreparedStatement psSignos = cn.prepareStatement(sqlSignos)) {
                    SignosVitales sv = atencion.getSignosVitales();
                    psSignos.setInt(1, idAtencionGenerado);
                    psSignos.setDouble(2, sv.getPas());
                    psSignos.setDouble(3, sv.getPad());
                    psSignos.setDouble(4, sv.getTemperatura());
                    psSignos.setDouble(5, sv.getPeso());
                    psSignos.setDouble(6, sv.getTalla());
                    psSignos.setInt(7, sv.getFc());
                    psSignos.setInt(8, sv.getFr());
                    psSignos.setDouble(9, sv.getImc());
                    psSignos.executeUpdate();
                }
            }

            // 3. Guardar Diagnósticos
            if (atencion.getListaDiagnosticos() != null && !atencion.getListaDiagnosticos().isEmpty()) {
                try (PreparedStatement psDiag = cn.prepareStatement(sqlDiag)) {
                    for (Diagnostico d : atencion.getListaDiagnosticos()) {
                        psDiag.setInt(1, idAtencionGenerado);
                        psDiag.setString(2, d.getDescripcion());
                        psDiag.setString(3, d.getTipo());
                        psDiag.executeUpdate();
                    }
                }
            }

            // 4. Guardar Receta Médica (si contiene medicamentos)
            if (atencion.getReceta() != null && atencion.getReceta().getDetalles() != null
                    && !atencion.getReceta().getDetalles().isEmpty()) {

                atencion.getReceta().setIdAtencion(idAtencionGenerado);
                RecetaDAO.registrarReceta(atencion.getReceta(), cn);
            }

            // 5. Actualizar el estado de la Cita a 'Atendida' por su código
            if (atencion.getCodigoCita() != null && !atencion.getCodigoCita().isEmpty()) {
                try (PreparedStatement psCita = cn.prepareStatement(sqlUpdateCita)) {
                    psCita.setString(1, atencion.getCodigoCita());
                    psCita.executeUpdate();
                }
            }

            // Confirmar transacción
            cn.commit();
            return true;

        } catch (SQLException e) {
            // Revertir todo si falla algún paso
            if (cn != null) {
                try {
                    cn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
            System.err.println("Error en transacción de Atención Médica: " + e.getMessage());
            return false;

        } finally {
            // Restaurar auto-commit
            if (cn != null) {
                try {
                    cn.setAutoCommit(true);
                } catch (SQLException ex) {
                    System.err.println("Error al restaurar autoCommit: " + ex.getMessage());
                }
            }
        }
    }
}