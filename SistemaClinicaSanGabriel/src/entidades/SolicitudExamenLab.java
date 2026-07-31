package entidades;

import java.time.LocalDate;

public class SolicitudExamenLab {
    private int idSolicitud;
    private int idPaciente;
    private LocalDate fecha;
    private String descripcion;

    public SolicitudExamenLab(int idSolicitud, int idPaciente, LocalDate fecha, String descripcion) {
        this.idSolicitud = idSolicitud;
        this.idPaciente = idPaciente;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
    
    public SolicitudExamenLab(){
        idSolicitud = 000;
        idPaciente = 000;
        fecha = LocalDate.of(2026,07,01);
        descripcion = "NULL";
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
