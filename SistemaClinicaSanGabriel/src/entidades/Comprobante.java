package entidades;

import java.time.LocalDate;

public class Comprobante {
    
    private int idComprobante;
    private String numeroComprobante;
    private LocalDate fechaEmision;
    private String tipoComprobante;
    private double total;
    private int idPago;

    public Comprobante() {
    }

    public Comprobante(int idComprobante, String numeroComprobante, LocalDate fechaEmision, String tipoComprobante, double total, int idPago) {
        this.idComprobante = idComprobante;
        this.numeroComprobante = numeroComprobante;
        this.fechaEmision = fechaEmision;
        this.tipoComprobante = tipoComprobante;
        this.total = total;
        this.idPago = idPago;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    @Override
    public String toString() {
        return "Comprobante{" +
                "idComprobante=" + idComprobante +
                ", numeroComprobante='" + numeroComprobante + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", tipoComprobante='" + tipoComprobante + '\'' +
                ", total=" + total +
                ", idPago=" + idPago +
                '}';
    }
    
}
