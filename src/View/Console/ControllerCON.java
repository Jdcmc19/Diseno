package View.Console;

import ElevadorBuilder.BuilderV1;
import FileStrategy.Context;
import FileStrategy.Types.Json;
import FileStrategy.Types.Text;
import FileStrategy.Types.Xml;
import ParameterDTO.ParameterBO;
import ParameterDTO.ParameterTO;
import Scheduler.Dispatcher;
import Scheduler.ModeStrategy.SheduleV1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerCON {
    private Dispatcher dispatcher;
    private ParameterTO parametros;



    public void LeerArchivos(String path,String Tipo) throws IOException {
        ParameterBO temporal ;
        Context context = new Context();
        if(Tipo.equals("1") || Tipo.equals("Json")){
            context.setStrategy(new Json());
        }
        else if (Tipo.equals("2") || Tipo.equals("Text")){
            context.setStrategy(new Text());
        } else if(Tipo.equals("3") || Tipo.equals("XML")){
            context.setStrategy(new Xml());
        }
        temporal =context.leer(path);

        parametros=temporal.getParametros();


    }

    public void IniciarSimulacion(int unidadT){
        if(revisar()) {
            dispatcher = new Dispatcher(new SheduleV1(), parametros);

            dispatcher.createElevadores(new BuilderV1(parametros));

            dispatcher.iniciarSimulacion(unidadT);
        }
        else{
            System.out.println("Hubo un problema con los archivos");
        }
        }

    public void SimulacionPaso(int unidadT) throws IOException {
        String paso;
        if(revisar()) {
            dispatcher = new Dispatcher(new SheduleV1(), parametros);

            dispatcher.createElevadores(new BuilderV1(parametros));
            dispatcher.setStep(true);
            dispatcher.iniciarSimulacion(unidadT);

            while(dispatcher.isStep()==true){

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Continuar paso a paso? S/N:\n  ");
                paso = br.readLine();
                if(paso.equals("N")){
                    dispatcher.setStep(false);

                }
                else{
                    dispatcher.setNext(true);
                }
            }
        }
        else{
            System.out.println("Hubo un problema con los archivos");
        }
    }

        /* public boolean revisarValoresSimples(){
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
    }*/


    public boolean revisar(){
        if (this.parametros.getCantidadPisos()<2) return false;
        System.out.println("llego aqui");

        if (this.parametros.getMaxCantidadPersonas()==0) return false;
        if(this.parametros.getCantidadElevadores()==0)return false;
        if(this.parametros.getMaxPeso()==0) return false;
        if(this.parametros.getProbabilidadesDestino().size()!=this.parametros.getCantidadPisos()) return false;
        if(this.parametros.getProbabilidadesDetener().size()!=  this.parametros.getCantidadElevadores()) return false;
        if(this.parametros.getProbabilidadesEmergencia().size()!=  this.parametros.getCantidadElevadores()) return false;
        if(this.parametros.getProbabilidadesLlamada().size()!=this.parametros.getCantidadPisos()) return false;
        if(this.parametros.getTiempoPuertaAbierta().size()!=  this.parametros.getCantidadElevadores()) return false;
        if(this.parametros.getTiempoTransicion().size()!=this.parametros.getCantidadElevadores())return false;
        return true;



    }





}