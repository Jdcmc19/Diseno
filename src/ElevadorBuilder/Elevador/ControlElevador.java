package ElevadorBuilder.Elevador;

import Boton.BotonDestino;
import ElevadorBuilder.Elevador.Component.Cabin.Cabina;
import ElevadorBuilder.Elevador.Component.Cabin.DireccionElevador;
import ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes.Bajar;
import ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes.Detenerse;
import ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes.Subir;
import ElevadorBuilder.Elevador.Component.Cabin.Move.Mover;
import ElevadorBuilder.Elevador.Component.IndicadorPiso;
import ElevadorBuilder.Elevador.Component.SensorPeso;
import ElevadorBuilder.Elevador.Component.SensorPiso;
import Interrupciones.Solicitud;

import java.util.ArrayList;

public class ControlElevador {
    private ArrayList<Solicitud> solicitudes;
    private ArrayList<Byte> destinos;

    private SensorPeso sensorPeso;
    private ArrayList<IndicadorPiso> indicadorPiso;
    private ArrayList<SensorPiso> sensorPiso;
    private Cabina cabina;
    private ArrayList<BotonDestino> botonDestino;

    public ControlElevador(SensorPeso sensorPeso, ArrayList<IndicadorPiso> indicadorPiso,  ArrayList<SensorPiso> sensorPiso, Cabina cabina, ArrayList<BotonDestino> botonDestino) {
        this.sensorPeso = sensorPeso;
        this.indicadorPiso = indicadorPiso;
        this.sensorPiso = sensorPiso;
        this.cabina = cabina;
        this.botonDestino = botonDestino;
        solicitudes = new ArrayList<>();
        destinos = new ArrayList<>();
    }

    public ControlElevador(ArrayList<SensorPiso> sensorPiso, Cabina cabina, ArrayList<BotonDestino> botonDestino,ArrayList<Byte> destinos) {
        this.sensorPiso = sensorPiso;
        this.cabina = cabina;
        this.botonDestino = botonDestino;
        this.destinos=destinos;
    }

    @Override
    public String toString() {
        return "Solicitudes: "+solicitudes.size()+"\nDestinos: "+destinos.size()+"\nSensorPeso: "+sensorPeso.toString()+"\nIndicadorPiso: "+indicadorPiso.size()+
                "\nSensorPiso: "+sensorPiso.size()+"\nCabina: "+cabina.toString()+"\nBotonDestino: "+botonDestino.size();
    }
    /******************************************************************************************************************/
    public void crearHiloMoverse(){//hilo
        Thread thread = new Thread(){
            public void run(){
                while(true){
                    if(destinos.size()>0){
                        Mover a;
                        if(cabina.getPisoActual()>destinos.get(0)){
                            System.out.println("Un elevador baja del piso: "+cabina.getPisoActual());
                            a = new Bajar();
                        }
                        else if(cabina.getPisoActual()<destinos.get(0)){
                            System.out.println("Un elevador sube del piso: "+cabina.getPisoActual());
                            a = new Subir();
                        }
                        else{
                            System.out.println("Un elevador se mantiene en el piso: "+cabina.getPisoActual());
                            a = new Detenerse();
                        }
                        moverse(a);
                    }
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
    public void crearHiloSolicitudes(){
        Thread thread = new Thread(){
            public void run(){
                ControlElevador ce;
                while(true){
                    if(solicitudes.size()>0){
                        System.out.println("Resolvio solicitud del elevador");
                        ce = solicitudes.get(0).cumplirSolicitud(new ControlElevador(sensorPiso,cabina,botonDestino,destinos));
                        botonDestino = ce.getBotonesDestino();
                        cabina = ce.getCabina();
                        sensorPiso = ce.getSensorPiso();
                        solicitudes.remove(0);
                    }
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
    public void crearHilo(){
        crearHiloMoverse();
        crearHiloSolicitudes();
    }
    public void moverse(Mover m){

        cabina.setMove(m);
        cabina.setDireccionActual(m.getDireccion());
        if(cabina.getDireccionActual()==DireccionElevador.NINGUNA){
            if(destinos.size()>1) {
                if (cabina.getPisoActual() > destinos.get(1)) {
                    cabina.setDireccionPrevista(DireccionElevador.ABAJO);
                }
                else
                    cabina.setDireccionPrevista(DireccionElevador.ARRIBA);
            }
            else
                cabina.setDireccionPrevista(DireccionElevador.NINGUNA);
        }
        cabina.moverse();
        if(destinos.contains(cabina.getPisoActual()))
            destinos.remove(cabina.getPisoActual());
    }
    /******************************************************************************************************************/
    public ArrayList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public ArrayList<Byte> getDestinos() {
        return destinos;
    }

    public void setDestinos(ArrayList<Byte> destinos) {
        this.destinos = destinos;
    }

    public SensorPeso getSensorPeso() {
        return sensorPeso;
    }

    public void setSensorPeso(SensorPeso sensorPeso) {
        this.sensorPeso = sensorPeso;
    }

    public ArrayList<IndicadorPiso> getIndicadorPiso() {
        return indicadorPiso;
    }

    public void setIndicadorPiso(ArrayList<IndicadorPiso> indicadorPiso) {
        this.indicadorPiso = indicadorPiso;
    }

    public void setIndicadorPiso(int cantPisos){
        indicadorPiso = new ArrayList<>();
        IndicadorPiso ap;
        for(int i=0;i<cantPisos;i++){
            ap = new IndicadorPiso(i+1);
            indicadorPiso.add(ap);
        }
        ap=indicadorPiso.get(cabina.getPisoActual()-1);
        ap.encender();
        indicadorPiso.set(cabina.getPisoActual()-1,ap);
    }

    public ArrayList<SensorPiso> getSensorPiso() {
        return sensorPiso;
    }

    public void setSensorPiso(ArrayList<SensorPiso> sensorPiso) {
        this.sensorPiso = sensorPiso;
    }

    public Cabina getCabina() {
        return cabina;
    }

    public void setCabina(Cabina cabina) {
        this.cabina = cabina;
    }

    public ArrayList<BotonDestino> getBotonesDestino() {
        return botonDestino;
    }

    public void setBotonesDestino(ArrayList<BotonDestino> botonDestino) {
        this.botonDestino = botonDestino;
    }
}
