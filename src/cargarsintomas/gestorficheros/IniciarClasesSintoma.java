package cargarsintomas.gestorficheros;

import monitor.Sintoma;

import java.lang.reflect.Constructor;

public class IniciarClasesSintoma {

    private static final String NOMBRE_PAQUETE="sintomas.";

    public Sintoma iniciarClaseSintoma(String nombreClase, String nombreSintoma){
        Sintoma sintoma= null;
        sintoma= obtenerSintoma(nombreClase,nombreSintoma);

        return sintoma;
    }

    private Sintoma obtenerSintoma(String nombreClase, String nombreSintoma){
        Sintoma sintoma= null;
        try{
            String myClassName = Sintoma.class.getName();
            // Create class of type Base.
            Class<?> myClass = Class.forName(NOMBRE_PAQUETE + nombreClase).asSubclass(Sintoma.class);
            // Create constructor call with argument types.
            Constructor<?> ctr = myClass.getConstructor(String.class);
            // Finally create object of type Base and pass data to constructor.
            String arg1 = nombreSintoma;
            Object object = ctr.newInstance(new Object[] { arg1 });
            // Type-cast and access the data from class Base.
            Sintoma base = (Sintoma)object;
            sintoma= base;

        }catch (Exception e){

        }

        return sintoma;
    }

}
