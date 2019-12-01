package kernel.model;

import java.io.*;

public class FileConection {
    private final File archivo;
    private BufferedReader lector;
    private BufferedWriter escritor;

    public FileConection(String ruta) {
        archivo = new File(ruta);
    }

    public void initReader() throws IOException{
        setLector(new BufferedReader(new FileReader(getArchivo())));
    }

    public void initWritter()throws IOException{
        setEscritor(new BufferedWriter(new FileWriter(archivo)));
    }

    public File getArchivo() {
        return archivo;
    }

    public BufferedReader getLector() {
        return lector;
    }

    public void setLector(BufferedReader lector) {
        this.lector = lector;
    }

    public BufferedWriter getEscritor() {
        return escritor;
    }

    public void setEscritor(BufferedWriter escritor) {
        this.escritor = escritor;
    }

}