package entidades;

import java.time.LocalDate;
import java.time.LocalTime;

public class Diagnostico {
    
    private int idDiagnostico;
    private int idPaciente;
    private LocalDate fecha;
    private String tipo;
    private String descripcion;
    private String observaciones;
    
    public Diagnostico(){
        idDiagnostico = 000;
        idPaciente = 000;
        fecha = LocalDate.of(2001,01,01);
        tipo = "NT";
        descripcion = "ND";
        observaciones = "NO";
    }

    public Diagnostico(int idDiagnostico, int idPaciente, LocalDate fecha, String tipo, String descripcion, String observaciones) {
        this.idDiagnostico = idDiagnostico;
        this.idPaciente = idPaciente;
        this.fecha = fecha;
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

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
                ", paciente: '" + getIdPaciente() + '\'' +
                ", fecha: '" + getFecha() + '\'' +
                ", tipo: '" + getTipo() + '\'' +
                ", descripcion '" + getDescripcion() + '\'' +
                ", observaciones: '" + getObservaciones() +
                '}';
    }
}
