package View.GUI;

import Boton.DireccionLlamada;
import ElevadorBuilder.BuilderV1;
import ElevadorBuilder.Elevador.Component.Cabin.DireccionElevador;
import ElevadorBuilder.Elevador.ControlElevador;
import FileStrategy.Context;
import FileStrategy.Types.Json;
import FileStrategy.Types.Text;
import FileStrategy.Types.Xml;
import ParameterDTO.ParameterBO;
import ParameterDTO.ParameterTO;
import Scheduler.Dispatcher;
import Scheduler.ModeStrategy.SheduleV1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerGUI implements Initializable {
    /*Atributos*/
    Dispatcher dispatcher;
    ParameterTO parameterTO = new ParameterTO();
    ArrayList<Button> botonesDestino = new ArrayList<>();
    Thread t;
    /*MENU PRINCIPAL*/
    @FXML
    TextField mCantPersonas,mPeso,mCantPisos,mCantElevadores,mEntrePisos,mPuertaAbierta,mLlamadaElevador,mDetenerse,mDestino,mEmergencia;
    @FXML
    Button mIniciarSimulacion,mGuardarSimulacion,mCargarSimulacion;
    @FXML
    RadioButton mXML,mJSON,mTXT;
    /*VISTA EXTERIOR*/
    @FXML
    Button eSubir,eBajar,eOnSensorPiso,eOffSensorPiso,eBtoPiso;
    @FXML
    Label ePisoActualElevador,ePiso;
    @FXML
    ComboBox<Integer> eCbPisos,iCbElevadores;
    @FXML
    Tab tabExterior,tabInterior;
    /*VISTA INTERIOR*/
    @FXML
    Button iEmergencia,iDetenerse,iPesoActual,iBtoElevador;
    @FXML
    Label iDireccionActual,iDireccionPrevista,iPisoActual;
    @FXML
    AnchorPane iAnchorPane;
    public void initialize(URL fxmlLocations, ResourceBundle resources){


        ToggleGroup tg = new ToggleGroup();
        mXML.setToggleGroup(tg);
        mTXT.setToggleGroup(tg);
        mJSON.setToggleGroup(tg);

        eBtoPiso.setOnAction(event -> {
            if(eCbPisos.getItems().size()==0){
                ArrayList<Integer> a = new ArrayList<>();
                for(int i=0;i<parameterTO.getCantidadPisos();i++){
                    a.add(i+1);
                }
                ObservableList<Integer> as = FXCollections.observableArrayList(a);
                eCbPisos.setItems(as);
            }
            else{
                ePiso.setText(eCbPisos.getValue()+"");
                if(eCbPisos.getValue()==1){
                    eBajar.setVisible(false);
                    eSubir.setVisible(true);
                }
                else if(eCbPisos.getValue()==parameterTO.getCantidadPisos()){
                    eSubir.setVisible(false);
                    eBajar.setVisible(true);
                }
                else{
                    eSubir.setVisible(true);
                    eBajar.setVisible(true);
                }
                actualizarPiso(eCbPisos.getValue());
            }
        });
        iBtoElevador.setOnAction(event -> {
            if(iCbElevadores.getItems().size()==0){
                ArrayList<Integer> a = new ArrayList<>();
                for(int i=0;i<parameterTO.getCantidadElevadores();i++){
                    a.add(i+1);
                }
                ObservableList<Integer> as = FXCollections.observableArrayList(a);
                iCbElevadores.setItems(as);
            }
            else{
                actualizarElevador(iCbElevadores.getValue());
            }
        });
        mCargarSimulacion.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            String path=file.getAbsolutePath();

            Context context = new Context();

            if(mJSON.isSelected()){
                context.setStrategy(new Json());
            }
            else if(mXML.isSelected()){
                context.setStrategy(new Xml());
            }
            else if(mTXT.isSelected()){
                context.setStrategy(new Text());
            }
            try{
                ParameterBO parameterBO = context.leer(path);
                parameterTO = parameterBO.getParametros();
                setBotonesDestino();
                desplegarParametros();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
        mGuardarSimulacion.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File file = directoryChooser.showDialog(null);
            String path=file.getAbsolutePath()+'\\';
            Context context = new Context();

            if(mJSON.isSelected()){
                context.setStrategy(new Json());
            }
            else if(mXML.isSelected()){
                context.setStrategy(new Xml());
            }
            else if(mTXT.isSelected()){
                context.setStrategy(new Text());
            }
            try{
                if(setValores()){
                    ParameterBO parameterBO = new ParameterBO();
                    parameterBO.setParametros(parameterTO);

                    context.escribir(path,parameterBO);
                }else
                    System.out.println("No setvalores guardar");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
        mIniciarSimulacion.setOnAction(event -> {
            if(setValores()){
                dispatcher = new Dispatcher(new SheduleV1(),parameterTO);
                dispatcher.createElevadores(new BuilderV1(parameterTO));
                setBotonesDestino();
                dispatcher.iniciarSimulacion();
            }else
                System.out.println("NO setValores()");
        });
    }
    public void desplegarParametros(){
        mCantPersonas.setText(parameterTO.getMaxCantidadPersonas()+"");
        mPeso.setText(parameterTO.getMaxPeso()+"");
        mCantPisos.setText(parameterTO.getCantidadPisos()+"");
        mCantElevadores.setText(parameterTO.getCantidadElevadores()+"");
        mEntrePisos.setText(stringFromArrayList(parameterTO.getTiempoTransicion()));
        mPuertaAbierta.setText(stringFromArrayList(parameterTO.getTiempoPuertaAbierta()));
        mLlamadaElevador.setText(stringFromArrayList(parameterTO.getProbabilidadesLlamada()));
        mDetenerse.setText(stringFromArrayList(parameterTO.getProbabilidadesDetener()));
        mDestino.setText(stringFromArrayList(parameterTO.getProbabilidadesDestino()));
        mEmergencia.setText(stringFromArrayList(parameterTO.getProbabilidadesEmergencia()));
    }
    public void setBotonesDestino(){
        botonesDestino = new ArrayList<>();
        Button bto1;
        int x = 438,y = 108;
        for(int i=1;i<=parameterTO.getCantidadPisos();i++){
            bto1 = new Button(i+"");
            bto1.setDisable(true);
            bto1.setStyle("-fx-font: 8 System;-fx-opacity: 1.0;");
            bto1.setMinSize(25,25);
            bto1.setMaxSize(25,25);
            bto1.setPrefSize(25,25);
            bto1.setVisible(true);
            if(i<=84){
                if(i%6==1){
                    x=438;
                    y+=26;
                }
                bto1.setLayoutX(x+26);
                x+=26;
                bto1.setLayoutY(y);
            }
            else if(i==85){
                x=16;y=253;
                bto1.setLayoutX(x+26);
                x+=26;
                bto1.setLayoutY(y);

            }
            else{
                if(i%6==1){
                    x=16;
                    y+=26;
                }
                bto1.setLayoutX(x+26);
                x+=26;
                bto1.setLayoutY(y);
            }
            iAnchorPane.getChildren().add(bto1);
            botonesDestino.add(bto1);
        }
    }
    public void actualizarPiso(int piso){//exterioor
        limpiarPiso();
        ePiso.setText(piso+"");
        if(piso==1&&dispatcher.getBotonesLlamadas().get(0).get(0).prendido()){
            eSubir.setStyle("-fx-opacity: 1.0;-fx-background-color: green;");
        }
        else if(piso==parameterTO.getCantidadPisos()&&dispatcher.getBotonesLlamadas().get(piso-1).get(0).prendido()){
            eBajar.setStyle("-fx-opacity: 1.0;-fx-background-color: green;");
        }
        else if(dispatcher.getBotonesLlamadas().get(piso-1).get(0).prendido()){
            eSubir.setStyle("-fx-opacity: 1.0;-fx-background-color: green;");
        }
        else if(dispatcher.getBotonesLlamadas().get(piso-1).get(1).prendido()){
            eBajar.setStyle("-fx-opacity: 1.0;-fx-background-color: green;");
        }if(dispatcher.getCalendarizado()[piso-1]!=null){
            ePisoActualElevador.setText(dispatcher.getCalendarizado()[piso-1]+"");
            if(dispatcher.getControlesElevador().get(dispatcher.getCalendarizado()[piso-1]).getSensorPiso().get(piso-1).isOpen()){
                eOnSensorPiso.setStyle("-fx-opacity: 1.0;-fx-background-color: green;");
            }
            else
                eOffSensorPiso.setStyle("-fx-opacity: 1.0;-fx-background-color: red;");
        }
    }
    public void limpiarPiso(){
        ePiso.setText("");
        ePisoActualElevador.setText("");
        eSubir.setStyle("-fx-opacity: 1.0;");
        eBajar.setStyle("-fx-opacity: 1.0;");
        eOffSensorPiso.setStyle("-fx-opacity: 1.0;");
        eOnSensorPiso.setStyle("-fx-opacity: 1.0;");
    }
    public void hiloElevador(int elevador){
        if(t!= null) {
            t.stop();
        }
        t = new Thread(() -> {
            while (true) {
                actualizarElevador(elevador);
            }
        });
    }

    public void hiloPiso(int piso){
        if(t!= null) {
            t.stop();
        }
        t = new Thread(() -> {
            while (true) {
                actualizarElevador(piso);
            }
        });
    }
    public void actualizarElevador(int elevador){//interior
        limpiarElevador();
        ControlElevador ce = dispatcher.getControlesElevador().get(elevador-1);
        for(int i=0;i<parameterTO.getCantidadPisos();i++){
            if(ce.getBotonesDestino().get(i).prendido()){
                botonesDestino.get(i).setStyle("-fx-opacity: 1.0;-fx-background-color: green;");
            }
        }
        iPisoActual.setText(ce.getCabina().getPisoActual()+"");
        iDireccionActual.setText(ce.getCabina().getDireccionActual()+"");
        iDireccionPrevista.setText(ce.getCabina().getDireccionPrevista()+"");
        if(ce.getCabina().getInterruptorEmergencia().getOn())
            iEmergencia.setStyle("-fx-opacity: 1.0;-fx-background-color: red;");
        else
            iEmergencia.setStyle("-fx-opacity: 1.0;");
        if(ce.getCabina().getBotonDetenerse().getOn())
            iDetenerse.setStyle("-fx-opacity: 1.0;-fx-background-color: green;");
        else
            iDetenerse.setStyle("-fx-opacity: 1.0;");
        if(ce.getSensorPeso().overFlow())
            iPesoActual.setStyle("-fx-opacity: 1.0;-fx-background-color: red;");
        else
            iPesoActual.setStyle("-fx-opacity: 1.0;");
    }
    public void limpiarElevador(){
        for(int i=0;i<parameterTO.getCantidadPisos();i++){
            botonesDestino.get(i).setStyle("-fx-opacity: 1.0");
        }
        iPisoActual.setText("");
        iDireccionActual.setText("");
        iDireccionPrevista.setText("");
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

    /**********************************INTERRUPCIONES***********************************/
    public void botonDestinoInterrupcion(int piso, int elevador){
        dispatcher.botonDestinoInterrupcion(piso,elevador);
    }
    public void sensorPisoInterrupcion(int piso, int elevador){
        dispatcher.sensorPisoInterrupcion(piso, elevador);
    }
    public void botonLlamadaInterrupcion(int piso, DireccionLlamada direccionLlamada){
        dispatcher.botonLlamadaInterrupcion(piso,direccionLlamada);
    }
    public void controlesMotorInterrupcion(int elevador, int control){
        dispatcher.controlesMotorInterrupcion(elevador,control);

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
    public String stringFromArrayList(ArrayList<Integer> arrayList){
        String a = "";
        for(int i=0;i<arrayList.size();i++){
            a+=arrayList.get(i);
            if(i!=arrayList.size()-1){
                a+=",";
            }
        }
        return a;
    }
}
