package entidades;

public class AtencionMedica {

    private String motivo;
    private String antecedentes;
    private SignosVitales signos;
    private Diagnostico diagnostico;
    private String tratamiento;
    private String observaciones;

    // Constructor privado: solo el Builder puede crear el objeto
    private AtencionMedica(Builder builder) {
        this.motivo = builder.motivo;
        this.antecedentes = builder.antecedentes;
        this.signos = builder.signos;
        this.diagnostico = builder.diagnostico;
        this.tratamiento = builder.tratamiento;
        this.observaciones = builder.observaciones;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public SignosVitales getSignos() {
        return signos;
    }

    public void setSignos(SignosVitales signos) {
        this.signos = signos;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public static class Builder {

        private String motivo;
        private String antecedentes;
        private SignosVitales signos;
        private Diagnostico diagnostico;
        private String tratamiento;
        private String observaciones;

        public Builder motivo(String motivo) {
            this.motivo = motivo;
            return this;
        }

        public Builder antecedentes(String antecedentes) {
            this.antecedentes = antecedentes;
            return this;
        }

        public Builder signos(SignosVitales signos) {
            this.signos = signos;
            return this;
        }

        public Builder diagnostico(Diagnostico diagnostico) {
            this.diagnostico = diagnostico;
            return this;
        }

        public Builder tratamiento(String tratamiento) {
            this.tratamiento = tratamiento;
            return this;
        }

        public Builder observaciones(String observaciones) {
            this.observaciones = observaciones;
            return this;
        }

        public AtencionMedica build() {
            return new AtencionMedica(this);
        }
    }
}