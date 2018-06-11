package Scheduler.ModeStrategy;

import Boton.DireccionLlamada;
import ElevadorBuilder.Elevador.Component.Cabin.DireccionElevador;
import ElevadorBuilder.Elevador.ControlElevador;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class SheduleV1 implements Strategy{
    public SheduleV1() {
    }

    @Override
    public int Calendarizar(DireccionLlamada dr, int partida, ArrayList<ControlElevador> controles) {
        int menor=999;
        int elevador = -1;
        int cant=9999;
        int ele=0;
        ControlElevador ce;
        for(int i=0;i<controles.size();i++) {
            ce = controles.get(i);
            if (ce.getSensorPeso().getCantidadMaxima()>ce.getSensorPeso().getCantidadActual()){
                if (cant > ce.getDestinos().size()) {
                    cant = ce.getDestinos().size();
                    ele = i;
                }
                if (ce.getDestinos().size() == 0 && ce.getCabina().getDireccionActual() == DireccionElevador.NINGUNA) {//LIBRE
                    if (menor > abs(ce.getCabina().getPisoActual() - partida)) {
                        menor = abs(ce.getCabina().getPisoActual() - partida);
                        elevador = i;
                    }
                } else {//OCUPADO
                    if ((ce.getCabina().getDireccionActual() == DireccionElevador.ARRIBA && dr == DireccionLlamada.SUBE) || (ce.getCabina().getDireccionActual() == DireccionElevador.ABAJO && dr == DireccionLlamada.BAJA) ||
                            (ce.getCabina().getDireccionPrevista() == DireccionElevador.ARRIBA && dr == DireccionLlamada.SUBE) || (ce.getCabina().getDireccionPrevista() == DireccionElevador.ABAJO && dr == DireccionLlamada.BAJA)) {
                        if (ce.getDestinos().contains(partida)) {
                            return i;
                        }
                    }

                }
            }
        }
        if(elevador!=-1)
            return elevador;
        return ele;
    }
}
