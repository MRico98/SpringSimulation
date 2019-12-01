package kernel.controller;


public interface Controller {
    public Event procesarPeticion(String service,Object objeto);
}
