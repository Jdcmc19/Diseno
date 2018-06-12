package ElevadorBuilder.Elevador;

import Boton.BotonDestino;
import ElevadorBuilder.Elevador.Component.Cabin.Cabina;
import ElevadorBuilder.Elevador.Component.Cabin.DireccionElevador;
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

    @Override
    public String toString() {
        return "Solicitudes: "+solicitudes.size()+"\nDestinos: "+destinos.size()+"\nSensorPeso: "+sensorPeso.toString()+"\nIndicadorPiso: "+indicadorPiso.size()+
                "\nSensorPiso: "+sensorPiso.size()+"\nCabina: "+cabina.toString()+"\nBotonDestino: "+botonDestino.size();
    }
    /******************************************************************************************************************/
    public void cumplirSolicitudes(){
        ControlElevador ce;
        for(int i=0;i<solicitudes.size();i++){
            ce = solicitudes.get(i).cumplirSolicitud(this);
            this.botonDestino = ce.getBotonesDestino();
            this.cabina = ce.getCabina();
            this.sensorPiso = ce.getSensorPiso();
        }
    }
    public void crearHilo(){//hilo
        while(true)
            if(solicitudes.size()>0)
                cumplirSolicitudes();
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
