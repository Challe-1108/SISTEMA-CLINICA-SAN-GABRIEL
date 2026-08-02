package logica;


import entidades.AtencionMedica;
import entidades.Diagnostico;
import entidades.RecetaMedica;
import entidades.SignosVitales;

public class HistorialClinicoBuilder {

    private final AtencionMedica atencion;

    // Constructor que exige el ID de la cita obligatoria
    public HistorialClinicoBuilder(int idCita) {
        this.atencion = new AtencionMedica();
        this.atencion.setIdCita(idCita);
    }

    public HistorialClinicoBuilder conAnamnesis(String motivoConsulta, String antecedentes) {
        this.atencion.setMotivoConsulta(motivoConsulta);
        this.atencion.setAntecedentes(antecedentes);
        return this;
    }

    public HistorialClinicoBuilder conSignosVitales(SignosVitales signosVitales) {
        this.atencion.setSignosVitales(signosVitales);
        return this;
    }

    public HistorialClinicoBuilder agregarDiagnostico(Diagnostico diagnostico) {
        this.atencion.agregarDiagnostico(diagnostico);
        return this;
    }

    public HistorialClinicoBuilder conTratamientoYObservaciones(String planTratamiento, String observaciones) {
        this.atencion.setPlanTratamiento(planTratamiento);
        this.atencion.setObservaciones(observaciones);
        return this;
    }

    public HistorialClinicoBuilder conRecetaMedica(RecetaMedica receta) {
        this.atencion.setRecetaMedica(receta);
        return this;
    }

    // Devuelve el objeto AtencionMedica completamente construido
    public AtencionMedica build() {
        return this.atencion;
    }
}