package entidades;

public class ReporteAtencion {
  
    private String especialidad;
    private int cantidadPacientes;

    public ReporteAtencion() {
    }

    public ReporteAtencion(String especialidad, int cantidadPacientes) {
        this.especialidad = especialidad;
        this.cantidadPacientes = cantidadPacientes;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getCantidadPacientes() {
        return cantidadPacientes;
    }

    public void setCantidadPacientes(int cantidadPacientes) {
        this.cantidadPacientes = cantidadPacientes;
    }

    @Override
    public String toString() {
        return "ReporteAtencion{" +
                "especialidad='" + especialidad + '\'' +
                ", cantidadPacientes=" + cantidadPacientes +
                '}';
    }
    
}
