package ElevadorBuilder.Elevador.Component;

public class IndicadorPiso {
    private int piso;
    private Boolean isOn;

    public IndicadorPiso(int piso) {
        this.piso = piso;
        this.isOn = false;
    }

    private void encender(){}
    private void apagar(){}

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

}
