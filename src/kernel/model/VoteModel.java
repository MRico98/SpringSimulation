package kernel.model;

import kernel.controller.Event;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

public class VoteModel{
    private HashMap<String,Integer> votes = new HashMap<>();

    public void addvote(Event event,FileConection archivo)throws IOException{
        archivo.initReader();
        readerfile(archivo.getLector());
        votes.replace((String)event.getContenido(),votes.get(event.getContenido())+1);
        archivo.initWritter();
        writtefile(archivo.getEscritor());
    }

    private void readerfile(BufferedReader lector) throws IOException {
        String linea;
        while((linea = lector.readLine()) != null){
            String[] info = linea.split(":");
            votes.put(info[0],Integer.valueOf(info[1]));
        }
        lector.close();
    }

    private void writtefile(BufferedWriter escritor) throws IOException {
        votes.forEach((key,value)->{
            String linea = key + ":" + value + "\n";
            try {
                escritor.write(linea);
            }catch (IOException e){

            }
        });
        escritor.close();
    }

}
