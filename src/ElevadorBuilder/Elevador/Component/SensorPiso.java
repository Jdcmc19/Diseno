package ElevadorBuilder.Elevador.Component;

public class SensorPiso {
    private Boolean isClosed;
    private int piso;

    public SensorPiso(int piso) {
        this.isClosed = true;
        this.piso = piso;
    }

    @Override
    public String toString() {
        return "\n\tisClosed: "+isClosed+"\n\tPiso: "+piso;
    }

    public String enviarSolicitud(){return"btoo deterne";}
    public void close(){
        isClosed = true;
    }
    public void abrir(){
        isClosed = false;
    }

    public Boolean isOpen() {
        return !isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
}
