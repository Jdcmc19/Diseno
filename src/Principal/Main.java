package Principal;

import FileStrategy.Context;

import FileStrategy.Types.Text;
import FileStrategy.Types.Xml;
import ParameterDTO.*;
import FileStrategy.Types.Json;
import View.Console.ViewCON;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/GUI/ViewGUI.fxml"));
        primaryStage.setTitle("Hello Mundo");
        primaryStage.setScene(new Scene(root, 643, 528));
        primaryStage.show();
        //ParameterBO pBO;
        //Context file;
        /*file = new Context(new Xml());
        pBO = file.leer("pruebaXml.xml");
        //file.escribir("pruebaXml", pBO);
        file = new Context(new Json());
        //pBO = file.leer("pruebaJason.js");
        file.escribir("pruebaJason", pBO);
        file = new Context(new Text());
        file.escribir("pruebaTxt", pBO);*/
        //pBO = file.leer("pruebaTxt.txt");
    }

    public static void main(String[] args) {
        //TODO VER EL LOG EN LA ESPECIFICACION(PERSONAS QUE ENTRAN Y SALEN)
        //TODO VER QUE PASA CON EL DIRECCION PREVISTA QUE NUNCA CAMBIA DE NINGUNA
        //TODO VER QUE SIRVA LA VISTA DE CONSOLA

        //launch(args);



        String RespuestaConsola;
        ViewCON Vista_consola = new ViewCON();


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            while(true) {
                System.out.print("Desea empezar con Consola o Interfaz (C/I): ");
                RespuestaConsola = br.readLine();
                if(RespuestaConsola.equals("I")){
                    launch(args);
                    break;
                }

                else{
                    Vista_consola.menu();
                    break;

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        /*
        ParameterTO.setCantidadPisos(6);
        ParameterTO.setMaxPeso(450);

        BuilderV1 builderV1 = new BuilderV1();
        Director director = new Director(builderV1);
        ControlElevador controlElevador = director.contruir();

        System.out.println(controlElevador.toString());*/
    }
}
