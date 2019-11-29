public class Configuration {
    ClassObject controller;
    ClassObject model;

    public Configuration(ClassObject controller, ClassObject model){
        this.controller = controller;
        this. model = model;
    }

    @Override
    public String toString() {
        return "[Controller: "+ controller.getClassName() + ", "+ controller.getMethodName() +
                "Model: "+ model.getClassName() + ", " + model.getMethodName()+"]";
    }
}
