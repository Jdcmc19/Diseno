package Scheduler.ModeStrategy;

import Boton.DireccionLlamada;
import ElevadorBuilder.Elevador.ControlElevador;

import java.util.ArrayList;

public interface Strategy {
    int Calendarizar(DireccionLlamada dr, int partida, ArrayList<ControlElevador> controles);
}
