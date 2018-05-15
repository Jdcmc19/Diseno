package ElevadorBuilder.Elevador.Component;

public class SensorPiso {
    private Boolean isClosed;

    public SensorPiso() {
        this.isClosed = true;
    }

    private void enviarInterrupcion(){}
    private void close(){}

    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }
}
