package Kernel.Controller;


public class Controlador implements Controller {

    @Override
    public Event procesarPeticion(String[] params){
        System.out.println(params[0]);
        return new Event("WraperEvent","ContentInWrapper");
    }


    public boolean evaluar(Event event) {
        return event.isValid();
    }


}
