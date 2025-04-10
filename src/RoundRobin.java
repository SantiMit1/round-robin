import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {
    private final Queue<Proceso> colaProcesos;
    private final Reloj reloj;
    private final int quantum;
    private int quantumCounter = 0;
    private final float tiempoDeIntercambio;
    private Proceso procesoAnterior;

    public RoundRobin(int quantum) {
        this.colaProcesos = new LinkedList<>();
        this.reloj = new Reloj();
        this.quantum = quantum;
        this.tiempoDeIntercambio = quantum / 4.0f;
        this.procesoAnterior = null;
    }

    public void agregarProceso(float tiempoDeServicio) {
        if (tiempoDeServicio <= 0) {
            throw new IllegalArgumentException("El tiempo de servicio debe ser mayor que cero.");
        }

        Proceso nuevoProceso = new Proceso(colaProcesos.size() + 1, tiempoDeServicio);
        colaProcesos.add(nuevoProceso);
    }

    public void ejecutar() {
        while (!colaProcesos.isEmpty()) {
            Proceso proceso = colaProcesos.poll();

            if (proceso.getTiempoDeServicio() > 0) {
                if (!proceso.equals(procesoAnterior) && procesoAnterior != null) {
                    System.out.println("Scheduler quita el proceso " + procesoAnterior.getPid() + " y lo pone en espera. (" + tiempoDeIntercambio / 2 + " unidades de tiempo)");
                    reloj.avanzar(tiempoDeIntercambio / 2);
                    colaProcesos.add(procesoAnterior);
                }

                if (!proceso.equals(procesoAnterior)) {
                    System.out.println("Scheduler pone en ejecucion el proceso " + proceso.getPid() + " (" + tiempoDeIntercambio / 2 + " unidades de tiempo)");
                }

                if (proceso.getTiempoDeServicio() > quantum) {
                    System.out.println("Scheduler ejecuta el proceso " + proceso.getPid() + " durante " + quantum + " unidades de tiempo.");
                    reloj.avanzar(quantum);
                    proceso.setTiempoDeServicio(proceso.getTiempoDeServicio() - quantum);
                    colaProcesos.add(proceso);
                } else {
                    System.out.println("Scheduler ejecuta el proceso " + proceso.getPid() + " durante " + proceso.getTiempoDeServicio() + " unidades de tiempo.");
                    reloj.avanzar(proceso.getTiempoDeServicio());
                    proceso.setTiempoDeServicio(0);
                }

                quantumCounter++;
                procesoAnterior = proceso;
            }

        }

        System.out.println("Terminada la ejecucion de todos los procesos en " + reloj.getTiempo() + " unidades de tiempo (" + quantumCounter + " quantums).");
        System.out.println("Tiempo de espera promedio: ");
    }
}
