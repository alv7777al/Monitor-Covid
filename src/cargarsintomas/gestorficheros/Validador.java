package cargarsintomas.gestorficheros;

//import gestorficheros.GestorFicheroSerializado;
import cargarsintomas.gestorficheros.GestorFicheroSerializado;
import monitor.Sintoma;

import java.text.Normalizer;

public class Validador {

    public boolean existeSintoma(Sintoma sintoma, GestorFicheroSerializado fichero){
        boolean existe= false;
        for(int i=0; i< fichero.getDatos().size(); i++){
            if(sintoma.toString().equals(fichero.getDatos().get(i).toString())){
                existe= true;
            }
        }

        return existe;
    }

    public String normalizarTexto(String texto){
        String cad= "";
        String cadenaNormalize = Normalizer.normalize(texto, Normalizer.Form.NFD);
        String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");

        return cad= cadenaSinAcentos;
    }

}
