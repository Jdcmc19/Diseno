package ElevadorBuilder;

import Boton.BotonDestino;
import ElevadorBuilder.Elevador.Component.Cabin.Cabina;
import ElevadorBuilder.Elevador.Component.IndicadorPiso;
import ElevadorBuilder.Elevador.Component.SensorPeso;
import ElevadorBuilder.Elevador.Component.SensorPiso;
import ElevadorBuilder.Elevador.ControlElevador;
import ParameterDTO.ParameterTO;

import java.util.ArrayList;

public class ConcreteBuilder implements Builder{
    @Override
    public ControlElevador getElevador() {
        return new ControlElevador(contruirSensorPeso(),contruirIndicadorPiso(),contruirSensorPiso(),contruirCabina(),contruirBotonDestino());
    }

    public ConcreteBuilder() {
    }

    @Override
    public Cabina contruirCabina() {
        return new Cabina();
    }

    @Override
    public ArrayList<SensorPiso> contruirSensorPiso() {
        ArrayList<SensorPiso> sensorPisos = new ArrayList<>();
        SensorPiso se;
        for(int i=0;i<ParameterTO.getCantidadPisos();i++){
            se = new SensorPiso(i+1);
            sensorPisos.add(se);
        }
        return sensorPisos;
    }

    @Override
    public SensorPeso contruirSensorPeso() {
        return new SensorPeso(ParameterTO.getMaxPeso());
    }

    @Override
    public ArrayList<BotonDestino> contruirBotonDestino() {
        ArrayList<BotonDestino> botonDestino = new ArrayList<>();
        BotonDestino bto;
        for(int i=0;i<ParameterTO.getCantidadPisos();i++){
            bto = new BotonDestino(i+1);
            botonDestino.add(bto);
        }
        return botonDestino;
    }

    @Override
    public ArrayList<IndicadorPiso> contruirIndicadorPiso() {
        ArrayList<IndicadorPiso> indicadorPisos = new ArrayList<>();
        IndicadorPiso ip;
        for(int i=0;i<ParameterTO.getCantidadPisos();i++){
            ip = new IndicadorPiso(i+1);
            indicadorPisos.add(ip);
        }
        return indicadorPisos;
    }
}
