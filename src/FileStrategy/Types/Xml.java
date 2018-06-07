package FileStrategy.Types;

import FileStrategy.File;
import ParameterDTO.ParameterBO;
import ParameterDTO.ParameterTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Xml implements File {

    public Xml() {

    }

    @Override
    public void escribir(String nombre, ParameterBO p) {
        ParameterTO parametros = p.getParametros();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(nombre + ".xml", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writer.println("<parametros>");
        writer.println("\t<cantidadPisos> " + parametros.getCantidadPisos() + " </cantidadPisos>");
        writer.println("\t<cantidadElevadores> " + parametros.getCantidadElevadores() + " </cantidadElevadores>");
        writer.println("\t<maxCantidadPersonas> " + parametros.getMaxCantidadPersonas() + " </maxCantidadPersonas>");
        writer.println("\t<maxPeso> " + parametros.getMaxPeso() + " </maxPeso>");

        writer.println("\t<probabilidadesLlamada>");
        esrcibirLinea(writer, parametros.getProbabilidadesLlamada());
        writer.println("\t</probabilidadesLlamada>");

        writer.println("\t<probabilidadesDestino>");
        esrcibirLinea(writer, parametros.getProbabilidadesDestino());
        writer.println("\t</probabilidadesDestino>");

        writer.println("\t<probabilidadesDetener>");
        esrcibirLinea(writer, parametros.getProbabilidadesDetener());
        writer.println("\t</probabilidadesDetener>");

        writer.println("\t<probabilidadesEmergencia>");
        esrcibirLinea(writer, parametros.getProbabilidadesEmergencia());
        writer.println("\t</probabilidadesEmergencia>");

        writer.println("\t<tiempoTransicion>");
        esrcibirLinea(writer, parametros.getTiempoTransicion());
        writer.println("\t</tiempoTransicion>");

        writer.println("\t<tiempoPuertaAbierta>");
        esrcibirLinea(writer, parametros.getTiempoPuertaAbierta());
        writer.println("\t</tiempoPuertaAbierta>");

        writer.println("</parametros>");
        writer.close();

    }

    public void esrcibirLinea(PrintWriter writer, ArrayList a){
        for(int i = 0; i<a.size(); i++){
            writer.println("\t\t<value> " + a.get(i) + " </value>");
        }
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
        ArrayList<Integer> probabilidadesLlamada = readParamList(fileScanner);
        ArrayList<Integer> probabilidadesDestino = readParamList(fileScanner);
        ArrayList<Integer> probabilidadesDetener = readParamList(fileScanner);
        ArrayList<Integer> probabilidadesEmergencia = readParamList(fileScanner);
        ArrayList<Integer> tiempoTransicion = readParamList(fileScanner);
        ArrayList<Integer> tiempoPuertaAbierta = readParamList(fileScanner);

        parametros.setParametros(cantidadPisos, cantidadadElevadores, maxCantidadPersonas, maxPeso, probabilidadesLlamada,
                probabilidadesDestino, probabilidadesDetener, probabilidadesEmergencia, tiempoTransicion, tiempoPuertaAbierta);

        return parametros;

    }
    public ArrayList<Integer> readParamList(Scanner s){
        ArrayList<Integer> params = new ArrayList<>();
        s.nextLine();
        String l = s.nextLine();
        while(isValue(l)) {
            Scanner lineScanner = new Scanner(l);
            while (lineScanner.hasNext()) {
                if (lineScanner.hasNextInt()) {
                    params.add(lineScanner.nextInt());
                } else {
                    lineScanner.next();
                }
            }
            l = s.nextLine();
        }
        return params;
    }

    public boolean isValue(String l){
        Scanner lineScanner = new Scanner(l);
        while (lineScanner.hasNext()) {
            if (lineScanner.hasNextInt()) {
                return true;
            } else {
                lineScanner.next();
            }
        }
        return false;
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
