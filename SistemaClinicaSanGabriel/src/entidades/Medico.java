/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Usuario
 */

public class Medico {
    private String codigo;
    private String colegiatura;
    private String dni;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private List<Especialidad> especialidades;

    public Medico() {
        this.codigo = "NN";
        this.colegiatura = "NN";
        this.dni = "NN";
        this.nombres = "NN";
        this.apellidos = "NN";
        this.telefono = "NN";
        this.correo = "NN";
        this.especialidades = new ArrayList<>();
    }

    public Medico(String codigo, String colegiatura, String dni, String nombres,
            String apellidos, String telefono, String correo, List<Especialidad> especialidades) {
        this.codigo = codigo;
        this.colegiatura = colegiatura;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.especialidades = especialidades;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getColegiatura() {
        return colegiatura;
    }

    public void setColegiatura(String colegiatura) {
        this.colegiatura = colegiatura;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    @Override
    public String toString() {
        return "\n\tCodigo: " + getCodigo() +
               "\n\tColegiatura: " + getColegiatura() +
               "\n\tDNI: " + getDni() +
               "\n\tNombres: " + getNombres() +
               "\n\tApellidos: " + getApellidos() +
               "\n\tTelefono: " + getTelefono() +
               "\n\tCorreo: " + getCorreo() +
               "\n\tEspecialidades: " + getEspecialidades();
    }
}
