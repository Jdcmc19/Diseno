package Scheduler;

import Boton.BotonLlamada;
import Boton.DireccionLlamada;
import ElevadorBuilder.Builder;
import ElevadorBuilder.Director;
import ElevadorBuilder.Elevador.ControlElevador;
import Interrupciones.*;
import ParameterDTO.ParameterTO;
import Scheduler.ModeStrategy.Strategy;

import java.util.ArrayList;
//import java.util.spi.AbstractResourceBundleProvider;

public class Dispatcher {
    private ParameterTO parameterTO;
    private Strategy calendarizador;
    private ArrayList<Solicitud> solicitudes;
    private ArrayList<ArrayList<BotonLlamada>> botonesLlamadas;
    private ArrayList<ControlElevador> controlesElevador;

    public Dispatcher(Strategy calendarizador, ArrayList<ArrayList<BotonLlamada>> botonesLlamadas) {
        this.calendarizador = calendarizador;
        this.botonesLlamadas = botonesLlamadas;
        this.solicitudes = new ArrayList<>();
    }

    public Dispatcher(Strategy calendarizador) {
        this.calendarizador = calendarizador;
        this.solicitudes = new ArrayList<>();
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

    public void delegarSolicitudes(){
        Dispatcher d;
        for(int i=0;i<solicitudes.size();i++){
            d=solicitudes.get(i).delegarSolicitud(this);
            this.botonesLlamadas = d.getBotonesLlamadas();
            this.controlesElevador = d.getControlesElevador();
        }
    }

    /**********************************INTERRUPCIONES***********************************/
    public void botonDestinoInterrupcion(int piso, int elevador){//luz
        IntBotonDestino intBotonDestino =new IntBotonDestino((byte)piso,(byte)elevador);
        solicitudes.add(intBotonDestino);
    }
    public void sensorPisoInterrupcion(int piso, int elevador){
        IntSensorPiso intSensorPiso =new IntSensorPiso((byte)piso,(byte)elevador);
        solicitudes.add(intSensorPiso);
    }
    public void botonLlamadaInterrupcion(int piso, DireccionLlamada direccionLlamada){//luz
        IntBotonLlamada intBotonLlamada = new IntBotonLlamada((byte)piso,direccionLlamada);
        solicitudes.add(intBotonLlamada);
    }

    public void controlesMotorInterrupcion(int elevador,int control){
        IntControlesMotor intControlesMotor = new IntControlesMotor((byte)elevador,(byte)control);
        solicitudes.add(intControlesMotor);
    }

    /***********************************************************************************/

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
