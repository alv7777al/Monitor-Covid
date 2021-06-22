package cargarregistros.gestorficheros;

import cargarsintomas.CargarSintomas;
import monitor.Registro;
import monitor.Sintoma;
import monitor.Sintomas;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Archivo {

    private IniciarClasesSintoma iniciarClasesSintoma;
    private GestorFicheroSerializado gestorFicheroSerializado;
    private Validador validador;
    private LeerPaquete leerPaquete;

    private Sintomas sintomas;

    public Archivo(){

        validador= new Validador();
        iniciarClasesSintoma= new IniciarClasesSintoma();
    }



    public Sintomas leerSintomasArchivo(){
        Sintomas sintomas= new Sintomas();
        gestorFicheroSerializado= new GestorFicheroSerializado(getPathSintomas());
        gestorFicheroSerializado.getDatos().forEach(s -> {
            sintomas.add((Sintoma) s);
        });

        return sintomas;
    }

    public Registro guardarRegistro(Sintomas sintomas){
        this.sintomas= sintomas;
        Registro registro= new Registro(new Date(), sintomas);
        gestorFicheroSerializado= new GestorFicheroSerializado(getPath());
        if(registro != null){
            gestorFicheroSerializado.guardarDato(registro);
        }

        return registro;
    }

    public Sintomas getSintomasSeleccionados(){
        return this.sintomas;
    }

    private String getPath() {
        File miDir = new File(".");
        String dir = "";
        String path = "";
        String separador = System.getProperty("file.separator");

        try {
            dir = miDir.getCanonicalPath();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        boolean esDesarrollo = false;
        File file2 = new File(dir);
        String[] a = file2.list();

        for(int i = 0; i < a.length; ++i) {
            if (a[i].equals("src")) {
                esDesarrollo = true;
            }
        }

        if (!esDesarrollo) {
            path = dir + separador + "cargarregistros" + separador + "registros.dat";
        } else {
            path = dir + separador + "src" + separador + "cargarregistros" + separador + "registros.dat";
        }

        System.out.println("ruta:       " + path);
        return path;
    }

    private String getPathSintomas() {
        File miDir = new File(".");
        String dir = "";
        String path = "";
        String separador = System.getProperty("file.separator");

        try {
            dir = miDir.getCanonicalPath();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        boolean esDesarrollo = false;
        File file2 = new File(dir);
        String[] a = file2.list();

        for(int i = 0; i < a.length; ++i) {
            if (a[i].equals("src")) {
                esDesarrollo = true;
            }
        }

        if (!esDesarrollo) {
            path = dir + separador + "cargarsintomas" + separador + "sintomas.dat";
        } else {
            path = dir + separador + "src" + separador + "cargarsintomas" + separador + "sintomas.dat";
        }

        System.out.println("ruta:       " + path);
        return path;
    }

}
