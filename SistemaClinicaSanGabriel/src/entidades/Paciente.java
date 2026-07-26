/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author sthef
 */
import java.time.LocalDate;
import java.time.Period;

public class Paciente {
    private int idPaciente;
    private String dni;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String sexo;
    private String telefono;
    private String direccion;
    private String numeroHistoriaClinica;
    private SeguroMedico seguroMedico;
    private Apoderado apoderado;
    private boolean estado;

    // Constructor privado: solo el Builder puede construir el objeto
    private Paciente(Builder builder) {
        this.idPaciente = builder.idPaciente;
        this.dni = builder.dni;
        this.nombres = builder.nombres;
        this.apellidos = builder.apellidos;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.sexo = builder.sexo;
        this.telefono = builder.telefono;
        this.direccion = builder.direccion;
        this.numeroHistoriaClinica = builder.numeroHistoriaClinica;
        this.seguroMedico = builder.seguroMedico;
        this.apoderado = builder.apoderado;
        this.estado = builder.estado;
    }

    // ---------- Getters ----------
    public int getIdPaciente() { return idPaciente; }
    public String getDni() { return dni; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public String getSexo() { return sexo; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public String getNumeroHistoriaClinica() { return numeroHistoriaClinica; }
    public SeguroMedico getSeguroMedico() { return seguroMedico; }
    public Apoderado getApoderado() { return apoderado; }
    public boolean isEstado() { return estado; }

    // ---------- Setters necesarios para edición ----------
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }
    public void setEstado(boolean estado) { this.estado = estado; }
    public void setSeguroMedico(SeguroMedico seguroMedico) { this.seguroMedico = seguroMedico; }
    public void setApoderado(Apoderado apoderado) { this.apoderado = apoderado; }

    

    // ---------- Patrón Builder ----------
    public static class Builder {
        private int idPaciente;
        private String dni;
        private String nombres;
        private String apellidos;
        private LocalDate fechaNacimiento;
        private String sexo;
        private String telefono;
        private String direccion;
        private String numeroHistoriaClinica;
        private SeguroMedico seguroMedico;
        private Apoderado apoderado;
        private boolean estado = true; // por defecto, activo

        public Builder idPaciente(int idPaciente) {
            this.idPaciente = idPaciente;
            return this;
        }

        public Builder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public Builder nombres(String nombres) {
            this.nombres = nombres;
            return this;
        }

        public Builder apellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public Builder fechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public Builder sexo(String sexo) {
            this.sexo = sexo;
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder numeroHistoriaClinica(String numeroHistoriaClinica) {
            this.numeroHistoriaClinica = numeroHistoriaClinica;
            return this;
        }

        public Builder seguroMedico(SeguroMedico seguroMedico) {
            this.seguroMedico = seguroMedico;
            return this;
        }

        public Builder apoderado(Apoderado apoderado) {
            this.apoderado = apoderado;
            return this;
        }

        public Builder estado(boolean estado) {
            this.estado = estado;
            return this;
        }
        public Paciente build() {
            return new Paciente(this);
        }

        
    }
}