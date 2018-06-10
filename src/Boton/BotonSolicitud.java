package Boton;

public abstract class BotonSolicitud {
    private int piso;
    private Boolean isOn;

    public BotonSolicitud(int piso) {
        this.piso = piso;
        this.isOn = false;
    }

    public void encender(){
        isOn=true;
    }
    public void apagar(){
        isOn=false;
    }
    public void bloquear(){}
    public abstract String enviarSolicitud();
}
