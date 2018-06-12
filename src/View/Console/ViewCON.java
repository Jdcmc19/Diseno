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
        String Tipo;
        ControllerCON Controller= new ControllerCON();
        File Temp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Pegue el path del archivo: ");
        Path = br.readLine();
        System.out.println(Path);
        System.out.println("Que tipo de archivo quiere leer? 1 para Json, 2 para Text, 3 para XML: ");
        Tipo=br.readLine();
        if(Tipo.equals("1") || Tipo.equals("Json")){
            Controller.LeerArchivos(Path,Tipo);

        }
        else{
            if(Tipo.equals("2") || Tipo.equals("Text")){
                Controller.LeerArchivos(Path,Tipo);

            }
            else{
                if(Tipo.equals("3") || Tipo.equals("XML")){
                    Controller.LeerArchivos(Path,Tipo);

                }
            }
        }
        ControllerCON controlador = new ControllerCON();
        controlador.IniciarSimulacion();

    }


}