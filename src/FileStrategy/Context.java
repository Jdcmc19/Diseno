package FileStrategy;

import ParameterDTO.ParameterBO;

import java.io.IOException;

public class Context {
    private File strategy;

    public Context(File strategy){
        this.strategy = strategy;
    }

    public void escribir(String nombre, ParameterBO p){
        strategy.escribir(nombre, p);
    }
    public ParameterBO leer(String nombre) throws IOException {
        return strategy.leer(nombre);
    }
}