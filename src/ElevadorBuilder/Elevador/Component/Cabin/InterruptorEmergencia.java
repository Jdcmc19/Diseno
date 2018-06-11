package ElevadorBuilder.Elevador.Component.Cabin;

public class InterruptorEmergencia {
    private Boolean isOn;
    public InterruptorEmergencia() {
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
