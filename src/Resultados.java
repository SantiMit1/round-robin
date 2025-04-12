public class Resultados {
    private final float tiempoServicio;
    private float rFinal;

    public Resultados(float tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    public void setrFinal(float rFinal) {
        this.rFinal = rFinal;
    }

    public float getrFinal() {
        return rFinal;
    }

    public float getTiempoServicio() {
        return tiempoServicio;
    }
}
