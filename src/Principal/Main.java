package Principal;

import FileStrategy.Context;

import FileStrategy.Types.Text;
import FileStrategy.Types.Xml;
import ParameterDTO.*;
import FileStrategy.Types.Json;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/GUI/ViewGUI.fxml"));
        primaryStage.setTitle("Hello Mundo");
        primaryStage.setScene(new Scene(root, 643, 528));
        primaryStage.show();
        ParameterBO pBO;
        Context file;
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
        launch(args);



        /*
        ParameterTO.setCantidadPisos(6);
        ParameterTO.setMaxPeso(450);

        BuilderV1 builderV1 = new BuilderV1();
        Director director = new Director(builderV1);
        ControlElevador controlElevador = director.contruir();

        System.out.println(controlElevador.toString());*/
    }







}
