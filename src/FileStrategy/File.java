package FileStrategy;

import ParameterDTO.ParameterBO;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface File {
    void escribir(String nombre, ParameterBO p);
    ParameterBO leer(String nombre) throws IOException;
}
