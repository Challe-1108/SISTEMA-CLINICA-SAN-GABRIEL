package entidades;

import java.time.LocalDate;
import java.time.LocalTime;

public class Diagnostico {
    
    private int idDiagnostico;
    private Paciente paciente;
    private LocalDate fecha;
    private LocalTime hora;
    private String tipo;
    private String descripcion;
    private String observaciones;
    
    public Diagnostico(){
        idDiagnostico = 000;
        fecha = LocalDate.of(2001,01,01);
        hora = LocalTime.of(0,0);
        tipo = "NT";
        descripcion = "ND";
        observaciones = "NO";
    }

    public Diagnostico(int idDiagnostico, Paciente paciente, LocalDate fecha, LocalTime hora, String tipo, String descripcion, String observaciones) {
        this.idDiagnostico = idDiagnostico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
    }
    
    

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString(){
        return "Diagnostico{" +
                "idDiagnostico=" + getIdDiagnostico() +
                ", paciente: '" + getPaciente() + '\'' +
                ", fecha: '" + getFecha() + '\'' +
                ", hora: " + getHora() + '\'' +
                ", tipo: '" + getTipo() + '\'' +
                ", descripcion '" + getDescripcion() + '\'' +
                ", observaciones: '" + getObservaciones() +
                '}';
    }
}
