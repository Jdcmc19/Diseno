package Interrupciones;

import Boton.BotonDestino;
import ElevadorBuilder.Elevador.ControlElevador;
import Scheduler.Dispatcher;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class IntBotonDestino implements Solicitud{
    private byte piso;
    private byte elevador;

    public IntBotonDestino(byte piso, byte elevador) {
        this.piso = piso;
        this.elevador = elevador;
    }

    @Override
    public Dispatcher delegarSolicitud(Dispatcher d) {
        ArrayList<ControlElevador> array = d.getControlesElevador();
        ControlElevador control = array.get(elevador);
        ArrayList<Solicitud> asoli = control.getSolicitudes();
        asoli.add(this);
        control.setSolicitudes(asoli);
        array.set(elevador,control);
        d.setControlesElevador(array);
        return d;
    }

    @Override
    public ControlElevador cumplirSolicitud(ControlElevador controlElevador) {
        ArrayList<BotonDestino> aboton = controlElevador.getBotonesDestino();
        BotonDestino bd = aboton.get(piso);
        ArrayList<Byte> dest  =controlElevador.getDestinos();
        if((byte)controlElevador.getCabina().getPisoActual()-1!=piso){
            dest.add(piso);
            bd.encender();
        }
        aboton.set(piso,bd);
        controlElevador.setBotonesDestino(aboton);
        return controlElevador;
    }
}
