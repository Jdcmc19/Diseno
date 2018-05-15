package Boton;

public abstract class BotonSolicitud {
    private int piso;
    private Boolean isOn;

    public BotonSolicitud(int piso) {
        this.piso = piso;
        this.isOn = false;
    }

    private void encender(){}
    private void apagar(){}
    private void bloquear(){}
    abstract void enviarSolicitud();
}
