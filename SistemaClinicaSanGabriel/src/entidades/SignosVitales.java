package entidades;

public class SignosVitales {
    private int idSignos;
    private int idAtencion;
    private double pas; // Presión Arterial Sistólica
    private double pad; // Presión Arterial Diastólica
    private double temperatura;
    private double peso; // en kg
    private double talla; // en cm
    private int fc; // Frecuencia Cardíaca (bpm)
    private int fr; // Frecuencia Respiratoria (rpm)
    private double imc; // Índice de Masa Corporal

    // Constructor vacío
    public SignosVitales() {
    }

    // Constructor completo
    public SignosVitales(double pas, double pad, double temperatura, double peso, double talla, int fc, int fr) {
        this.pas = pas;
        this.pad = pad;
        this.temperatura = temperatura;
        this.peso = peso;
        this.talla = talla;
        this.fc = fc;
        this.fr = fr;
        this.imc = calcularIMC();
    }

    // Método de negocio para calcular el IMC automáticamente
    public double calcularIMC() {
        if (this.talla > 0) {
            double tallaEnMetros = this.talla / 100.0;
            double resultado = this.peso / (tallaEnMetros * tallaEnMetros);
            return Math.round(resultado * 100.0) / 100.0; // Redondea a 2 decimales
        }
        return 0.0;
    }

    // --- GETTERS Y SETTERS ---

    public int getIdSignos() {
        return idSignos;
    }

    public void setIdSignos(int idSignos) {
        this.idSignos = idSignos;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public double getPas() {
        return pas;
    }

    public void setPas(double pas) {
        this.pas = pas;
    }

    public double getPad() {
        return pad;
    }

    public void setPad(double pad) {
        this.pad = pad;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
        this.imc = calcularIMC(); // Recalcula IMC cuando cambia el peso
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
        this.imc = calcularIMC(); // Recalcula IMC cuando cambia la talla
    }

    public int getFc() {
        return fc;
    }

    public void setFc(int fc) {
        this.fc = fc;
    }

    public int getFr() {
        return fr;
    }

    public void setFr(int fr) {
        this.fr = fr;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getCategoriaIMC() {
        double valor = this.imc;
        if (valor < 18.5) return "Bajo peso";
        if (valor < 25) return "Normal";
        if (valor < 30) return "Sobrepeso";
        return "Obesidad";
    }

    @Override
    public String toString() {
        return "SignosVitales{PAS=" + pas + ", PAD=" + pad
                + ", Temp=" + temperatura + ", Peso=" + peso
                + ", Talla=" + talla + ", FC=" + fc + ", FR=" + fr
                + ", IMC=" + imc + '}';
    }
}