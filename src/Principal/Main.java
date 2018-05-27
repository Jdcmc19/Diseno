package Principal;

import ElevadorBuilder.BuilderV1;
import ElevadorBuilder.Director;
import ElevadorBuilder.Elevador.ControlElevador;
import ParameterDTO.ParameterTO;
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
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);

        ParameterTO.setCantidadPisos(6);
        ParameterTO.setMaxPeso(450);

        BuilderV1 builderV1 = new BuilderV1();
        Director director = new Director(builderV1);
        ControlElevador controlElevador = director.contruir();

        System.out.println(controlElevador.toString());
    }
}
