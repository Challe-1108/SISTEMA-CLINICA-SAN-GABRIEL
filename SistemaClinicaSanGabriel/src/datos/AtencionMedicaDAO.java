package datos;

import entidades.*;
import java.sql.*;

public class AtencionMedicaDAO {

    public static boolean registrarAtencionCompleta(AtencionMedica atencion) {
        String sqlAtencion = "INSERT INTO atenciones_medicas (idCita, motivoConsulta, antecedentes, planTratamiento, observaciones) VALUES (?, ?, ?, ?, ?)";
        String sqlSignos = "INSERT INTO signos_vitales (idAtencion, pas, pad, temperatura, peso, talla, fc, fr, imc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlDiag = "INSERT INTO diagnosticos_atencion (idAtencion, codigoCIE10, descripcion, tipo) VALUES (?, ?, ?, ?)";
        String sqlUpdateCita = "UPDATE citas SET estado = 'Atendida' WHERE idCita = ?";

        Connection cn = null;
        try {
            cn = ConexionBD.getInstancia().getConexion();
            cn.setAutoCommit(false); // Transacción para garantizar consistencia total

            // 1. Guardar Atención
            int idAtencionGenerado = -1;
            try (PreparedStatement ps = cn.prepareStatement(sqlAtencion, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, atencion.getIdCita());
                ps.setString(2, atencion.getMotivoConsulta());
                ps.setString(3, atencion.getAntecedentes());
                ps.setString(4, atencion.getPlanTratamiento());
                ps.setString(5, atencion.getObservaciones());
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idAtencionGenerado = rs.getInt(1);
                    atencion.setIdAtencion(idAtencionGenerado);
                }
            }

            // 2. Guardar Signos Vitales
            if (atencion.getSignosVitales() != null) {
                try (PreparedStatement ps = cn.prepareStatement(sqlSignos)) {
                    SignosVitales sv = atencion.getSignosVitales();
                    ps.setInt(1, idAtencionGenerado);
                    ps.setDouble(2, sv.getPresionArterialSistolica());
                    ps.setDouble(3, sv.getPresionArterialDiastolica());
                    ps.setDouble(4, sv.getTemperatura());
                    ps.setDouble(5, sv.getPeso());
                    ps.setDouble(6, sv.getTalla());
                    ps.setInt(7, sv.getFrecuenciaCardiaca());
                    ps.setInt(8, sv.getFrecuenciaRespiratoria());
                    ps.setDouble(9, sv.getImc());
                    ps.executeUpdate();
                }
            }

            // 3. Guardar Diagnósticos (RN-24)
            try (PreparedStatement ps = cn.prepareStatement(sqlDiag)) {
                for (Diagnostico d : atencion.getListaDiagnosticos()) {
                    ps.setInt(1, idAtencionGenerado);
                    ps.setString(2, d.getCodigoCIE10());
                    ps.setString(3, d.getDescripcion());
                    ps.setString(4, d.getTipo());
                    ps.executeUpdate();
                }
            }

            // 4. Integración con Farmacia (Receta y medicamentos)
            if (atencion.getRecetaMedica() != null && !atencion.getRecetaMedica().getDetalles().isEmpty()) {
                atencion.getRecetaMedica().setIdAtencion(idAtencionGenerado);
                RecetaDAO.registrarReceta(atencion.getRecetaMedica(), cn);
            }

            // 5. Cierre de historial actualizando estado de Cita a 'Atendida'
            try (PreparedStatement ps = cn.prepareStatement(sqlUpdateCita)) {
                ps.setInt(1, atencion.getIdCita());
                ps.executeUpdate();
            }

            cn.commit();
            return true;

        } catch (SQLException e) {
            if (cn != null) {
                try { cn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            System.err.println("Error en transacción de atención médica: " + e.getMessage());
            return false;
        }
    }
}