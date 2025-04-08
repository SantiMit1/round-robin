public class Reloj {
    private float tiempo;

    public Reloj() {
        this.tiempo = 0;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void avanzar(float tiempo) {
        this.tiempo += tiempo;
    }
}
