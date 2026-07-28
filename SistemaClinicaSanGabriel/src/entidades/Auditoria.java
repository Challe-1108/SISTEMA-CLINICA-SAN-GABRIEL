/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author yordi
 */
public class Auditoria {
    private int idAuditoria;
    private int idUsuario;
    private LocalDate fecha;
    private LocalTime hora;
    private String modulo;
    private String operacion;

    public Auditoria(){}
    public Auditoria(int idAuditoria, int idUsuario, LocalDate fecha, LocalTime hora, String modulo, String operacion) {
        this.idAuditoria = idAuditoria;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.modulo = modulo;
        this.operacion = operacion;
    }

    public Auditoria(int idUsuario, LocalDate fecha, LocalTime hora, String modulo, String operacion) {
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.modulo = modulo;
        this.operacion = operacion;
    }

    public int getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(int idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getFechaFormateada() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.fecha.format(dateFormatter);
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getHoraFormateada() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return this.hora.format(timeFormatter);
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    @Override
    public String toString() {
        return "Auditoria{" +
                "idAuditoria=" + idAuditoria +
                ", idUsuario=" + idUsuario +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", modulo='" + modulo + '\'' +
                ", operacion='" + operacion + '\'' +
                '}';
    }
}
