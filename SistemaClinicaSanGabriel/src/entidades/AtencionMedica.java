package entidades;

public class AtencionMedica {

    private int idAtencion;
    private String motivo;
    private String antecedentes;
    private int idSignosVitales;
    private int idDiagnostico;
    private String tratamiento;
    private String observaciones;

    private AtencionMedica(Builder builder) {
        this.idAtencion = builder.idAtencion;
        this.motivo = builder.motivo;
        this.antecedentes = builder.antecedentes;
        this.idSignosVitales = builder.idSignosVitales;
        this.idDiagnostico = builder.idDiagnostico;
        this.tratamiento = builder.tratamiento;
        this.observaciones = builder.observaciones;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
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

    public int getIdSignosVitales() {
        return idSignosVitales;
    }

    public void setIdSignosVitales(int idSignosVitales) {
        this.idSignosVitales = idSignosVitales;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
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

        private int idAtencion;
        private String motivo;
        private String antecedentes;
        private int idSignosVitales;
        private int idDiagnostico;
        private String tratamiento;
        private String observaciones;

        public Builder idAtencion(int idAtencion){
            this.idAtencion = idAtencion;
            return this;
        }

        
        
        public Builder motivo(String motivo) {
            this.motivo = motivo;
            return this;
        }

        public Builder antecedentes(String antecedentes) {
            this.antecedentes = antecedentes;
            return this;
        }

        public Builder signos(int idSignosVitales) {
            this.idSignosVitales = idSignosVitales;
            return this;
        }

        public Builder diagnostico(int idDiagnostico) {
            this.idDiagnostico = idDiagnostico;
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