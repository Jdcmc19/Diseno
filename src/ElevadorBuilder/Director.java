package ElevadorBuilder;

import ElevadorBuilder.Elevador.ControlElevador;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    private ControlElevador contruir(){
        return builder.getElevador();
    }
}
