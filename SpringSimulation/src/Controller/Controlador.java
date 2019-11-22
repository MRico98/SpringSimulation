package Controller;


public class Controlador implements Controller {

    @Override
    public void procesarPeticion(Event event){
        if (evaluar(event)){
            switch (event.getPeticion()){
                case "add":
                    //Creamos el evento para añadir votos
                    break;
                case "substract":
                    //Creamos el evento para eliminar votos
                    break;
            }
        }
    }


    public boolean evaluar(Event event) {
        return event.isValid();
    }


}
