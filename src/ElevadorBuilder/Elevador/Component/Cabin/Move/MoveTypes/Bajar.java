package ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes;

import ElevadorBuilder.Elevador.Component.Cabin.Move.Mover;

public class Bajar implements Mover {
    public Bajar() {
    }

    @Override
    public int Moverse(int piso) {
        if(piso==1)
            return 1;
        else
            return piso-1;
    }
}
