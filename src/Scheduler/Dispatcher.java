package Scheduler;

import Boton.BotonLlamada;
import Boton.DireccionLlamada;
import ElevadorBuilder.Builder;
import ElevadorBuilder.Director;
import ElevadorBuilder.Elevador.ControlElevador;
import ParameterDTO.ParameterTO;
import Scheduler.ModeStrategy.Strategy;

import java.util.ArrayList;

public class Dispatcher {
    private Strategy calendarizador;
    private ArrayList<BotonLlamada> botonesLlamadas;
    private ArrayList<ControlElevador> controlesElevador;

    public Dispatcher(Strategy calendarizador, ArrayList<BotonLlamada> botonesLlamadas) {
        this.calendarizador = calendarizador;
        this.botonesLlamadas = botonesLlamadas;
    }

    public Dispatcher(Strategy calendarizador) {
        this.calendarizador = calendarizador;
        BotonLlamada bl;
        for(int i=0;i<ParameterTO.getCantidadPisos()-2;i++){
            bl = new BotonLlamada(i+2,DireccionLlamada.SUBE);
            botonesLlamadas.add(bl);
            bl = new BotonLlamada(i+2,DireccionLlamada.BAJA);
            botonesLlamadas.add(bl);
        }

    }
    public void createElevadores(Builder builder){
        Director director = new Director(builder);
        ControlElevador controlElevador;
        controlesElevador = new ArrayList<>();
        for (int i=0;i<ParameterTO.getCantidadPisos();i++){
            controlElevador = director.contruir();
            controlesElevador.add(controlElevador);
        }
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

    public ArrayList<ControlElevador> getControlesElevador() {
        return controlesElevador;
    }

    public void setControlesElevador(ArrayList<ControlElevador> controlesElevador) {
        this.controlesElevador = controlesElevador;
    }
}
