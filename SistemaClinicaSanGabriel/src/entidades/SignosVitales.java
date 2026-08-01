package entidades;

public class SignosVitales {
    
    private int idSignosVitales;
    private float temperatura;
    private int pulso;
    private int presionSistolica;
    private int presionDiastolica;
    private int respiracion;

    public SignosVitales(int idSignosVitales, float temperatura, int pulso, int presionSistolica, int presionDiastolica, int respiracion) {
        this.idSignosVitales = idSignosVitales;
        this.temperatura = temperatura;
        this.pulso = pulso;
        this.presionSistolica = presionSistolica;
        this.presionDiastolica = presionDiastolica;
        this.respiracion = respiracion;
    }
    
    public SignosVitales(){
        idSignosVitales = 000;
        temperatura = 36.5f;
        pulso = 60;
        presionSistolica = 80;
        presionDiastolica = 50;
        respiracion = 16;
    }
    
    public int getIdSignosVitales(){
        return idSignosVitales;
    }
    
    public void setIdSignosVitales(){
        this.idSignosVitales = idSignosVitales;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public int getPulso() {
        return pulso;
    }

    public void setPulso(int pulso) {
        this.pulso = pulso;
    }

    public int getPresionSistolica() {
        return presionSistolica;
    }

    public void setPresionSistolica(int presionSistolica) {
        this.presionSistolica = presionSistolica;
    }
    
    public int getPresionDiastolica() {
        return presionDiastolica;
    }

    public void setPresionDiastolica(int presionDiastolica) {
        this.presionDiastolica = presionDiastolica;
    }

    public int getRespiracion() {
        return respiracion;
    }

    public void setRespiracion(int respiracion) {
        this.respiracion = respiracion;
    }
    
    @Override
    public String toString(){
        return "Signos vitales{" +
                "signos vitales: " + getIdSignosVitales() + 
                "temperatura: " + getTemperatura() +
                ", pulso: '" + getPulso() + '\'' +
                ", presion Sistolica: '" + getPresionSistolica() + '\'' +
                ", presion Diastolica: '" + getPresionDiastolica() + '\'' +
                ", respiracion: " + getRespiracion() +
                '}';
    }
}
