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
    public Boolean prendido(){
        if(isOn)
            return true;
        return false;
    }
    public abstract String enviarSolicitud();
}
