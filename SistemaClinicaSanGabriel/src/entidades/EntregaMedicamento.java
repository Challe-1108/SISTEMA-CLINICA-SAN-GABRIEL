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
public class EntregaMedicamento {
    private int idEntrega;
    private int idAtencion;     
    private int idMedicamento;  
    private int cantidad;   
    private Timestamp fechaEntrega;

    public EntregaMedicamento() {
    }
    public EntregaMedicamento(int idEntrega, int idAtencion, int idMedicamento, int cantidad, Timestamp fechaEntrega) {
        this.idEntrega = idEntrega;
        this.idAtencion = idAtencion;
        this.idMedicamento = idMedicamento;
        this.cantidad = cantidad;
        this.fechaEntrega = fechaEntrega;
    }
    // Get y Set:
    public int getIdEntrega() {
        return idEntrega;
    }
    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }
    public int getIdAtencion() {
        return idAtencion;
    }
    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }
    public int getIdMedicamento() {
        return idMedicamento;
    }
    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public Timestamp getFechaEntrega() {
        return fechaEntrega;
    }
    public void setFechaEntrega(Timestamp fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}
