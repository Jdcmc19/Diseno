package ParameterDTO;

import java.util.ArrayList;

public class ParameterBO {

    private ParameterTO parametros = new ParameterTO();

    public ParameterTO getParametros(){
        return parametros;

    }
    public void setParametros(int pisos, int elevadores, int personas, int maxPeso, ArrayList pLlamada, ArrayList pDestino,
                              ArrayList pDetener, ArrayList pEmergencia, ArrayList tTrans, ArrayList tPuerta){
        parametros.setCantidadPisos(pisos);
        parametros.setCantidadElevadores(elevadores);
        parametros.setMaxCantidadPersonas(personas);
        parametros.setMaxPeso(maxPeso);
        parametros.setProbabilidadesLlamada(pLlamada);
        parametros.setProbabilidadesDestino(pDestino);
        parametros.setProbabilidadesDetener(pDetener);
        parametros.setProbabilidadesEmergencia(pEmergencia);
        parametros.setTiempoTransicion(tTrans);
        parametros.setTiempoPuertaAbierta(tPuerta);
    }

}
