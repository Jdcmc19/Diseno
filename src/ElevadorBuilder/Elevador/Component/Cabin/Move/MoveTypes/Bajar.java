package ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes;

import ElevadorBuilder.Elevador.Component.Cabin.DireccionElevador;
import ElevadorBuilder.Elevador.Component.Cabin.Move.Mover;
import ParameterDTO.ParameterTO;

public class Bajar implements Mover {
    public Bajar() {
    }

    @Override
    public int Moverse(int piso, ParameterTO parameterTO) {
        if(piso==1)
            return 1;
        else
            return piso-1;
    }

    @Override
    public DireccionElevador getDireccion() {
        return DireccionElevador.ABAJO;
    }
}
