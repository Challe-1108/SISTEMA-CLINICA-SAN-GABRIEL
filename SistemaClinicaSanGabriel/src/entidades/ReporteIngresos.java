package entidades;

import java.time.LocalDate;


public class ReporteIngresos {
    private LocalDate fecha;
    private int cantidadPagos;
    private double totalIngresos;

    public ReporteIngresos() {
    }

    public ReporteIngresos(LocalDate fecha, int cantidadPagos, double totalIngresos) {
        this.fecha = fecha;
        this.cantidadPagos = cantidadPagos;
        this.totalIngresos = totalIngresos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidadPagos() {
        return cantidadPagos;
    }

    public void setCantidadPagos(int cantidadPagos) {
        this.cantidadPagos = cantidadPagos;
    }

    public double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    @Override
    public String toString() {
        return "ReporteIngresos{" +
                "fecha=" + fecha +
                ", cantidadPagos=" + cantidadPagos +
                ", totalIngresos=" + totalIngresos +
                '}';
    }
}
