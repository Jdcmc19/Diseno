package ElevadorBuilder.Elevador.Component.Cabin;

public class BotonDetenerse {
    private Boolean isOn;
    public BotonDetenerse() {
        isOn = false;
    }

    public Boolean getOn() {
        return isOn;
    }

    public void setOn(Boolean on) {
        isOn = on;
    }

    public String enviarSolicitud(){return"btoo deterne";}
}
