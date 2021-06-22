package cargarsintomas;

import cargarsintomas.gestorficheros.*;
import cargarsintomas.gui.Ventana;
import monitor.Sintoma;
import monitor.Sintomas;


public class CargarSintomas {

    private Sintomas sintomas;
    private GestorFicheroSerializado gestorFicheroSerializado;
    private LeerPaquete leerPaquete;
    private Ventana ventana;
    private Archivo archivo;

    public CargarSintomas(){
        cargarSintoma();
        cargarSintomas();
    }


    private void cargarSintoma() {
        this.archivo= new Archivo();
        //gestorFicheroSerializado= new GestorFicheroSerializado("sintomas.dat");
        this.sintomas = new Sintomas();
        this.archivo.getGestorFicheroSerializado().getDatos().forEach(s -> {
            sintomas.add((Sintoma) s);
        });

    }

    private void cargarSintomas(){
         new Ventana();

    }

    public Sintomas getSintomas() {
        return sintomas;
    }



}
