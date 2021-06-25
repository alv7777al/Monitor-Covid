package cargarsintomas.gestorficheros;

import cargarsintomas.CargarSintomas;
import monitor.Sintoma;
import monitor.Sintomas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Archivo {

    private IniciarClasesSintoma iniciarClasesSintoma;
    private GestorFicheroSerializado gestorFicheroSerializado;
    private Validador validador;
    private LeerPaquete leerPaquete;

    public Archivo(){
        this.gestorFicheroSerializado= new GestorFicheroSerializado(getPath());
        validador= new Validador();
    }

    public Sintoma guardarSintoma(String nombreClase, String nombreSintoma){
        iniciarClasesSintoma= new IniciarClasesSintoma();
        nombreSintoma= nombreSintoma.trim();
        nombreSintoma= validador.normalizarTexto(nombreSintoma);
        Sintoma sintoma= iniciarClasesSintoma.iniciarClaseSintoma(nombreClase,nombreSintoma);
        if(sintoma != null){
            if(!existeSintoma(sintoma, gestorFicheroSerializado)){
                gestorFicheroSerializado.guardarDato(sintoma);
            }else{
                sintoma= null;
            }
        }

        return sintoma;
    }


    private boolean existeSintoma(Sintoma sintoma, GestorFicheroSerializado fichero){
        return validador.existeSintoma(sintoma,fichero);
    }

    public List<Class> obtenerClasesPaqueteSintomas(){
        List<Class> clases= new ArrayList<>();
        leerPaquete= new LeerPaquete();
        try {
            clases= leerPaquete.obtenerClasesPaqueteSintomas(CargarSintomas.class.getClassLoader(),"sintomas");
        }catch (Exception e){

        }

        return clases;
    }

    public Sintomas leerSintomasArchivo(){
        Sintomas sintomas= new Sintomas();
        gestorFicheroSerializado.getDatos().forEach(s -> {
            sintomas.add((Sintoma) s);
        });

        return sintomas;
    }

    public GestorFicheroSerializado getGestorFicheroSerializado() {
        return gestorFicheroSerializado;
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
            path = dir + separador + "cargarsintomas" + separador + "sintomas.dat";
        } else {
            path = dir + separador + "src" + separador + "cargarsintomas" + separador + "sintomas.dat";
        }

        System.out.println("ruta:       " + path);
        return path;
    }

    public Sintoma iniciarClaseSintoma(String nombreClase,String nombreSintoma){
        Sintoma sintoma= iniciarClasesSintoma.iniciarClaseSintoma(nombreClase,nombreSintoma);

        return sintoma;
    }

}
