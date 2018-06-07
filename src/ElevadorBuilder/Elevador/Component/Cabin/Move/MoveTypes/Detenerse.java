package ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes;

import ElevadorBuilder.Elevador.Component.Cabin.Move.Mover;
import ParameterDTO.ParameterTO;

public class Detenerse implements Mover {
    public Detenerse() {
    }

    @Override
    public int Moverse(int piso, ParameterTO parameterTO) {
        return piso;
    }
}
