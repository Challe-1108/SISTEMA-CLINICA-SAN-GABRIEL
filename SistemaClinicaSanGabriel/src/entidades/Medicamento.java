/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author LENOVO
 */
public class Medicamento {
    private int idMedicamento;
    private String nombre;
    private String descripcion;
    private int stockActual;
    private int stockMinimo;
    private double precioUnitario;
    private boolean estado; // true: Activo; false: Inactivo

    public Medicamento() {
    }

    public Medicamento(int idMedicamento, String nombre, String descripcion, int stockActual, int stockMinimo, double precioUnitario, boolean estado) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.precioUnitario = precioUnitario;
        this.estado = estado;
    }
    // Ahora get y set:
    public int getIdMedicamento() {
        return idMedicamento;
    }
    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getStockActual() {
        return stockActual;
    }
    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }
    public int getStockMinimo() {
        return stockMinimo;
    }
    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
