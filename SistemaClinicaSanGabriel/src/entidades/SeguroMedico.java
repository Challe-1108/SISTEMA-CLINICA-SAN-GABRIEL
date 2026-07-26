/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author sthef
 */
public class SeguroMedico {
     private int idSeguro;
    private String compania;
    private String numeroPoliza;
    private String tipoCobertura;
    private boolean estado; // true = activo, false = inactivo

    public SeguroMedico() {
    }

    public SeguroMedico(int idSeguro, String compania, String numeroPoliza, String tipoCobertura, boolean estado) {
        this.idSeguro = idSeguro;
        this.compania = compania;
        this.numeroPoliza = numeroPoliza;
        this.tipoCobertura = tipoCobertura;
        this.estado = estado;
    }

    public int getIdSeguro() { return idSeguro; }
    public void setIdSeguro(int idSeguro) { this.idSeguro = idSeguro; }

    public String getCompania() { return compania; }
    public void setCompania(String compania) { this.compania = compania; }

    public String getNumeroPoliza() { return numeroPoliza; }
    public void setNumeroPoliza(String numeroPoliza) { this.numeroPoliza = numeroPoliza; }

    public String getTipoCobertura() { return tipoCobertura; }
    public void setTipoCobertura(String tipoCobertura) { this.tipoCobertura = tipoCobertura; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}
