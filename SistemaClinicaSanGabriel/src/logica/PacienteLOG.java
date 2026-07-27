    package logica;

import datos.PacienteDAO;
import entidades.Paciente;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.swing.JOptionPane;

public class PacienteLOG {

    public static boolean registrarPaciente(Paciente paciente) {
        if (!validarDatosObligatorios(paciente)) {
            return false;
        }

        Paciente existente = PacienteDAO.buscarPorDni(paciente.getDni());
        if (existente != null) {
            JOptionPane.showMessageDialog(null,
                    "El DNI " + paciente.getDni() + " ya esta registrado a nombre de:\n"
                    + existente.getNombres() + " " + existente.getApellidos() + ".\n\n"
                    + "Si es un paciente diferente, verifique que el DNI sea correcto.",
                    "DNI ya registrado", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        Paciente existenteHC = PacienteDAO.buscarPorHistoriaClinica(paciente.getNumeroHistoriaClinica());
        if (existenteHC != null) {
            JOptionPane.showMessageDialog(null,
                    "La historia clinica Nro. " + paciente.getNumeroHistoriaClinica() + " ya esta asignada a:\n"
                    + existenteHC.getNombres() + " " + existenteHC.getApellidos() + ".\n\n"
                    + "Cada paciente debe tener un numero de historia clinica unico.",
                    "Historia clinica duplicada", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (esMenorDeEdad(paciente.getFechaNacimiento()) && paciente.getApoderado() == null) {
            JOptionPane.showMessageDialog(null,
                    "El paciente es menor de 18 anios.\n"
                    + "Por ley, los menores de edad deben contar con un apoderado o tutor responsable.\n\n"
                    + "Por favor, complete los datos del apoderado en la seccion correspondiente.",
                    "Apoderado requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean exito = PacienteDAO.insertar(paciente);
        if (exito) {
            JOptionPane.showMessageDialog(null,
                    "El paciente " + paciente.getNombres() + " " + paciente.getApellidos()
                    + " ha sido registrado correctamente.",
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se pudo registrar el paciente. Verifique la conexion a la base de datos e intente nuevamente.",
                    "Error al guardar", JOptionPane.ERROR_MESSAGE);
        }
        return exito;
    }

    public static boolean actualizarPaciente(Paciente paciente) {
        if (!validarDatosObligatorios(paciente)) {
            return false;
        }

        if (esMenorDeEdad(paciente.getFechaNacimiento()) && paciente.getApoderado() == null) {
            JOptionPane.showMessageDialog(null,
                    "El paciente es menor de 18 anios y no tiene apoderado registrado.\n"
                    + "Debe asignar un apoderado o tutor responsable antes de guardar los cambios.",
                    "Apoderado requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean exito = PacienteDAO.actualizar(paciente);
        if (exito) {
            JOptionPane.showMessageDialog(null,
                    "Los datos de " + paciente.getNombres() + " " + paciente.getApellidos()
                    + " se han actualizado correctamente.",
                    "Actualizacion exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se pudieron guardar los cambios. Verifique la conexion e intente nuevamente.",
                    "Error al actualizar", JOptionPane.ERROR_MESSAGE);
        }
        return exito;
    }

    public static Paciente buscarPorDni(String dni) {
        Paciente paciente = PacienteDAO.buscarPorDni(dni);
        if (paciente == null) {
            JOptionPane.showMessageDialog(null,
                    "No se encontro ningun paciente con el DNI: " + dni + ".\n"
                    + "Verifique que el DNI sea correcto e intente nuevamente.",
                    "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
        return paciente;
    }

    public static List<Paciente> buscarPorNombreOApellido(String texto) {
        List<Paciente> resultado = PacienteDAO.buscarPorNombre(texto);
        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No se encontraron pacientes con el nombre o apellido: \"" + texto + "\".\n"
                    + "Intente con un termino de busqueda diferente.",
                    "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
        return resultado;
    }

    public static Paciente buscarPorHistoriaClinica(String numeroHistoriaClinica) {
        Paciente paciente = PacienteDAO.buscarPorHistoriaClinica(numeroHistoriaClinica);
        if (paciente == null) {
            JOptionPane.showMessageDialog(null,
                    "No se encontro ningun paciente con la historia clinica Nro. " + numeroHistoriaClinica + ".\n"
                    + "Verifique el numero e intente nuevamente.",
                    "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
        return paciente;
    }

    public static boolean inactivarPaciente(int idPaciente) {
        boolean exito = PacienteDAO.eliminarLogico(idPaciente);
        if (exito) {
            JOptionPane.showMessageDialog(null,
                    "El paciente ha sido marcado como inactivo.\n"
                    + "Ya no aparecera en las busquedas activas del sistema.",
                    "Paciente inactivado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se pudo inactivar al paciente. Verifique la conexion e intente nuevamente.",
                    "Error al inactivar", JOptionPane.ERROR_MESSAGE);
        }
        return exito;
    }

    public static List<Paciente> listarPacientes() {
        return PacienteDAO.listarTodos();
    }

    private static boolean esMenorDeEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return false;
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() < 18;
    }

    private static boolean validarDatosObligatorios(Paciente paciente) {
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
