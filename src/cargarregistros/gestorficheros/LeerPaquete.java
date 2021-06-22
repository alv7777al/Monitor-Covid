package cargarregistros.gestorficheros;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LeerPaquete {


    public List<Class> obtenerClasesPaqueteSintomas(ClassLoader cl,String pack) throws Exception{

        String dottedPackage = pack.replaceAll("[/]", ".");
        List<Class> classes = new ArrayList<Class>();
        URL upackage = cl.getResource(pack);

        DataInputStream dis = new DataInputStream((InputStream) upackage.getContent());
        String line = null;
        while ((line = dis.readLine()) != null) {
            if(line.endsWith(".class")) {
                classes.add(Class.forName(dottedPackage+"."+line.substring(0,line.lastIndexOf('.'))));
            }
        }
        return classes;
    }

    /*
    private List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }*/

}
