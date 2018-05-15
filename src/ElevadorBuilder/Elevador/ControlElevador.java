package ElevadorBuilder.Elevador;

import Boton.BotonDestino;
import ElevadorBuilder.Elevador.Component.Cabin.Cabina;
import ElevadorBuilder.Elevador.Component.IndicadorPiso;
import ElevadorBuilder.Elevador.Component.SensorPeso;
import ElevadorBuilder.Elevador.Component.SensorPiso;

import java.util.ArrayList;

public class ControlElevador {
    private ArrayList solicitudes;
    private ArrayList<Integer> destinos;

    private SensorPeso sensorPeso;
    private IndicadorPiso indicadorPiso;
    private SensorPiso sensorPiso;
    private Cabina cabina;
    private BotonDestino botonDestino;

    public ControlElevador(ArrayList solicitudes, ArrayList<Integer> destinos, SensorPeso sensorPeso, IndicadorPiso indicadorPiso, SensorPiso sensorPiso, Cabina cabina, BotonDestino botonDestino) {
        this.solicitudes = solicitudes;
        this.destinos = destinos;
        this.sensorPeso = sensorPeso;
        this.indicadorPiso = indicadorPiso;
        this.sensorPiso = sensorPiso;
        this.cabina = cabina;
        this.botonDestino = botonDestino;
    }

    public ArrayList getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(ArrayList solicitudes) {
        this.solicitudes = solicitudes;
    }

    public ArrayList<Integer> getDestinos() {
        return destinos;
    }

    public void setDestinos(ArrayList<Integer> destinos) {
        this.destinos = destinos;
    }

    public SensorPeso getSensorPeso() {
        return sensorPeso;
    }

    public void setSensorPeso(SensorPeso sensorPeso) {
        this.sensorPeso = sensorPeso;
    }

    public IndicadorPiso getIndicadorPiso() {
        return indicadorPiso;
    }

    public void setIndicadorPiso(IndicadorPiso indicadorPiso) {
        this.indicadorPiso = indicadorPiso;
    }

    public SensorPiso getSensorPiso() {
        return sensorPiso;
    }

    public void setSensorPiso(SensorPiso sensorPiso) {
        this.sensorPiso = sensorPiso;
    }

    public Cabina getCabina() {
        return cabina;
    }

    public void setCabina(Cabina cabina) {
        this.cabina = cabina;
    }

    public BotonDestino getBotonDestino() {
        return botonDestino;
    }

    public void setBotonDestino(BotonDestino botonDestino) {
        this.botonDestino = botonDestino;
    }
}
