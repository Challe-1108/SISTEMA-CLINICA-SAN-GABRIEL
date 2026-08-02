/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Usuario
 */
public class HorarioMedico {
    private String codigo;
    private Medico medico;
    private String diaSemana;
    private String horaInicio;
    private String horaFin;

    public HorarioMedico() {
        this.codigo = "NN";
        this.medico = new Medico();
        this.diaSemana = "NN";
        this.horaInicio = "NN";
        this.horaFin = "NN";
    }

    public HorarioMedico(String codigo, Medico medico, String diaSemana,
            String horaInicio, String horaFin) {
        this.codigo = codigo;
        this.medico = medico;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "\n\tCodigo: " + getCodigo() +
               "\n\tMedico: " + getMedico().getNombres() +
               "\n\tDia: " + getDiaSemana() +
               "\n\tHora Inicio: " + getHoraInicio() +
               "\n\tHora Fin: " + getHoraFin();
    }
}

