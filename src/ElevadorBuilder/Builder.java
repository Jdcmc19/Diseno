package ElevadorBuilder;

import Boton.BotonDestino;
import ElevadorBuilder.Elevador.Component.Cabin.Cabina;
import ElevadorBuilder.Elevador.Component.IndicadorPiso;
import ElevadorBuilder.Elevador.Component.SensorPeso;
import ElevadorBuilder.Elevador.Component.SensorPiso;
import ElevadorBuilder.Elevador.ControlElevador;

import java.util.ArrayList;

public interface Builder {
    Cabina contruirCabina();
    ArrayList<SensorPiso> contruirSensorPiso();
    SensorPeso contruirSensorPeso();
    ArrayList<BotonDestino> contruirBotonDestino();
    ArrayList<IndicadorPiso> contruirIndicadorPiso();
    ControlElevador getElevador();
}
