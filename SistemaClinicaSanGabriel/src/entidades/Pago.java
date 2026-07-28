package entidades;

import java.time.LocalDate;

public class Pago {
    private int idPago;
    private int idAtencion;
    private LocalDate fechaPago;
    private double monto;
    private String metodoPago;
    private boolean estado;

    public Pago() {
    }

    public Pago(int idPago, int idAtencion, LocalDate fechaPago, double monto, String metodoPago, boolean estado) {
        this.idPago = idPago;
        this.idAtencion = idAtencion;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estado = estado;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago=" + idPago +
                ", idAtencion=" + idAtencion +
                ", fechaPago=" + fechaPago +
                ", monto=" + monto +
                ", metodoPago='" + metodoPago + '\'' +
                ", estado=" + estado +
                '}';
    }
}
