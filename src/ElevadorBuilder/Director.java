package ElevadorBuilder;

import ElevadorBuilder.Elevador.ControlElevador;

import java.lang.reflect.InvocationTargetException;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public ControlElevador contruir(){
        return builder.getElevador();
    }
}
