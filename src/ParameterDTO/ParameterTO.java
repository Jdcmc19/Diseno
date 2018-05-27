package ParameterDTO;

import java.util.ArrayList;

public class ParameterTO {
    private static int cantidadPisos;
    private static int cantidadElevadores;
    private static int maxCantidadPersonas;
    private static int maxPeso;
    private static ArrayList<Integer> probabilidadesLlamada;
    private static ArrayList<Integer>  probabilidadesDestino;
    private static ArrayList<Integer>  probabilidadesDetener;
    private static ArrayList<Integer>  probabilidadesEmergencia;
    private static ArrayList<Integer>  tiempoTransicion;
    private static ArrayList<Integer>  tiempoPuertaAbierta;

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

    public static int getCantidadElevadores() {
        return cantidadElevadores;
    }

    public static void setCantidadElevadores(int cantidadElevadores) {
        ParameterTO.cantidadElevadores = cantidadElevadores;
    }

    public static int getMaxCantidadPersonas() {
        return maxCantidadPersonas;
    }

    public static void setMaxCantidadPersonas(int maxCantidadPersonas) {
        ParameterTO.maxCantidadPersonas = maxCantidadPersonas;
    }

    public static ArrayList<Integer> getProbabilidadesLlamada() {
        return probabilidadesLlamada;
    }

    public static void setProbabilidadesLlamada(ArrayList<Integer> probabilidadesLlamada) {
        ParameterTO.probabilidadesLlamada = probabilidadesLlamada;
    }

    public static ArrayList<Integer> getProbabilidadesDestino() {
        return probabilidadesDestino;
    }

    public static void setProbabilidadesDestino(ArrayList<Integer> probabilidadesDestino) {
        ParameterTO.probabilidadesDestino = probabilidadesDestino;
    }

    public static ArrayList<Integer> getProbabilidadesDetener() {
        return probabilidadesDetener;
    }

    public static void setProbabilidadesDetener(ArrayList<Integer> probabilidadesDetener) {
        ParameterTO.probabilidadesDetener = probabilidadesDetener;
    }

    public static ArrayList<Integer> getProbabilidadesEmergencia() {
        return probabilidadesEmergencia;
    }

    public static void setProbabilidadesEmergencia(ArrayList<Integer> probabilidadesEmergencia) {
        ParameterTO.probabilidadesEmergencia = probabilidadesEmergencia;
    }

    public static ArrayList<Integer> getTiempoTransicion() {
        return tiempoTransicion;
    }

    public static void setTiempoTransicion(ArrayList<Integer> tiempoTransicion) {
        ParameterTO.tiempoTransicion = tiempoTransicion;
    }

    public static ArrayList<Integer> getTiempoPuertaAbierta() {
        return tiempoPuertaAbierta;
    }

    public static void setTiempoPuertaAbierta(ArrayList<Integer> tiempoPuertaAbierta) {
        ParameterTO.tiempoPuertaAbierta = tiempoPuertaAbierta;
    }
}
