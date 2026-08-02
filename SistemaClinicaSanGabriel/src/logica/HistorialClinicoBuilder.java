package logica;

import entidades.*;

public class HistorialClinicoBuilder {

    private AtencionMedica atencion;

    // Cambiado de int idCita a String codigoCita
    public HistorialClinicoBuilder(String codigoCita) {
        this.atencion = new AtencionMedica();
        this.atencion.setCodigoCita(codigoCita);
    }

    public HistorialClinicoBuilder conAnamnesis(String motivo, String antecedentes) {
        this.atencion.setMotivoConsulta(motivo);
        this.atencion.setAntecedentes(antecedentes);
        return this;
    }

    public HistorialClinicoBuilder conSignosVitales(SignosVitales sv) {
        this.atencion.setSignosVitales(sv);
        return this;
    }

    public HistorialClinicoBuilder conTratamientoYObservaciones(String tratamiento, String observaciones) {
        this.atencion.setTratamiento(tratamiento);
        this.atencion.setObservaciones(observaciones);
        return this;
    }

    public HistorialClinicoBuilder agregarDiagnostico(Diagnostico diag) {
        this.atencion.getListaDiagnosticos().add(diag);
        return this;
    }

    public HistorialClinicoBuilder conRecetaMedica(RecetaMedica receta) {
        this.atencion.setReceta(receta);
        return this;
    }

    public HistorialClinicoBuilder agregarMedicamento(int idMedicamento, String nombreMedicamento, int cantidad, String indicacion) {
        RecetaMedica receta = this.atencion.getReceta();
        if (receta == null) {
            receta = new RecetaMedica();
            this.atencion.setReceta(receta);
        }
        receta.agregarMedicamento(idMedicamento, nombreMedicamento, cantidad, indicacion);
        return this;
    }

    public AtencionMedica build() {
        return this.atencion;
    }
}