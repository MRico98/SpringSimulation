package kernel.controller;


public class Controlador implements Controller {

    @Override
    public Event procesarPeticion(String service,Object objeto){
        return new Event(service,objeto);
    }

    public boolean evaluar(Event event) {
        return event.isValid();
    }

}
