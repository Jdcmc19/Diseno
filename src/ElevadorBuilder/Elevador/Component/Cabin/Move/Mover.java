package ElevadorBuilder.Elevador.Component.Cabin.Move;


import ElevadorBuilder.Elevador.Component.Cabin.DireccionElevador;
import ParameterDTO.ParameterTO;

public interface Mover {

    int Moverse(int piso, ParameterTO parameterTO);
    DireccionElevador getDireccion();
}
