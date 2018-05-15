package ParameterDTO;

import java.util.ArrayList;

public class ParameterTO {
    private static int cantidadPisos;
    private int cantidadElevadores;
    private int maxCantidadPersonas;
    private static int maxPeso;
    private ArrayList probabilidadesLlamada;
    private ArrayList probabilidadesDestino;
    private ArrayList probabilidadesDetener;
    private ArrayList probabilidadesEmergencia;
    private ArrayList tiempoTransicion;
    private ArrayList tiempoPuertaAbierta;

    public static int getCantidadPisos() {
        return cantidadPisos;
    }

    public static void setCantidadPisos(int cantidadPisos) {
        ParameterTO.cantidadPisos = cantidadPisos;
    }

    public static int getMaxPeso() {
        return maxPeso;
    }

    public static void setMaxPeso(int maxPeso) {
        ParameterTO.maxPeso = maxPeso;
    }
}
