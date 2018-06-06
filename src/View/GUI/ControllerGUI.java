package View.GUI;

import ElevadorBuilder.BuilderV1;
import ParameterDTO.ParameterTO;
import Scheduler.Dispatcher;
import Scheduler.ModeStrategy.SheduleV1;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerGUI implements Initializable {
    /*Atributos*/
    Dispatcher dispatcher;

    /*MENU PRINCIPAL*/
    @FXML
    TextField mCantPersonas,mPeso,mCantPisos,mCantElevadores,mEntrePisos,mPuertaAbierta,mLlamadaElevador,mDetenerse,mDestino,mEmergencia;
    @FXML
    Button mIniciarSimulacion,mGuardarSimulacion,mCargarSimulacion;
    /*VISTA EXTERIOR*/
    @FXML
    Button eSubir,eBajar,eOnSensorPiso,eOffSensorPiso;
    @FXML
    Label ePisoActualElevador,ePiso;
    /*VISTA INTERIOR*/
    @FXML
    Button iEmergencia,iDetenerse,iPesoActual;
    @FXML
    Label iDireccionActual,iDireccionPrevista,iPisoActual;
    public void initialize(URL fxmlLocations, ResourceBundle resources){
        mCargarSimulacion.setOnAction(event -> {
            String hola = "1,2,3,4,5,6,986,3";
            ArrayList<Integer> aa = arrayListFromString(hola);
            System.out.println(aa.toString());
        });

        mIniciarSimulacion.setOnAction(event -> {
            if(setValores()){
                dispatcher = new Dispatcher(new SheduleV1());
                dispatcher.createElevadores(new BuilderV1());
                //System.out.println(dispatcher.getControlesElevador().get(0).toString());
            }else
                System.out.println("NO setValores()");
        });
    }
    public boolean setValores(){
        if(revisarValoresSimples()){
            ParameterTO.setMaxCantidadPersonas(arrayListFromString(mCantPersonas.getText()).get(0));
            ParameterTO.setMaxPeso(arrayListFromString(mPeso.getText()).get(0));
            ParameterTO.setCantidadPisos(arrayListFromString(mCantPisos.getText()).get(0));
            ParameterTO.setCantidadElevadores(arrayListFromString(mCantElevadores.getText()).get(0));
            if(revisarValoresCompuestos()){
                ParameterTO.setTiempoTransicion(arrayListFromString(mEntrePisos.getText()));
                ParameterTO.setTiempoPuertaAbierta(arrayListFromString(mPuertaAbierta.getText()));
                ParameterTO.setProbabilidadesLlamada(arrayListFromString(mLlamadaElevador.getText()));
                ParameterTO.setProbabilidadesDetener(arrayListFromString(mDetenerse.getText()));
                ParameterTO.setProbabilidadesDestino(arrayListFromString(mDestino.getText()));
                ParameterTO.setProbabilidadesEmergencia(arrayListFromString(mEmergencia.getText()));
                return true;
            }
            return false;
        }return false;
    }
    public boolean revisarValoresSimples(){
        if(arrayListFromString(mCantPersonas.getText()).size()!=1)return false;
        if(arrayListFromString(mPeso.getText()).size()!=1)return false;
        if(arrayListFromString(mCantPisos.getText()).size()!=1)return false;
        if(arrayListFromString(mCantElevadores.getText()).size()!=1)return false;


        return true;
    }
    public boolean revisarValoresCompuestos(){
        if(arrayListFromString(mEntrePisos.getText()).size()!=ParameterTO.getCantidadElevadores())return false;
        if(arrayListFromString(mPuertaAbierta.getText()).size()!=ParameterTO.getCantidadElevadores())return false;
        if(arrayListFromString(mLlamadaElevador.getText()).size()!=ParameterTO.getCantidadPisos())return false;
        if(arrayListFromString(mDestino.getText()).size()!=ParameterTO.getCantidadPisos())return false;
        if(arrayListFromString(mDetenerse.getText()).size()!=ParameterTO.getCantidadElevadores())return false;
        if(arrayListFromString(mEmergencia.getText()).size()!=ParameterTO.getCantidadElevadores())return false;

        return true;
    }
    public ArrayList<Integer> arrayListFromString(String a){
        int tmp=0;
        char currentValue;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i=0;i<a.length();i++){
            currentValue = a.charAt(i);
            if(currentValue >= 0x30 && currentValue <= 0x39){
                tmp = tmp*10 + (currentValue-0x30);
            }
            else if(currentValue==',') {
                arrayList.add(tmp);
                tmp =0;
            }

        }
        arrayList.add(tmp);
        return arrayList;
    }
}
