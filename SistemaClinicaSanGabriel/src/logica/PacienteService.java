/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author sthef
 */

import datos.PacienteDAO;
import entidades.Paciente;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.swing.JOptionPane;

public class PacienteService {

    private PacienteDAO pacienteDAO;

    public PacienteService() {
        this.pacienteDAO = new PacienteDAO();
    }

    public boolean registrarPaciente(Paciente paciente) {
        // RN-09: Validar datos obligatorios
        if (!validarDatosObligatorios(paciente)) {
            return false;
        }

        // RN-07: DNI único
        Paciente existente = pacienteDAO.buscarPorDni(paciente.getDni());
        if (existente != null) {
            JOptionPane.showMessageDialog(null,
                    "Ya existe un paciente registrado con el DNI " + paciente.getDni() + ".",
                    "DNI duplicado", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // RN-10: Historia clínica única
        Paciente existenteHC = pacienteDAO.buscarPorHistoriaClinica(paciente.getNumeroHistoriaClinica());
        if (existenteHC != null) {
            JOptionPane.showMessageDialog(null,
                    "Ya existe un paciente registrado con la historia clínica " + paciente.getNumeroHistoriaClinica() + ".",
                    "Historia clínica duplicada", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // RN-08: Apoderado obligatorio si es menor de edad
        if (esMenorDeEdad(paciente.getFechaNacimiento()) && paciente.getApoderado() == null) {
            JOptionPane.showMessageDialog(null,
                    "El paciente es menor de edad. Debe registrar un apoderado responsable (RN-08).",
                    "Apoderado requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean exito = pacienteDAO.insertar(paciente);
        if (exito) {
            JOptionPane.showMessageDialog(null, "Paciente registrado correctamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el paciente. Intente nuevamente.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return exito;
    }

    public boolean actualizarPaciente(Paciente paciente) {
        // RN-09: Validar datos obligatorios
        if (!validarDatosObligatorios(paciente)) {
            return false;
        }

        // RN-08: Apoderado obligatorio si es menor de edad
        if (esMenorDeEdad(paciente.getFechaNacimiento()) && paciente.getApoderado() == null) {
            JOptionPane.showMessageDialog(null,
                    "El paciente es menor de edad. Debe registrar un apoderado responsable (RN-08).",
                    "Apoderado requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean exito = pacienteDAO.actualizar(paciente);
        if (exito) {
            JOptionPane.showMessageDialog(null, "Datos del paciente actualizados correctamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el paciente.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return exito;
    }

    public Paciente buscarPorDni(String dni) {
        Paciente paciente = pacienteDAO.buscarPorDni(dni);
        if (paciente == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún paciente con ese DNI.",
                    "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
        return paciente;
    }

    public List<Paciente> buscarPorNombreOApellido(String texto) {
        List<Paciente> resultado = pacienteDAO.buscarPorNombre(texto);
        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron pacientes con ese criterio.",
                    "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
        return resultado;
    }

    public Paciente buscarPorHistoriaClinica(String numeroHistoriaClinica) {
        Paciente paciente = pacienteDAO.buscarPorHistoriaClinica(numeroHistoriaClinica);
        if (paciente == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún paciente con esa historia clínica.",
                    "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
        return paciente;
    }

    public boolean inactivarPaciente(int idPaciente) {
        boolean exito = pacienteDAO.eliminarLogico(idPaciente);
        if (exito) {
            JOptionPane.showMessageDialog(null, "Paciente marcado como inactivo.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo inactivar al paciente.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return exito;
    }

    public List<Paciente> listarPacientes() {
        return pacienteDAO.listarTodos();
    }

    /**
     * RN-08: Determina si el paciente es menor de edad a partir de su fecha de nacimiento.
     * Vive en la capa de lógica porque es una regla de negocio, no una responsabilidad de la entidad.
     */
    private boolean esMenorDeEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return false;
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() < 18;
    }

    /**
     * RN-09: Valida que todos los datos obligatorios estén presentes.
     * DNI, nombres, apellidos, fecha de nacimiento, sexo, teléfono y dirección.
     */
    private boolean validarDatosObligatorios(Paciente paciente) {
        StringBuilder errores = new StringBuilder();

        if (paciente.getDni() == null || paciente.getDni().trim().isEmpty()) {
            errores.append("- El DNI es obligatorio.\n");
        }
        if (paciente.getNombres() == null || paciente.getNombres().trim().isEmpty()) {
            errores.append("- Los nombres son obligatorios.\n");
        }
        if (paciente.getApellidos() == null || paciente.getApellidos().trim().isEmpty()) {
            errores.append("- Los apellidos son obligatorios.\n");
        }
        if (paciente.getFechaNacimiento() == null) {
            errores.append("- La fecha de nacimiento es obligatoria.\n");
        }
        if (paciente.getSexo() == null || paciente.getSexo().trim().isEmpty()) {
            errores.append("- El sexo es obligatorio.\n");
        }
        if (paciente.getTelefono() == null || paciente.getTelefono().trim().isEmpty()) {
            errores.append("- El teléfono es obligatorio.\n");
        }
        if (paciente.getDireccion() == null || paciente.getDireccion().trim().isEmpty()) {
            errores.append("- La dirección es obligatoria.\n");
        }
        if (paciente.getNumeroHistoriaClinica() == null || paciente.getNumeroHistoriaClinica().trim().isEmpty()) {
            errores.append("- El número de historia clínica es obligatorio.\n");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(null,
                    "Los siguientes campos son obligatorios:\n\n" + errores.toString(),
                    "Campos requeridos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}