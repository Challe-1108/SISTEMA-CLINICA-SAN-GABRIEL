package logica;

import datos.AtencionMedicaDAO;
import entidades.AtencionMedica;
import entidades.Diagnostico;
import entidades.SignosVitales;

import javax.swing.JOptionPane;

public class AtencionMedicaLOG {

    public boolean registrarAtencion(AtencionMedica atencion) {

        // 1. Validaciones generales de la Atención
        if (atencion == null) {
            JOptionPane.showMessageDialog(null, "No se proporcionaron datos para la atención médica.", "Dato Incorrecto", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validación opcional/obligatoria según si la atención siempre viene ligada a un código de cita
        if (atencion.getCodigoCita() != null && !atencion.getCodigoCita().isEmpty()) {
            if (!atencion.getCodigoCita().matches("CIT-\\d{4}")) {
                JOptionPane.showMessageDialog(null, "El código de cita no tiene el formato válido (ej: CIT-0001).", "Dato Incorrecto", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        if (atencion.getMotivoConsulta() == null || atencion.getMotivoConsulta().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El motivo de la consulta es obligatorio.", "Dato Incorrecto", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // 2. Validaciones de Signos Vitales
        SignosVitales sv = atencion.getSignosVitales();
        if (sv != null) {
            if (sv.getPeso() <= 0) {
                JOptionPane.showMessageDialog(null, "El peso del paciente debe ser mayor a 0 kg.", "Dato Incorrecto", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (sv.getTalla() <= 0) {
                JOptionPane.showMessageDialog(null, "La talla del paciente debe ser mayor a 0 cm.", "Dato Incorrecto", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (sv.getPas() > 0 && sv.getPad() > 0 && sv.getPas() <= sv.getPad()) {
                JOptionPane.showMessageDialog(null, "La Presión Arterial Sistólica (PAS) debe ser mayor a la Diastólica (PAD).", "Dato Incorrecto", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        // 3. Validaciones de Diagnósticos
        if (atencion.getListaDiagnosticos() == null || atencion.getListaDiagnosticos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe registrar al menos un diagnóstico para el paciente.", "Dato Incorrecto", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        for (Diagnostico d : atencion.getListaDiagnosticos()) {
            if (d.getDescripcion() == null || d.getDescripcion().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Uno de los diagnósticos no cuenta con una descripción.", "Dato Incorrecto", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (d.getTipo() == null || (!d.getTipo().equalsIgnoreCase("Presuntivo") && !d.getTipo().equalsIgnoreCase("Definitivo"))) {
                JOptionPane.showMessageDialog(null, "El tipo de diagnóstico debe ser 'Presuntivo' o 'Definitivo'.", "Dato Incorrecto", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        // 4. Invocar a la Capa de Datos (DAO)
        boolean guardadoExitoso = AtencionMedicaDAO.registrarAtencionCompleta(atencion);

        if (guardadoExitoso) {
            JOptionPane.showMessageDialog(null, "La atención médica se registró correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrió un error en la base de datos al registrar la atención.", "Error BD", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}