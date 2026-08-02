/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.HorarioDAO;
import datos.SesionUsuario;
import entidades.HorarioMedico;
import entidades.Medico;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.time.LocalTime;

//@Harold

public class HorarioLOG {

    public static boolean registrarHorario(HorarioMedico horario) {
        if (horario.getMedico() == null) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar un medico.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (horario.getDiaSemana().isEmpty() || horario.getHoraInicio().isEmpty()
                || horario.getHoraFin().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Complete dia, hora inicio y hora fin.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar formato de hora inicio (HH:mm, 24 horas)
        if (!horario.getHoraInicio().matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {
            JOptionPane.showMessageDialog(null,
                    "La hora de inicio debe tener el formato HH:mm en formato 24 horas (ej: 08:00).",
                    "Hora invalida", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar formato de hora fin (HH:mm, 24 horas)
        if (!horario.getHoraFin().matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {
            JOptionPane.showMessageDialog(null,
                    "La hora de fin debe tener el formato HH:mm en formato 24 horas (ej: 13:00).",
                    "Hora invalida", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar que la hora fin sea posterior a la hora inicio
        LocalTime horaInicio = LocalTime.parse(horario.getHoraInicio());
        LocalTime horaFin = LocalTime.parse(horario.getHoraFin());
        if (!horaFin.isAfter(horaInicio)) {
            JOptionPane.showMessageDialog(null,
                    "La hora de fin debe ser posterior a la hora de inicio.",
                    "Horario invalido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean exito = HorarioDAO.registrarHorario(horario);
        if (exito) {
            AuditoriaLOG.registrarAuditoria(
                SesionUsuario.getInstance().getIdUsuario(),
                "Horarios",
                "Registró horario para el medico " + horario.getMedico().getCodigo()
                    + " el dia " + horario.getDiaSemana()
                    + " de " + horario.getHoraInicio() + " a " + horario.getHoraFin()
            );
        }
        return exito;
    }

    public static ArrayList<HorarioMedico> listarHorariosPorMedico(Medico medico) {
        return HorarioDAO.listarHorariosPorMedico(medico);
    }

    public static boolean eliminarHorario(String idHorario) {
        boolean exito = HorarioDAO.eliminarHorario(idHorario);
        if (exito) {
            AuditoriaLOG.registrarAuditoria(
                SesionUsuario.getInstance().getIdUsuario(),
                "Horarios",
                "Eliminó el horario N° " + idHorario
            );
        }
        return exito;
    }
}
