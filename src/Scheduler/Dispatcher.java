package Scheduler;

import Boton.BotonLlamada;
import Boton.DireccionLlamada;
import ElevadorBuilder.Builder;
import ElevadorBuilder.Director;
import ElevadorBuilder.Elevador.ControlElevador;
import ParameterDTO.ParameterTO;
import Scheduler.ModeStrategy.Strategy;

import java.util.ArrayList;
//import java.util.spi.AbstractResourceBundleProvider;

public class Dispatcher {
    private ParameterTO parameterTO;
    private Strategy calendarizador;
    private ArrayList<ArrayList<BotonLlamada>> botonesLlamadas;
    private ArrayList<ControlElevador> controlesElevador;

    public Dispatcher(Strategy calendarizador, ArrayList<ArrayList<BotonLlamada>> botonesLlamadas) {
        this.calendarizador = calendarizador;
        this.botonesLlamadas = botonesLlamadas;
    }

    public Dispatcher(Strategy calendarizador) {
        this.calendarizador = calendarizador;
        BotonLlamada b1 = new BotonLlamada(1,DireccionLlamada.SUBE);
        ArrayList<BotonLlamada> tmp = new ArrayList<>();
        tmp.add(b1);
        botonesLlamadas.add(tmp);
        for(int i=0;i<parameterTO.getCantidadPisos()-2;i++){
            tmp = new ArrayList<>();
            b1 = new BotonLlamada(i+2,DireccionLlamada.SUBE);
            tmp.add(b1);
            b1 = new BotonLlamada(i+2,DireccionLlamada.BAJA);
            tmp.add(b1);
            botonesLlamadas.add(tmp);
        }
        tmp = new ArrayList<>();
        b1 = new BotonLlamada(parameterTO.getCantidadPisos(),DireccionLlamada.BAJA);
        tmp.add(b1);
        botonesLlamadas.add(tmp);

    }
    public void createElevadores(Builder builder){
        Director director = new Director(builder);
        ControlElevador controlElevador;
        controlesElevador = new ArrayList<>();
        for (int i=0;i<parameterTO.getCantidadPisos();i++){
            controlElevador = director.contruir();
            controlesElevador.add(controlElevador);
        }
    }

    public ParameterTO getParameterTO() {
        return parameterTO;
    }

    public void setParameterTO(ParameterTO parameterTO) {
        this.parameterTO = parameterTO;
    }

    public Strategy getCalendarizador() {
        return calendarizador;
    }

    public void setCalendarizador(Strategy calendarizador) {
        this.calendarizador = calendarizador;
    }

    public ArrayList<ArrayList<BotonLlamada>> getBotonesLlamadas() {
        return botonesLlamadas;
    }

    public void setBotonesLlamadas(ArrayList<ArrayList<BotonLlamada>> botonesLlamadas) {
        this.botonesLlamadas = botonesLlamadas;
    }

    public ArrayList<ControlElevador> getControlesElevador() {
        return controlesElevador;
    }

    public void setControlesElevador(ArrayList<ControlElevador> controlesElevador) {
        this.controlesElevador = controlesElevador;
    }
}
