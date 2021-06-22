package cargarregistros.gestorficheros;

import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {

    /**
     * Constructor que recibe OutputStream
     * @param out OutputStream
     * @throws IOException IOException
     */
    public MiObjectOutputStream(OutputStream out) throws ImagingOpException, IOException {
        super(out); //Llama al constructor de la superclase(OutputStream)
    }
    /**
     * Constructor sin parámetros
     * @throws IOException IOException
     */
    protected MiObjectOutputStream() throws IOException, SecurityException{
        super(); //Llama al constructor de la superclase(OutputStream)
    }

    @Override
    protected void writeStreamHeader() throws IOException{
        //No hacemos nada
    }
}
