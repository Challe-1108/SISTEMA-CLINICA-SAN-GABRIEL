/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import entidades.*;
import datos.MedicoDAO;
import javax.swing.JOptionPane;
/**
 *
 * @author Usuario
 */

public class MedicoService {

    public boolean registrarMedico(Medico medico) {
        // Validar que los campos obligatorios no esten vacios
        if (medico.getCodigo().isEmpty() || medico.getColegiatura().isEmpty()
                || medico.getDni().isEmpty() || medico.getNombres().isEmpty()
                || medico.getApellidos().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Todos los campos obligatorios deben estar completos.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // RN-12: especialidad obligatoria
        if (medico.getEspecialidades() == null || medico.getEspecialidades().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El medico debe tener al menos una especialidad.",
                    "Especialidad obligatoria", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // RN-13: colegiatura unica
        if (MedicoDAO.existeColegiatura(medico.getColegiatura())) {
            JOptionPane.showMessageDialog(null,
                    "Ya existe un medico registrado con la colegiatura " + medico.getColegiatura() + ".",
                    "Colegiatura duplicada", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return MedicoDAO.registrarMedico(medico);
    }

    public boolean actualizarMedico(Medico medico) {
        if (medico.getCodigo() == null || medico.getCodigo().equals("NN")) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar un medico valido para actualizar.",
                    "Medico invalido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return MedicoDAO.actualizarMedico(medico);
    }

    public boolean eliminarMedico(String codigo) {
        return MedicoDAO.eliminarMedico(codigo);
    }

    public java.util.ArrayList<Medico> listarMedicos() {
        return MedicoDAO.listarMedicos();
    }

    public java.util.ArrayList<Especialidad> listarEspecialidades() {
        return MedicoDAO.listarEspecialidades();
    }
}