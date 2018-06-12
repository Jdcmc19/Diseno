package ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes;

import ElevadorBuilder.Elevador.Component.Cabin.DireccionElevador;
import ElevadorBuilder.Elevador.Component.Cabin.Move.Mover;
import ParameterDTO.ParameterTO;

public class Subir implements Mover {
    public Subir() {
    }

    @Override
    public int Moverse(int piso, ParameterTO parameterTO) {
        if(piso==parameterTO.getCantidadPisos())
            return piso;
        else
            return piso+1;
    }
    @Override
    public DireccionElevador getDireccion() {
        return DireccionElevador.ARRIBA;
    }
}
