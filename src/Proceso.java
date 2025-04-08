public class Proceso {
    private final int pid;
    private float tiempoDeServicio;

    public Proceso(int pid, float tiempoDeServicio) {
        this.pid = pid;
        this.tiempoDeServicio = tiempoDeServicio;
    }

    public int getPid() {
        return pid;
    }

    public float getTiempoDeServicio() {
        return tiempoDeServicio;
    }

    public void setTiempoDeServicio(float tiempoDeServicio) {
        this.tiempoDeServicio = tiempoDeServicio;
    }
}
