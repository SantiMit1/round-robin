public class Proceso {
    private final int pid;
    private float tiempoDeServicio;
    private Resultados registro_asociado;


    public Proceso(int pid, float tiempoDeServicio) {
        this.pid = pid;
        this.tiempoDeServicio = tiempoDeServicio;
        this.registro_asociado=new Resultados(this.tiempoDeServicio,this.pid);
    }

    public void cargarTiempoFinal(float tiempo)
    {
        registro_asociado.setrFinal(tiempo);
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

    public float mostrarTiempoFinal()
    {
        return this.registro_asociado.getrFinal(); }

    public float mostrarDatos()
    {
        System.out.printf("PID: %d | TS: %.2f | Final: %.2f | Espera: %.2f\n",pid,registro_asociado.getTiempoServicio(), registro_asociado.getrFinal(), registro_asociado.getTiempoEspera());
        return this.registro_asociado.getTiempoEspera();

    }
    public float getTsInicial()
    {
      return  this.registro_asociado.getTiempoServicio();
    }
}
