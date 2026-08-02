/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import entidades.*;
import datos.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */

public class CitaService {

    public boolean registrarCita(Cita cita) {
        // RN-16: solo se pueden programar citas para pacientes registrados
        PacienteDAO pacienteDAO = new PacienteDAO();
        Paciente paciente = pacienteDAO.buscarPorHistoriaClinica(cita.getNumeroHistoriaClinica());
        if (paciente == null) {
            JOptionPane.showMessageDialog(null,
                    "No existe un paciente registrado con la historia clinica " + cita.getNumeroHistoriaClinica() + ".",
                    "Paciente no registrado", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // RN-14 / RN-19: la hora de la cita debe estar dentro del horario registrado del medico
        if (!estaDentroDeHorario(cita.getMedico(), cita.getFecha(), cita.getHora())) {
            JOptionPane.showMessageDialog(null,
                    "La hora seleccionada no esta dentro del horario de atencion del medico.",
                    "Horario no permitido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // RN-15 / RN-17: verificar disponibilidad del medico antes de registrar
        if (CitaDAO.medicoOcupado(cita.getMedico(), cita.getFecha(), cita.getHora())) {
            JOptionPane.showMessageDialog(null,
                    "El medico ya tiene una cita registrada en esa fecha y hora.",
                    "Medico no disponible", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return CitaDAO.registrarCita(cita);
    }

    // RN-21: cancelacion permitida solo hasta 2 horas antes de la cita
    public boolean cancelarCita(String codigoCita) {
        Cita cita = CitaDAO.buscarPorCodigo(codigoCita);
        if (cita == null) {
            JOptionPane.showMessageDialog(null,
                    "No se encontro la cita.",
                    "Cita no encontrada", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        LocalDateTime fechaHoraCita = obtenerFechaHora(cita.getFecha(), cita.getHora());
        LocalDateTime ahora = LocalDateTime.now();

        if (fechaHoraCita.minusHours(2).isBefore(ahora)) {
            JOptionPane.showMessageDialog(null,
                    "Solo se puede cancelar una cita hasta 2 horas antes de la hora programada.",
                    "Cancelacion no permitida", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return CitaDAO.actualizarEstadoCita(codigoCita, "Cancelada");
    }

    // RN-22: solo se puede reprogramar si la cita no ha sido atendida
    public boolean reprogramarCita(String codigoCita, String nuevaFecha, String nuevaHora) {
        Cita cita = CitaDAO.buscarPorCodigo(codigoCita);
        if (cita == null) {
            JOptionPane.showMessageDialog(null,
                    "No se encontro la cita.",
                    "Cita no encontrada", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (cita.getEstado().equals("Atendida")) {
            JOptionPane.showMessageDialog(null,
                    "No se puede reprogramar una cita que ya fue atendida.",
                    "Reprogramacion no permitida", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // RN-14 / RN-19 / RN-15 / RN-17: validar de nuevo horario y disponibilidad para la nueva fecha/hora
        if (!estaDentroDeHorario(cita.getMedico(), nuevaFecha, nuevaHora)) {
            JOptionPane.showMessageDialog(null,
                    "La nueva hora no esta dentro del horario de atencion del medico.",
                    "Horario no permitido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (CitaDAO.medicoOcupado(cita.getMedico(), nuevaFecha, nuevaHora)) {
            JOptionPane.showMessageDialog(null,
                    "El medico ya tiene otra cita registrada en esa fecha y hora.",
                    "Medico no disponible", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return CitaDAO.reprogramarCita(codigoCita, nuevaFecha, nuevaHora);
    }

    // HU-16 / RN-15: consultar disponibilidad del medico para evitar conflictos de horario
    public boolean consultarDisponibilidad(Medico medico, String fecha, String hora) {
        return !CitaDAO.medicoOcupado(medico, fecha, hora);
    }

    public ArrayList<Cita> listarCitas() {
        return CitaDAO.listarCitas();
    }

    // ---------- Metodos de apoyo ----------

    private boolean estaDentroDeHorario(Medico medico, String fecha, String hora) {
        ArrayList<HorarioMedico> horarios = HorarioDAO.listarHorariosPorMedico(medico);
        String diaSemana = obtenerDiaSemana(fecha);
        LocalTime horaCita = LocalTime.parse(hora);

        for (HorarioMedico h : horarios) {
            if (h.getDiaSemana().equalsIgnoreCase(diaSemana)) {
                LocalTime inicio = LocalTime.parse(h.getHoraInicio());
                LocalTime fin = LocalTime.parse(h.getHoraFin());
                if (!horaCita.isBefore(inicio) && !horaCita.isAfter(fin)) {
                    return true;
                }
            }
        }
        return false;
    }

    private String obtenerDiaSemana(String fecha) {
        LocalDate fechaLocal = LocalDate.parse(fecha);
        java.time.DayOfWeek dia = fechaLocal.getDayOfWeek();

        return switch (dia) {
            case MONDAY -> "Lunes";
            case TUESDAY -> "Martes";
            case WEDNESDAY -> "Miercoles";
            case THURSDAY -> "Jueves";
            case FRIDAY -> "Viernes";
            case SATURDAY -> "Sabado";
            case SUNDAY -> "Domingo";
            default -> "";
        };
    }

    private LocalDateTime obtenerFechaHora(String fecha, String hora) {
        String texto = fecha + "T" + hora;
        return LocalDateTime.parse(texto);
    }
}