/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Usuario
 */
public class Cita {
    public static final int DURACION_ESTANDAR_MINUTOS = 30; // RN-18

    private String codigo;
    private Medico medico;
    private String numeroHistoriaClinica;
    private String fecha;
    private String hora;
    private String estado;
    private String observaciones;

    private Cita(CitaBuilder builder) {
        this.codigo = builder.codigo;
        this.medico = builder.medico;
        this.numeroHistoriaClinica = builder.numeroHistoriaClinica;
        this.fecha = builder.fecha;
        this.hora = builder.hora;
        this.estado = builder.estado;
        this.observaciones = builder.observaciones;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getNumeroHistoriaClinica() {
        return numeroHistoriaClinica;
    }

    public void setNumeroHistoriaClinica(String numeroHistoriaClinica) {
        this.numeroHistoriaClinica = numeroHistoriaClinica;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getDuracionMinutos() {
        return DURACION_ESTANDAR_MINUTOS;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "\n\tCodigo: " + getCodigo() +
               "\n\tMedico: " + getMedico().getNombres() +
               "\n\tHistoria Clinica: " + getNumeroHistoriaClinica() +
               "\n\tFecha: " + getFecha() +
               "\n\tHora: " + getHora() +
               "\n\tDuracion (min): " + getDuracionMinutos() +
               "\n\tEstado: " + getEstado() +
               "\n\tObservaciones: " + getObservaciones();
    }

    // ---------- Builder ----------
    public static class CitaBuilder {
        private String codigo = "NN";
        private Medico medico = new Medico();
        private String numeroHistoriaClinica = "NN";
        private String fecha = "NN";
        private String hora = "NN";
        private String estado = "Programada"; // RN-20
        private String observaciones = "NN";

        public CitaBuilder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public CitaBuilder setMedico(Medico medico) {
            this.medico = medico;
            return this;
        }

        public CitaBuilder setNumeroHistoriaClinica(String numeroHistoriaClinica) {
            this.numeroHistoriaClinica = numeroHistoriaClinica;
            return this;
        }

        public CitaBuilder setFecha(String fecha) {
            this.fecha = fecha;
            return this;
        }

        public CitaBuilder setHora(String hora) {
            this.hora = hora;
            return this;
        }

        public CitaBuilder setEstado(String estado) {
            this.estado = estado;
            return this;
        }

        public CitaBuilder setObservaciones(String observaciones) {
            this.observaciones = observaciones;
            return this;
        }

        public Cita build() {
            return new Cita(this);
        }
    }
}
