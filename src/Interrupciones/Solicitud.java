package Interrupciones;

import ElevadorBuilder.Elevador.ControlElevador;
import Scheduler.Dispatcher;

public interface Solicitud {
    Dispatcher delegarSolicitud(Dispatcher dispatcher);
    ControlElevador cumplirSolicitud(ControlElevador controlElevador);
}