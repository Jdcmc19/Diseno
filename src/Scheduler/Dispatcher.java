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
import java.util.Random;
//import java.util.spi.AbstractResourceBundleProvider;

public class Dispatcher{
    private ParameterTO parameterTO;
    private Strategy calendarizador;
    private ArrayList<Solicitud> solicitudes;
    private ArrayList<ArrayList<BotonLlamada>> botonesLlamadas;
    private ArrayList<ControlElevador> controlesElevador;
    private Integer[] calendarizado;

    public Dispatcher(ArrayList<ArrayList<BotonLlamada>> botonesLlamadas,ArrayList<ControlElevador> controlesElevador){
        this.botonesLlamadas = botonesLlamadas;
        this.controlesElevador = controlesElevador;
    }

    public Dispatcher(Strategy calendarizador, ArrayList<ArrayList<BotonLlamada>> botonesLlamadas) {
        this.calendarizador = calendarizador;
        this.botonesLlamadas = botonesLlamadas;
        this.solicitudes = new ArrayList<>();
    }

    public Dispatcher(Strategy calendarizador, ParameterTO parameterTO) {
        this.parameterTO = parameterTO;
        this.calendarizador = calendarizador;
        this.solicitudes = new ArrayList<>();
        botonesLlamadas = new ArrayList<>();
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
        calendarizado = new Integer[parameterTO.getCantidadPisos()];
    }
    public int calendarizar(DireccionLlamada dr,int partida){
        int elevador = calendarizador.Calendarizar(dr,partida,this.controlesElevador);
        calendarizado[partida-1] = elevador;
        return elevador;
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

        Thread t = new Thread(){
            public void run() {
                Dispatcher d;
                while(true) {
                    if(solicitudes.size()>0){
                        d=solicitudes.get(0).delegarSolicitud(new Dispatcher(botonesLlamadas, controlesElevador));
                        botonesLlamadas = d.getBotonesLlamadas();
                        controlesElevador = d.getControlesElevador();
                    }
                }
            }
        };
        t.start();
    }

    public void crearHilosElevadores(){
        for(int i=0;i<controlesElevador.size();i++){
            controlesElevador.get(i).crearHilo();//crear funcion
        }
    }

    public void crearHiloDispatcher(){
        delegarSolicitudes();
    }
    public void iniciarSimulacion(){//hilo AQUI EL SLEEP DE UT
        crearHiloDispatcher();
        crearHilosElevadores();
        Boolean bandera = true;
        Thread hilo = new Thread(
                () -> {
            while (true) {
                while (bandera) {
                    for (int i = 0; i < parameterTO.getCantidadElevadores(); i++) {//por cada elevador
                        Random rand = new Random();
                        if (rand.nextInt(100) < parameterTO.getProbabilidadesDetener().get(i)) {//Posibilidad de detenerse
                            controlesMotorInterrupcion(i, 4);
                        }

                        if (rand.nextInt(100) < parameterTO.getProbabilidadesEmergencia().get(i)) {//Posibilidad de emergencia
                            controlesMotorInterrupcion(i, 4);
                        }
                        for (int e = 0; e < parameterTO.getCantidadPisos(); e++) {//Posibilidad de ir a cada piso
                            if (rand.nextInt(100) < parameterTO.getProbabilidadesDestino().get(e)) {
                                botonDestinoInterrupcion(e, i);
                            }
                        }
                    }
                    for (int i = 0; i < parameterTO.getCantidadPisos(); i++) {
                        Random r = new Random();
                        if (r.nextInt(100) < parameterTO.getProbabilidadesLlamada().get(i)) {
                            if (i == 0) {
                                botonLlamadaInterrupcion(i, DireccionLlamada.SUBE);
                            } else if (i == parameterTO.getCantidadPisos() - 1) {
                                botonLlamadaInterrupcion(i, DireccionLlamada.BAJA);
                            } else {
                                if (r.nextBoolean())
                                    botonLlamadaInterrupcion(i, DireccionLlamada.BAJA);
                                else
                                    botonLlamadaInterrupcion(i, DireccionLlamada.SUBE);
                            }
                        }
                    }
                    //TODO SLEEP DE UT
                }
            }
        }
        );

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

    public ArrayList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public Integer[] getCalendarizado() {
        return calendarizado;
    }

    public void setCalendarizado(Integer[] calendarizado) {
        this.calendarizado = calendarizado;
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
