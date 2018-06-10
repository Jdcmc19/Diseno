package Interrupciones;

import ElevadorBuilder.Elevador.Component.SensorPiso;
import ElevadorBuilder.Elevador.ControlElevador;
import Scheduler.Dispatcher;

import java.util.ArrayList;

public class IntSensorPiso implements Solicitud{
    private byte piso;
    private byte elevador;

    public IntSensorPiso(byte piso, byte elevador) {
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
        ArrayList<SensorPiso> sp = controlElevador.getSensorPiso();
        SensorPiso spp = sp.get(piso);
        if(spp.isOpen())
            spp.abrir();
        else
            spp.close();
        sp.set(piso,spp);
        controlElevador.setSensorPiso(sp);
        return controlElevador;
    }
}
