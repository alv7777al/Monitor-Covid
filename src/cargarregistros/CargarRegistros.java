package cargarregistros;

import cargarregistros.gui.Ventana;
import cargarregistros.gestorficheros.Archivo;
import cargarsintomas.gestorficheros.GestorFicheroSerializado;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintomas;

import java.util.Date;

public class CargarRegistros {

    private Registros registros;
    private Sintomas sintomas;
    private GestorFicheroSerializado gestorFicheroSerializado;
    private Archivo archivo;

    public CargarRegistros(Sintomas sintomas){

        this.sintomas= sintomas;
        //cargarSintomas();
    }


    private void cargarSintomasApp(Sintomas sintomasDisponibles, Sintomas sintomasPaciente){

        new Ventana(sintomasDisponibles, sintomasPaciente);
    }

    public Registro getRegistro(){
        Sintomas sintomasPaciente= new Sintomas();
        cargarSintomasApp(this.sintomas, sintomasPaciente);
        return new Registro(new Date(), sintomasPaciente);
    }

    /*public Registro guardarRegistro(){
        Registro registro= new Registro(new Date(), sintomas);
        gestorFicheroSerializado= new GestorFicheroSerializado("registros.dat");
        if(registro != null){
            gestorFicheroSerializado.guardarDato(registro);
        }

        return registro;
    }*/

}
