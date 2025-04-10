import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {
    private final Queue<Proceso> colaProcesos;
    private final Reloj reloj;
    private final int quantum;
    private int quantumCounter = 0;
    private final float tiempoDeIntercambio;
    private Proceso procesoAnterior;
    private LinkedList<Resultados> listaR;

    public RoundRobin(int quantum) {
        this.colaProcesos = new LinkedList<>();
        this.reloj = new Reloj();
        this.quantum = quantum;
        this.tiempoDeIntercambio = quantum / 4.0f;
        this.procesoAnterior = null;
        listaR=new LinkedList<>();
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
                    System.out.println("Scheduler quita el proceso anterior " + procesoAnterior.getPid() + " y lo pone en cola de espera. (" + tiempoDeIntercambio / 2 + " unidades de tiempo)");
                    reloj.avanzar(tiempoDeIntercambio / 2);
                    colaProcesos.add(procesoAnterior);
                }

                if (proceso.getTiempoDeServicio()>quantum)
                {
                    if (!proceso.equals(procesoAnterior))
                    {//si no es el mismo tiene que ponerlo
                        System.out.println("el scheduler inserta el proceso: "+ proceso.getPid());
                        reloj.avanzar(tiempoDeIntercambio/2);
                        System.out.println("el reloj avanza a: "+ reloj.getTiempo());
                    }
                    //se ejecuta el proceso
                    System.out.println("proceso" + proceso.getPid() + " en ejecucion.....");

                    //-------------------------------------------------------------
                    try {//espera antes de continuar,mejora legibilidad,solo semantico.
                        Thread.sleep(2000); // Espera 2000 milisegundos = 2 segundos
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //------------------------------------------------------------

                    proceso.setTiempoDeServicio(proceso.getTiempoDeServicio()-quantum);//resto el ts
                    reloj.avanzar(quantum);// actualizo reloj

                    System.out.println(" el tiempo del reloj es de: "+ reloj.getTiempo());


                } else {

                    if (!proceso.equals(procesoAnterior))
                    {//si no es el mismo tiene que ponerlo
                        System.out.println("el scheduler inserta el proceso: "+ proceso.getPid());
                        reloj.avanzar(tiempoDeIntercambio/2);
                        System.out.println("el reloj avanza a: "+ reloj.getTiempo());
                    }
                    // ejecuta el proceso por el tiempo restante
                    reloj.avanzar(proceso.getTiempoDeServicio());//avanzo tiempo restante en el reloj
                    proceso.setTiempoDeServicio(0);//seteo a 0 el tiempo de servicio requerido

                    System.out.println("proceso terminado, pid: "+proceso.getPid()+ " ,tiempo de retorno: "+ reloj.getTiempo());

                }


                procesoAnterior = proceso;
                quantumCounter++;
            }
            //aca es cuando el ts es 0, no usar dado que termino el proceso
        }

        System.out.println("Terminada la ejecucion de todos los procesos en " + reloj.getTiempo() + " unidades de tiempo (" + quantumCounter + " quantums).");
        System.out.println("Tiempo de espera promedio: ");
    }
}
