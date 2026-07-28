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
public class ResultadoExamen {
    private int idResultado;
    private int idExamen; // Para saber a quien le peertence el resultqdo
    private String detalleResultado; 
    private String observaciones;
    private Timestamp fechaRegistro;
    public ResultadoExamen() {
    }
    public ResultadoExamen(int idResultado, int idExamen, String detalleResultado, String observaciones, Timestamp fechaRegistro) {
        this.idResultado = idResultado;
        this.idExamen = idExamen;
        this.detalleResultado = detalleResultado;
        this.observaciones = observaciones;
        this.fechaRegistro = fechaRegistro;
    }
    // Gety Set:
    public int getIdResultado() {
        return idResultado;
    }
    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }
    public int getIdExamen() {
        return idExamen;
    }
    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }
    public String getDetalleResultado() {
        return detalleResultado;
    }
    public void setDetalleResultado(String detalleResultado) {
        this.detalleResultado = detalleResultado;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
