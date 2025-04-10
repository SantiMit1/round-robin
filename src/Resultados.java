public class Resultados {
    private final Proceso procesoRegistrado;
    private final float rFinal;

    public Resultados(Proceso p, float r)
    {
        this.procesoRegistrado=p;
        this.rFinal=r;
    }

    public Proceso getProcesoRegistrado() {
        return procesoRegistrado;
    }

    public float getrFinal() {
        return rFinal;
    }
}
