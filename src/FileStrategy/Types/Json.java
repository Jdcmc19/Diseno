package FileStrategy.Types;

import FileStrategy.File;
import ParameterDTO.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Json implements File {

    public Json() {
    }

    @Override
    public void escribir(String nombre, ParameterBO p) {
        ParameterTO parametros = p.getParametros();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(nombre+".js", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writer.println("{");
        writer.println("\t\"cantidadPisos\": " + parametros.getCantidadPisos() + " ,");
        writer.println("\t\"cantidadElevadores\": " + parametros.getCantidadElevadores() + " ,");
        writer.println("\t\"maxCantidadPersonas\": " + parametros.getMaxCantidadPersonas() + " ,");
        writer.println("\t\"maxPeso\": " + parametros.getMaxPeso() + " ,");

        writer.print("\t\"probabilidadesLlamada\": [ ");
        esrcibirLinea(writer, parametros.getProbabilidadesLlamada());

        writer.print("\t\"probabilidadesDestino\": [ ");
        esrcibirLinea(writer, parametros.getProbabilidadesDestino());

        writer.print("\t\"probabilidadesDetener\": [ ");
        esrcibirLinea(writer, parametros.getProbabilidadesDetener());

        writer.print("\t\"probabilidadesEmergencia\": [ ");
        esrcibirLinea(writer, parametros.getProbabilidadesEmergencia());

        writer.print("\t\"tiempoTransicion\": [ ");
        esrcibirLinea(writer, parametros.getTiempoTransicion());

        writer.print("\t\"tiempoPuertaAbierta\": [ ");
        esrcibirLinea(writer, parametros.getTiempoPuertaAbierta());


        writer.println("}");
        writer.close();

    }

    public void esrcibirLinea(PrintWriter writer, ArrayList a){
        writer.print(a.get(0));
        for(int i = 1; i<a.size(); i++){
            writer.print(" , "+ a.get(i));
        }
        writer.println(" ] ,");
    }

    @Override
    public ParameterBO leer(String nombre) throws IOException {
        ParameterBO parametros = new ParameterBO();
        java.io.File file = new java.io.File(nombre);
        Scanner fileScanner = new Scanner(file);
        fileScanner.nextLine();
        int cantidadPisos = readParam(fileScanner.nextLine());
        int cantidadadElevadores = readParam(fileScanner.nextLine());
        int maxCantidadPersonas = readParam(fileScanner.nextLine());
        int maxPeso = readParam(fileScanner.nextLine());
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
        while (lineScanner.hasNext()) {
            if (lineScanner.hasNextInt()) {
                params.add(lineScanner.nextInt());
            } else {
                lineScanner.next();
            }
        }
        return params;
    }
    public int readParam(String line){
        Scanner lineScanner = new Scanner(line);
        while (lineScanner.hasNext()) {
            if (lineScanner.hasNextInt()) {
                return lineScanner.nextInt();
            } else {
                lineScanner.next();
            }
        }
        return 0;
    }

}
