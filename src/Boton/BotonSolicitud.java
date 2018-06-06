package Boton;

public abstract class BotonSolicitud {
    private int piso;
    private Boolean isOn;

    public BotonSolicitud(int piso) {
        this.piso = piso;
        this.isOn = false;
    }

    private void encender(){
        isOn=true;
    }
    private void apagar(){
        isOn=false;
    }
    private void bloquear(){}
    abstract void enviarSolicitud();
}
