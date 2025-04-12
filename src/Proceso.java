public class Proceso {
    private final int pid;
    private float tiempoDeServicio;
    private final Resultados registroAsociado;


    public Proceso(int pid, float tiempoDeServicio) {
        this.pid = pid;
        this.tiempoDeServicio = tiempoDeServicio;
        this.registroAsociado = new Resultados(this.tiempoDeServicio);
    }

    public void cargarTiempoFinal(float tiempo) {
        registroAsociado.setrFinal(tiempo);
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

    public float mostrarTiempoFinal() {
        return this.registroAsociado.getrFinal();
    }

    public float getTsInicial() {
        return this.registroAsociado.getTiempoServicio();
    }
}
