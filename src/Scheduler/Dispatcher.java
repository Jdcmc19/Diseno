package Scheduler;

import Boton.BotonLlamada;
import Scheduler.ModeStrategy.Strategy;

import java.util.ArrayList;

public class Dispatcher {
    private Strategy calendarizador;
    private ArrayList<BotonLlamada> botonesLlamadas;

    public Dispatcher(Strategy calendarizador, ArrayList<BotonLlamada> botonesLlamadas) {
        this.calendarizador = calendarizador;
        this.botonesLlamadas = botonesLlamadas;
    }

    public Strategy getCalendarizador() {
        return calendarizador;
    }

    public void setCalendarizador(Strategy calendarizador) {
        this.calendarizador = calendarizador;
    }

    public ArrayList<BotonLlamada> getBotonesLlamadas() {
        return botonesLlamadas;
    }

    public void setBotonesLlamadas(ArrayList<BotonLlamada> botonesLlamadas) {
        this.botonesLlamadas = botonesLlamadas;
    }
}
