package ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes;

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
}
