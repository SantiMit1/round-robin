import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {
    private final Queue<Proceso> colaProcesos;
    private final LinkedList<Proceso> listaOriginalProcesos;
    private final Reloj reloj;
    private final int quantum;
    private final float tiempoDeIntercambio;
    private Proceso procesoAnterior;

    public RoundRobin(int quantum) {
        this.colaProcesos = new LinkedList<>();
        this.listaOriginalProcesos = new LinkedList<>();
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
        listaOriginalProcesos.add(nuevoProceso); // Guardamos para mostrar al final
    }

    public void ejecutar() {
        while (!colaProcesos.isEmpty()) {
            Proceso proceso = colaProcesos.poll();

            if (proceso.getTiempoDeServicio() > 0) {
                if (!proceso.equals(procesoAnterior) && procesoAnterior != null) {
                    System.out.println("Scheduler quita el proceso anterior " + procesoAnterior.getPid() +
                            " y lo pone en cola de espera. (" + tiempoDeIntercambio / 2 + " unidades de tiempo)");
                    reloj.avanzar(tiempoDeIntercambio / 2);
                    colaProcesos.add(procesoAnterior);
                }

                if (proceso.getTiempoDeServicio() > quantum) {
                    if (!proceso.equals(procesoAnterior)) {
                        System.out.println("El scheduler inserta el proceso: " + proceso.getPid());
                        reloj.avanzar(tiempoDeIntercambio / 2);
                        System.out.println("El reloj avanza a: " + reloj.getTiempo());
                    }

                    System.out.println("Proceso " + proceso.getPid() + " en ejecución...");

                    try {
                        Thread.sleep(1000); // Solo para simular visualmente, podés bajarlo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    proceso.setTiempoDeServicio(proceso.getTiempoDeServicio() - quantum);
                    reloj.avanzar(quantum);

                    System.out.println("El tiempo del reloj es: " + reloj.getTiempo());

                    colaProcesos.add(proceso); // Lo volvemos a poner en cola

                } else {
                    if (!proceso.equals(procesoAnterior)) {
                        System.out.println("El scheduler inserta el proceso: " + proceso.getPid());
                        reloj.avanzar(tiempoDeIntercambio / 2);
                        System.out.println("El reloj avanza a: " + reloj.getTiempo());
                    }

                    reloj.avanzar(proceso.getTiempoDeServicio());
                    proceso.setTiempoDeServicio(0);

                    System.out.println("Proceso terminado, pid: " + proceso.getPid() +
                            ", tiempo de retorno: " + reloj.getTiempo());

                    proceso.cargarTiempoFinal(reloj.getTiempo());
                }

                procesoAnterior = proceso;
            }
        }

        System.out.println("\n--- Resultados Finales ---");
        System.out.printf("%-5s %-10s %-12s %-12s%n", "PID", "TS", "T. Retorno", "T. Espera");

        float sumaTiemposEspera = 0;

        for (Proceso p : listaOriginalProcesos) {
            float tRetorno = p.mostrarTiempoFinal();
            float ts = p.getTsInicial();
            float tEspera = tRetorno - ts;
            sumaTiemposEspera += tEspera;

            System.out.printf("%-5d %-10.2f %-12.2f %-12.2f%n", p.getPid(), ts, tRetorno, tEspera);
        }

        float promedioEspera = sumaTiemposEspera / listaOriginalProcesos.size();
        System.out.printf("Tiempo de espera promedio: %.2f%n", promedioEspera);
    }
}
