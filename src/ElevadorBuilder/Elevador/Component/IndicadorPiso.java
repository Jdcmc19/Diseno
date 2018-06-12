package ElevadorBuilder.Elevador.Component;

public class IndicadorPiso {
    private int piso;
    private Boolean isOn;

    public IndicadorPiso(int piso) {
        this.piso = piso;
        this.isOn = false;
    }

    public void encender(){
        isOn=true;
    }
    public void apagar(){
        isOn=false;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

}
