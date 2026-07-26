/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author sthef
 */
public class Apoderado {
      private int idApoderado;
    private String dni;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String parentesco;
    private boolean estado;

    public Apoderado() {
    }

    public Apoderado(int idApoderado, String dni, String nombres, String apellidos,
                      String telefono, String parentesco, boolean estado) {
        this.idApoderado = idApoderado;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.parentesco = parentesco;
        this.estado = estado;
    }

    public int getIdApoderado() { return idApoderado; }
    public void setIdApoderado(int idApoderado) { this.idApoderado = idApoderado; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getParentesco() { return parentesco; }
    public void setParentesco(String parentesco) { this.parentesco = parentesco; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

}
