package Interrupciones;

import Boton.BotonLlamada;
import Boton.DireccionLlamada;
import ElevadorBuilder.Elevador.ControlElevador;
import Scheduler.Dispatcher;

import java.util.ArrayList;

public class IntBotonLlamada implements Solicitud {
    private byte piso;
    private DireccionLlamada direccionLlamada;

    public IntBotonLlamada(byte piso, DireccionLlamada direccionLlamada) {
        this.piso = piso;
        this.direccionLlamada = direccionLlamada;
    }

    @Override
    public Dispatcher delegarSolicitud(Dispatcher d) {
        ArrayList<ArrayList<BotonLlamada>> array = d.getBotonesLlamadas();
        ArrayList<BotonLlamada> aboton = array.get(piso);
        BotonLlamada ba;
        for(int i=0;i<aboton.size();i++){
            if(aboton.get(i).getDireccion()==direccionLlamada){
                ba = aboton.get(i);
                ba.encender();
                aboton.set(i,ba);
            }
        }
        array.set(piso,aboton);
        d.setBotonesLlamadas(array);
        return d;
    }
    @Override
    public ControlElevador cumplirSolicitud(ControlElevador controlElevador) {
        return null;
    }
}
