package entidades;

import java.time.LocalDate;
import java.time.Month;

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
}
