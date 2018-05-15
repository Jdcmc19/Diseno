package Boton;

public class BotonLlamada extends BotonSolicitud{

    private DireccionLlamada direccion;

    public BotonLlamada(int piso, DireccionLlamada direccion) {
        super(piso);
        this.direccion = direccion;
    }

    @Override
    void enviarSolicitud() {

    }
}
