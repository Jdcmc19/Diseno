package FileStrategy.Types;
import ParameterDTO.ParameterBO;
import ParameterDTO.ParameterTO;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Text implements FileStrategy.File {

    public Text() {
    }

    @Override
    public void escribir(String nombre, ParameterBO p) {
        ParameterTO parametros = p.getParametros();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(nombre + ".txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writer.println(parametros.getCantidadPisos());
        writer.println(parametros.getCantidadElevadores());
        writer.println(parametros.getMaxCantidadPersonas());
        writer.println(parametros.getMaxPeso());
        esrcibirLinea(writer, parametros.getProbabilidadesLlamada());
        esrcibirLinea(writer, parametros.getProbabilidadesDestino());
        esrcibirLinea(writer, parametros.getProbabilidadesDetener());
        esrcibirLinea(writer, parametros.getProbabilidadesEmergencia());
        esrcibirLinea(writer, parametros.getTiempoTransicion());
        esrcibirLinea(writer, parametros.getTiempoPuertaAbierta());

        writer.close();
    }

    public void esrcibirLinea(PrintWriter writer, ArrayList a){
        writer.print(a.get(0));
        for(int i = 1; i<a.size(); i++){
            writer.print(" "+ a.get(i));
        }
        writer.println();
    }

    @Override
    public ParameterBO leer(String nombre) throws IOException {
        ParameterBO parametros = new ParameterBO();
        java.io.File file = new java.io.File(nombre);
        Scanner fileScanner = new Scanner(file);
        int cantidadPisos = Integer.parseInt(fileScanner.nextLine());
        int cantidadadElevadores = Integer.parseInt(fileScanner.nextLine());
        int maxCantidadPersonas = Integer.parseInt(fileScanner.nextLine());
        int maxPeso = Integer.parseInt(fileScanner.nextLine());
        ArrayList<Integer> probabilidadesLlamada = readParamList(fileScanner.nextLine());
        ArrayList<Integer> probabilidadesDestino = readParamList(fileScanner.nextLine());
        ArrayList<Integer> probabilidadesDetener = readParamList(fileScanner.nextLine());
        ArrayList<Integer> probabilidadesEmergencia = readParamList(fileScanner.nextLine());
        ArrayList<Integer> tiempoTransicion = readParamList(fileScanner.nextLine());
        ArrayList<Integer> tiempoPuertaAbierta = readParamList(fileScanner.nextLine());

        parametros.setParametros(cantidadPisos, cantidadadElevadores, maxCantidadPersonas, maxPeso, probabilidadesLlamada,
                probabilidadesDestino, probabilidadesDetener, probabilidadesEmergencia, tiempoTransicion, tiempoPuertaAbierta);

        return parametros;

    }
    public ArrayList<Integer> readParamList(String l){
        ArrayList<Integer> params = new ArrayList<>();
        Scanner lineScanner = new Scanner(l);
        while (lineScanner.hasNextInt())
        {
            params.add(lineScanner.nextInt());
        }
        return params;
    }
}
