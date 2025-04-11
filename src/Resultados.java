public class Resultados {
    private int pid;
    private float tiempoServicio;
    private float rFinal;

    public Resultados(float tiempoServicio, int pid) {
        this.tiempoServicio = tiempoServicio;
        this.pid = pid;
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

    public int getPid() {
        return pid;
    }

    public float getTiempoRetorno() {
        return rFinal; // como todos llegan en tiempo 0, el tiempo de retorno es igual al tiempo final
    }

    public float getTiempoEspera() {
        return rFinal - tiempoServicio; // espera = retorno - servicio
    }
}
