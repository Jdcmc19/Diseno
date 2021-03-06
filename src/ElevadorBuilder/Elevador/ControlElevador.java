package ElevadorBuilder.Elevador;

import Boton.BotonDestino;
import ElevadorBuilder.Elevador.Component.Cabin.BotonDetenerse;
import ElevadorBuilder.Elevador.Component.Cabin.Cabina;
import ElevadorBuilder.Elevador.Component.Cabin.DireccionElevador;
import ElevadorBuilder.Elevador.Component.Cabin.InterruptorEmergencia;
import ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes.Bajar;
import ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes.Detenerse;
import ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes.Subir;
import ElevadorBuilder.Elevador.Component.Cabin.Move.Mover;
import ElevadorBuilder.Elevador.Component.IndicadorPiso;
import ElevadorBuilder.Elevador.Component.SensorPeso;
import ElevadorBuilder.Elevador.Component.SensorPiso;
import Interrupciones.Solicitud;
import Scheduler.Dispatcher;

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
                        if(cabina.getPisoActual()-1>(byte)destinos.get(0)){
                            System.out.println("Un elevador baja del piso: "+cabina.getPisoActual());
                            a = new Bajar();
                        }
                        else if(cabina.getPisoActual()-1<(byte)destinos.get(0)){
                            System.out.println("Un elevador sube del piso: "+cabina.getPisoActual());
                            a = new Subir();
                        }
                        else{
                            System.out.println(destinos.get(0));
                            System.out.println(cabina.getPisoActual());
                            System.out.println("Un elevador se mantiene en el piso: "+cabina.getPisoActual());
                            System.out.println(cabina.getPisoActual()+ "    "+destinos);
                            a = new Detenerse();
                        }
                        moverse(a);
                    }
                    try {
                        Thread.sleep(1000);
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
                        Thread.sleep(1000);
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
                if (cabina.getPisoActual()-1 > destinos.get(1)) {
                    cabina.setDireccionPrevista(DireccionElevador.ABAJO);
                }
                else
                    cabina.setDireccionPrevista(DireccionElevador.ARRIBA);
            }
            else
                cabina.setDireccionPrevista(DireccionElevador.NINGUNA);
        }
        cabina.moverse();
        int cp = cabina.getCantidadPersonas();
        if(cp>0) {
            cabina.setCantidadPersonas(cp - 1);
            System.out.println("Se baja 1 persona en el piso "+ cabina.getPisoActual());
        }
        ArrayList<Byte> as = new ArrayList<>();
        for(int i=0;i<botonDestino.size();i++){
            BotonDestino bb = botonDestino.get(i);
            bb.apagar();
            botonDestino.set(i,bb);
        }
        for(int i=0;i<destinos.size();i++){
            if(destinos.get(i)!=(byte)cabina.getPisoActual()-1){
                as.add(destinos.get(i));
                BotonDestino ap = botonDestino.get(destinos.get(i));
                ap.encender();
                botonDestino.set(destinos.get(i),ap);
            }
        }
        destinos = as;
        if(cabina.getInterruptorEmergencia().getOn()){
            InterruptorEmergencia ie = cabina.getInterruptorEmergencia();
            ie.setOn(false);
            cabina.setInterruptorEmergencia(ie);
        }
        if(cabina.getBotonDetenerse().getOn()){
            BotonDetenerse ie = cabina.getBotonDetenerse();
            ie.setOn(false);
            cabina.setBotonDetenerse(ie);
        }
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
