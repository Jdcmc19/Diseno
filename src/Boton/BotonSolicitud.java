package Boton;

public abstract class BotonSolicitud {
    private int piso;
    private Boolean isOn;

    private void encender(){}
    private void apagar(){}
    private void bloquear(){}
    abstract void enviarSolicitud();
}
