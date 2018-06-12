package Interrupciones;

import Boton.DireccionLlamada;
import ElevadorBuilder.Elevador.Component.Cabin.Cabina;
import ElevadorBuilder.Elevador.Component.Cabin.DireccionElevador;
import ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes.Bajar;
import ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes.Detenerse;
import ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes.Subir;
import ElevadorBuilder.Elevador.Component.Cabin.Move.Mover;
import ElevadorBuilder.Elevador.ControlElevador;
import Scheduler.Dispatcher;

import java.util.ArrayList;

public class IntControlesMotor implements Solicitud{

    private byte elevador;
    private byte control;

    public IntControlesMotor(byte elevador,byte control) {
        this.elevador = elevador;
        this.control = control;
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
        Mover mover = null;
        if(control==1){//suba
            mover = new Subir();
        }
        else if(control==2){//baje
            mover = new Bajar();
        }
        else if(control==4){//detenerse
            mover = new Detenerse();
        }
        controlElevador.moverse(mover);
        return controlElevador;
    }
}
