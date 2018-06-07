package View.GUI;

import ElevadorBuilder.BuilderV1;
import ElevadorBuilder.Elevador.Component.Cabin.DireccionElevador;
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
    ParameterTO parameterTO = new ParameterTO();

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
        });

        mIniciarSimulacion.setOnAction(event -> {
            if(setValores()){
                dispatcher = new Dispatcher(new SheduleV1());
                dispatcher.createElevadores(new BuilderV1(parameterTO));
                dispatcher.setParameterTO(parameterTO);
            }else
                System.out.println("NO setValores()");
        });
    }
    public boolean setValores(){
        if(revisarValoresSimples()){
            parameterTO.setMaxCantidadPersonas(arrayListFromString(mCantPersonas.getText()).get(0));
            parameterTO.setMaxPeso(arrayListFromString(mPeso.getText()).get(0));
            parameterTO.setCantidadPisos(arrayListFromString(mCantPisos.getText()).get(0));
            parameterTO.setCantidadElevadores(arrayListFromString(mCantElevadores.getText()).get(0));
            if(revisarValoresCompuestos()){
                parameterTO.setTiempoTransicion(arrayListFromString(mEntrePisos.getText()));
                parameterTO.setTiempoPuertaAbierta(arrayListFromString(mPuertaAbierta.getText()));
                parameterTO.setProbabilidadesLlamada(arrayListFromString(mLlamadaElevador.getText()));
                parameterTO.setProbabilidadesDetener(arrayListFromString(mDetenerse.getText()));
                parameterTO.setProbabilidadesDestino(arrayListFromString(mDestino.getText()));
                parameterTO.setProbabilidadesEmergencia(arrayListFromString(mEmergencia.getText()));
                return true;
            }
            return false;
        }return false;
    }
    public void botonLlamada(int piso, int direccionElevador){
         String a= dispatcher.getBotonesLlamadas().get(piso-1).get(direccionElevador).enviarSolicitud();
    }
    public void botonDestino(int elevador,int piso){
        String a= dispatcher.getControlesElevador().get(elevador-1).getBotonesDestino().get(piso-1).enviarSolicitud();
    }
    public void interruptorEmergencia(int elevador){
        String a= dispatcher.getControlesElevador().get(elevador-1).getCabina().getInterruptorEmergencia().enviarSolicitud();
    }
    public void botonDetenerse(int elevador){
        String a= dispatcher.getControlesElevador().get(elevador-1).getCabina().getBotonDetenerse().enviarSolicitud();
    }
    public void sensorPeso(int elevador){
        String a= dispatcher.getControlesElevador().get(elevador-1).getSensorPeso().enviarSolicitud();
    }
    public void sensorPiso(int piso){
        String a= dispatcher.getControlesElevador().get(piso-1).getSensorPiso().get(piso-1).enviarSolicitud();
    }
    public boolean revisarValoresSimples(){
        if(arrayListFromString(mCantPersonas.getText()).size()!=1)return false;
        if(arrayListFromString(mPeso.getText()).size()!=1)return false;
        if(arrayListFromString(mCantPisos.getText()).size()!=1)return false;
        if(arrayListFromString(mCantElevadores.getText()).size()!=1)return false;


        return true;
    }
    public boolean revisarValoresCompuestos(){
        if(arrayListFromString(mEntrePisos.getText()).size()!=parameterTO.getCantidadElevadores())return false;
        if(arrayListFromString(mPuertaAbierta.getText()).size()!=parameterTO.getCantidadElevadores())return false;
        if(arrayListFromString(mLlamadaElevador.getText()).size()!=parameterTO.getCantidadPisos())return false;
        if(arrayListFromString(mDestino.getText()).size()!=parameterTO.getCantidadPisos())return false;
        if(arrayListFromString(mDetenerse.getText()).size()!=parameterTO.getCantidadElevadores())return false;
        if(arrayListFromString(mEmergencia.getText()).size()!=parameterTO.getCantidadElevadores())return false;

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
