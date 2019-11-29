package Kernel.Controller;

public class Event {
    private String peticion;
    private Object contenido;

    public Event(String peticion, Object contenido){
        this.peticion=peticion;
        this.contenido= contenido;
    }

    public boolean isValid(){
        return !peticion.isEmpty() && contenido != null;
    }

    public String getPeticion() {
        return peticion;
    }

    public Object getContenido() {
        return contenido;
    }

}
