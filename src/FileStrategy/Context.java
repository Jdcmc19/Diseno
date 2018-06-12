package FileStrategy;

import FileStrategy.Types.Text;
import ParameterDTO.ParameterBO;

import java.io.IOException;

public class Context {
    private File strategy;

   /* public Context() {
    }*/

    public Context(File strategy){
        this.strategy = strategy;
    }

    public Context() {
        strategy = new Text();
    }

    public File getStrategy() {
        return strategy;
    }

    public void setStrategy(File strategy) {
        this.strategy = strategy;
    }

    public void escribir(String nombre, ParameterBO p){
        strategy.escribir(nombre, p);
    }
    public ParameterBO leer(String nombre) throws IOException {
        return strategy.leer(nombre);
    }


}