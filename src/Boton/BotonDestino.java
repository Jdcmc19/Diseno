package Boton;

public class BotonDestino extends BotonSolicitud{
    public BotonDestino(int piso) {
        super(piso);
    }

    @Override
    public String enviarSolicitud() {
        return "";
    }
}
