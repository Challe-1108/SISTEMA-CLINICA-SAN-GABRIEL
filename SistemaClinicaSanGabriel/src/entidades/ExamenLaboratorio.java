/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;
import java.sql.Timestamp;
/**
 *
 * @author LENOVO
 */
public class ExamenLaboratorio {
    private int idExamen;
    private int idAtencion;    
    private String tipoExamen;  
    private String estado;       //opciones:"Pendiente", "En proceso", "Finalizado", "Entregado"
    private Timestamp fechaSolicitud;
    public ExamenLaboratorio() {
    }
    public ExamenLaboratorio(int idExamen, int idAtencion, String tipoExamen, String estado, Timestamp fechaSolicitud) {
        this.idExamen = idExamen;
        this.idAtencion = idAtencion;
        this.tipoExamen = tipoExamen;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
    }
    // Get y Set:
    public int getIdExamen() {
        return idExamen;
    }
    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }
    public int getIdAtencion() {
        return idAtencion;
    }
    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }
    public String getTipoExamen() {
        return tipoExamen;
    }
    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Timestamp getFechaSolicitud() {
        return fechaSolicitud;
    }
    public void setFechaSolicitud(Timestamp fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
}
