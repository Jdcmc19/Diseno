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

import java.io.IOException;

public class ControllerCON {
    private Dispatcher dispatcher;
    private ParameterTO parametros;
    //leer tonteras funcoines marco
    // objeto dispatcher y parameterTo
    //controller gui 94


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

    public void IniciarSimulacion(){
        if(revisar()) {
            dispatcher = new Dispatcher(new SheduleV1(), parametros);

            dispatcher.createElevadores(new BuilderV1(parametros));
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