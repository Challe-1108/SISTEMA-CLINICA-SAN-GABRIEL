package entidades;

public class HistorialClinicoBuilder {
    private AtencionMedica atencion;

    public HistorialClinicoBuilder(int idCita, String codigoCita) {
        this.atencion = new AtencionMedica();
        this.atencion.setIdCita(idCita);
        this.atencion.setCodigoCita(codigoCita);
    }

    public HistorialClinicoBuilder conAnamnesis(String motivoConsulta, String antecedentes) {
        this.atencion.setMotivoConsulta(motivoConsulta);
        this.atencion.setAntecedentes(antecedentes);
        return this;
    }

    public HistorialClinicoBuilder conSignosVitales(SignosVitales sv) {
        this.atencion.setSignosVitales(sv);
        return this;
    }

    public HistorialClinicoBuilder agregarDiagnostico(Diagnostico diagnostico) {
        this.atencion.getListaDiagnosticos().add(diagnostico);
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

    public AtencionMedica build() {
        return this.atencion;
    }
}