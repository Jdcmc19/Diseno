package ParameterDTO;

import java.util.ArrayList;

public class ParameterTO {
    private int cantidadPisos;
    private int cantidadElevadores;
    private int maxCantidadPersonas;
    private int maxPeso;
    private ArrayList<Integer> probabilidadesLlamada;
    private ArrayList<Integer>  probabilidadesDestino;
    private ArrayList<Integer>  probabilidadesDetener;
    private ArrayList<Integer>  probabilidadesEmergencia;
    private ArrayList<Integer>  tiempoTransicion;
    private ArrayList<Integer>  tiempoPuertaAbierta;

    public ParameterTO() {
    }

    public int getCantidadPisos() {
        return cantidadPisos;
    }

    public void setCantidadPisos(int cantidadPisos) {
        this.cantidadPisos = cantidadPisos;
    }

    public int getMaxPeso() {
        return maxPeso;
    }

    public void setMaxPeso(int maxPeso) {
        this.maxPeso = maxPeso;
    }

    public int getCantidadElevadores() {
        return cantidadElevadores;
    }

    public void setCantidadElevadores(int cantidadElevadores) {
        this.cantidadElevadores = cantidadElevadores;
    }

    public int getMaxCantidadPersonas() {
        return maxCantidadPersonas;
    }

    public void setMaxCantidadPersonas(int maxCantidadPersonas) {
        this.maxCantidadPersonas = maxCantidadPersonas;
    }

    public ArrayList<Integer> getProbabilidadesLlamada() {
        return probabilidadesLlamada;
    }

    public void setProbabilidadesLlamada(ArrayList<Integer> probabilidadesLlamada) {
        this.probabilidadesLlamada = probabilidadesLlamada;
    }

    public ArrayList<Integer> getProbabilidadesDestino() {
        return probabilidadesDestino;
    }

    public void setProbabilidadesDestino(ArrayList<Integer> probabilidadesDestino) {
        this.probabilidadesDestino = probabilidadesDestino;
    }

    public ArrayList<Integer> getProbabilidadesDetener() {
        return probabilidadesDetener;
    }

    public void setProbabilidadesDetener(ArrayList<Integer> probabilidadesDetener) {
        this.probabilidadesDetener = probabilidadesDetener;
    }

    public ArrayList<Integer> getProbabilidadesEmergencia() {
        return probabilidadesEmergencia;
    }

    public void setProbabilidadesEmergencia(ArrayList<Integer> probabilidadesEmergencia) {
        this.probabilidadesEmergencia = probabilidadesEmergencia;
    }

    public ArrayList<Integer> getTiempoTransicion() {
        return tiempoTransicion;
    }

    public void setTiempoTransicion(ArrayList<Integer> tiempoTransicion) {
        this.tiempoTransicion = tiempoTransicion;
    }

    public ArrayList<Integer> getTiempoPuertaAbierta() {
        return tiempoPuertaAbierta;
    }

    public void setTiempoPuertaAbierta(ArrayList<Integer> tiempoPuertaAbierta) {
        this.tiempoPuertaAbierta = tiempoPuertaAbierta;
    }
}
