package ElevadorBuilder.Elevador.Component;

public class SensorPeso{
    private int cantidadMaxima;
    private int cantidadActual;

    public SensorPeso(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
        this.cantidadActual = 0;
    }

    @Override
    public String toString() {
        return "\n\tCantidadMax: "+cantidadMaxima+"\n\tCantidadActual: "+cantidadActual;
    }

    private void enviarInterrupcion(){}

    public int getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
}
