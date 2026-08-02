package logica;

import entidades.*;
import datos.*;
import javax.swing.JOptionPane;

public class AtencionMedicaLOG {
    
    public static boolean registrarAtencion(AtencionMedica atencion, SignosVitales signos, Diagnostico diagnostico, RecetaMedica receta){
        if (!validarDatos(atencion, signos, diagnostico, receta)){
            return false;
        }
        AtencionMedica am = AtencionMedicaDAO.buscarAtencionPorId(atencion.getIdAtencion());
        if (am != null) {
            JOptionPane.showMessageDialog(null,
                    "Atencion registrada a ID de paciente: " + diagnostico.getIdPaciente()+ " \n" ,
                    "Atencion Medica ya registrada", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        boolean sig1 = SignosVitalesDAO.registrarSignos(signos);
        boolean diag1 = DiagnosticoDAO.registrarDiagnostico(diagnostico);
        boolean at1 = AtencionMedicaDAO.registrarAtencion(atencion);
        if (at1 && sig1 && diag1) {
            JOptionPane.showMessageDialog(null,
                    "ID de paciente: " + diagnostico.getIdPaciente() + "\n" +
                    "ID del Diagnostico: " + diagnostico.getIdDiagnostico() + "\n"+
                    "ID de Signos Vitales: " + signos.getIdSignosVitales() + "\n",
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se pudo registrarla atencion medica",
                    "Error al guardar", JOptionPane.ERROR_MESSAGE);
        }
        return at1 && sig1 && diag1;
    }

    public static boolean validarDatos(AtencionMedica atencion, SignosVitales signos, Diagnostico diagnostico, RecetaMedica receta) {

        StringBuilder errores = new StringBuilder();

        if (atencion == null || signos == null || diagnostico == null || receta==null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Los datos de la atención médica no pueden estar vacíos.",
                    "Datos requeridos",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        if (atencion.getIdAtencion() <= 0) {
            errores.append("Debe colocar un número de atención válido.\n");
        }
        if (atencion.getMotivo() == null || atencion.getMotivo().trim().isEmpty()) {
            errores.append("Completar el campo: Motivo.\n");
        }
        if (atencion.getAntecedentes() == null || atencion.getAntecedentes().trim().isEmpty()) {
            errores.append("Completar el campo: Antecedentes\n");
        }
        //--Signos Vitales
        if (signos.getIdSignosVitales() <=0){
            errores.append("Colocar ID de Signos Vitales valido\n");
        }
        if (signos.getTemperatura()<20 || signos.getTemperatura()>46.5){
            errores.append("Colocar una temperatura Valida\n");
        }
        if (signos.getPulso()<20 || signos.getPulso()>250){
            errores.append("Colocar un pulso valido\n");
        }
        if (signos.getPresionSistolica()<40 || signos.getPresionSistolica() > 370){
            errores.append("Colocar presion Sistolica valida\n");
        }
        if (signos.getPresionDiastolica()<20 || signos.getPresionDiastolica()>360){
            errores.append("Colocar presion Diastolica Valida\n");
        }
        if (signos.getRespiracion()<=1 || signos.getRespiracion()>100){
            errores.append("Colocar respiraciones/minuto valido\n");
        }
        //----------
        //--Receta
        if (receta.getIdReceta() <=0){
            errores.append("Colocar ID de Receta valido");
        }
        if(receta.getFechaemision()==null){
            errores.append("Completar el campo: Fecha de emision");
        }
        if (receta.getIdPaciente() <= 0){
            errores.append("Colocar ID de Paciente valido");
        }
        if (receta.getDescripcion() == null || receta.getDescripcion().trim().isEmpty()){
            errores.append("Completar el campo: Descripcion");
        }
        if (receta.getIdMedicamento() <=0){
            errores.append("Colocar ID de Medicamento Valido");
        }
        if (receta.getCantidad() <=0.0){
            errores.append("Colocar Cantidad Valida");
        }
        //----------
        
        //--Diagnostico
        if (diagnostico.getIdDiagnostico() <= 0) {
            errores.append("Colocar ID de Diagnostico valido.\n");
        }
        if (diagnostico.getIdPaciente() <= 0){
            errores.append("Colocar ID de Paciente valido\n");
        }
        if (diagnostico.getFecha()== null){
            errores.append("Completar el campo: Fecha\n");
        }
        if (diagnostico.getTipo() == null || diagnostico.getTipo().trim().isEmpty()){
            errores.append("Completar el campo: Tipo\n");
        }
        if (diagnostico.getDescripcion() == null || diagnostico.getDescripcion().trim().isEmpty()){
            errores.append("Completar el campo: Descripcion\n");
        }
        if (diagnostico.getObservaciones() == null || diagnostico.getObservaciones().trim().isEmpty()){
            errores.append("Completar el campo: Obsservaciones\n");
        }
        //----------
        
        if (atencion.getTratamiento() == null || atencion.getTratamiento().trim().isEmpty()) {
            errores.append("Completar el campo: Tratamiento.\n");
        }
        if (atencion.getObservaciones() == null || atencion.getObservaciones().trim().isEmpty()) {
            errores.append("Completar el campo: Observaciones\n");
        }
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Los siguientes campos son obligatorios:\n\n" + errores.toString(),
                    "Campos requeridos",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }
}
