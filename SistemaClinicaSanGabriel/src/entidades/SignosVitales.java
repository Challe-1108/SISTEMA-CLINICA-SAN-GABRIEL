package entidades;

public class SignosVitales {
    
    private float temperatura;
    private int pulso;
    private int presion;
    private int respiracion;

    public SignosVitales(float temperatura, int pulso, int presion, int respiracion) {
        this.temperatura = temperatura;
        this.pulso = pulso;
        this.presion = presion;
        this.respiracion = respiracion;
    }
    
    public SignosVitales(){
        temperatura = 36.5f;
        pulso = 60;
        presion = 80;
        respiracion = 16;
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

    public int getPresion() {
        return presion;
    }

    public void setPresion(int presion) {
        this.presion = presion;
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
                "temperatura: " + getTemperatura() +
                ", pulso: '" + getPulso() + '\'' +
                ", presion: '" + getPresion() + '\'' +
                ", respiracion: " + getRespiracion() +
                '}';
    }
}
