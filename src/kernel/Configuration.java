package kernel;

public class Configuration {
    private ClassObject controller;
    private ClassObject model;

    public Configuration(ClassObject controller, ClassObject model){
        this.controller = controller;
        this. model = model;
    }

    @Override
    public String toString() {
        return "[Controller: "+ getController().getClassName() + ", "+ getController().getMethodName() +
                "Model: "+ getModel().getClassName() + ", " + getModel().getMethodName()+"]";
    }

    public ClassObject getController() {
        return controller;
    }

    public void setController(ClassObject controller) {
        this.controller = controller;
    }

    public ClassObject getModel() {
        return model;
    }

    public void setModel(ClassObject model) {
        this.model = model;
    }

}