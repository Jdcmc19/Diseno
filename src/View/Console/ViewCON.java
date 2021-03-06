package View.Console;

import FileStrategy.File;
import FileStrategy.Types.Json;
import FileStrategy.Types.Text;
import FileStrategy.Types.Xml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewCON {
    private String Path;

    // menú de antes
    public void menu() throws IOException {
       // appletkeytest test = new appletkeytest();
        //test.inciar();
        String Tipo;
        String modo;
        ControllerCON controller= new ControllerCON();
        File Temp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Pegue el path del archivo: ");
        Path = br.readLine();
        System.out.println(Path);
        System.out.println("Que tipo de archivo quiere leer? 1 para Json, 2 para Text, 3 para XML: ");
        Tipo=br.readLine();
        if(Tipo.equals("1") || Tipo.equals("Json")){
            controller.LeerArchivos(Path,Tipo);

        }
        else{
            if(Tipo.equals("2") || Tipo.equals("Text")){
                controller.LeerArchivos(Path,Tipo);

            }
            else{
                if(Tipo.equals("3") || Tipo.equals("XML")){
                    controller.LeerArchivos(Path,Tipo);

                }
            }
        }
        System.out.println("Desea empezar la simulación en modo automatico?: ");
        modo=br2.readLine();
        if (modo.equals("S")){
            controller.IniciarSimulacion(1500);
        }
        else{
            controller.SimulacionPaso(1500);

        }



    }


}