import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la duracion de un quantum: ");
        int quantum = sc.nextInt();
        if (quantum <= 0) {
            System.out.println("El quantum debe ser mayor que cero.");
            return;
        }

        RoundRobin rr = new RoundRobin(quantum);
        System.out.println("Ingrese el tiempo de servicio de los procesos (0 para terminar): ");
        while (true) {
            float tiempoDeServicio = sc.nextFloat();
            if (tiempoDeServicio <= 0) {
                break;
            }
            rr.agregarProceso(tiempoDeServicio);
        }

        rr.ejecutar();
    }
}
