package entidades;

public class SignosVitales {
    private int idSignos;
    private int idAtencion;
    private double presionArterialSistolica;
    private double presionArterialDiastolica;
    private double temperatura;
    private double peso;
    private double talla;
    private int frecuenciaCardiaca;
    private int frecuenciaRespiratoria;
    private double imc;

    public SignosVitales() {}

    public SignosVitales(double pas, double pad, double temp, double peso, double talla, int fc, int fr) {
        this.presionArterialSistolica = pas;
        this.presionArterialDiastolica = pad;
        this.temperatura = temp;
        this.peso = peso;
        this.talla = talla;
        this.frecuenciaCardiaca = fc;
        this.frecuenciaRespiratoria = fr;
        calcularIMC();
    }

    public void calcularIMC() {
        if (this.talla > 0) {
            double tallaMetros = this.talla / 100.0;
            this.imc = Math.round((this.peso / (tallaMetros * tallaMetros)) * 100.0) / 100.0;
        }
    }

    public int getIdSignos() { return idSignos; }
    public void setIdSignos(int idSignos) { this.idSignos = idSignos; }
    public int getIdAtencion() { return idAtencion; }
    public void setIdAtencion(int idAtencion) { this.idAtencion = idAtencion; }
    public double getPresionArterialSistolica() { return presionArterialSistolica; }
    public void setPresionArterialSistolica(double pas) { this.presionArterialSistolica = pas; }
    public double getPresionArterialDiastolica() { return presionArterialDiastolica; }
    public void setPresionArterialDiastolica(double pad) { this.presionArterialDiastolica = pad; }
    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; calcularIMC(); }
    public double getTalla() { return talla; }
    public void setTalla(double talla) { this.talla = talla; calcularIMC(); }
    public int getFrecuenciaCardiaca() { return frecuenciaCardiaca; }
    public void setFrecuenciaCardiaca(int fc) { this.frecuenciaCardiaca = fc; }
    public int getFrecuenciaRespiratoria() { return frecuenciaRespiratoria; }
    public void setFrecuenciaRespiratoria(int fr) { this.frecuenciaRespiratoria = fr; }
    public double getImc() { return imc; }
    public void setImc(double imc) { this.imc = imc; }
}