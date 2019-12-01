package kernel;

import kernel.controller.Controller;
import kernel.controller.Event;
import kernel.model.FileConection;
import kernel.model.VoteModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Configurator {
    private final HashMap<String, Configuration> configurationHashMap = new HashMap<>();
    private FileConection archivovotos;

    public void loadConfigurations(String fileRoute,String archivo) {
        try {
            NodeList nList = loadXMLNodes(fileRoute);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    addConfigurationsDependen(nNode);
                }
            }
            this.setArchivovotos(new FileConection(archivo));
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private NodeList loadXMLNodes(String fileRoute) throws ParserConfigurationException, IOException, SAXException {
        File fXmlFile = new File(fileRoute);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName("service");
    }

    private void addConfigurationsDependen(Node nNode){
        Element eElement = (Element) nNode;
        String serviceName = eElement.getAttribute("name");
        NodeList classStructure = eElement.getElementsByTagName("class");
        ClassObject[] objects = new ClassObject[2];
        for(int i=0; i< classStructure.getLength();i++){
            Element classElement = (Element) classStructure.item(i);
            String className = classElement.getAttribute("name");
            String methodName = eElement.getElementsByTagName("method").item(i).getTextContent();
            objects[i] = new ClassObject(className,methodName);
        }
        addConfig(serviceName,new Configuration(objects[0],objects[1]));//Se agrega la configuracion al hashmap
    }

    public void doSomething(String service, String parameter) {
        try {
            Configuration config = (Configuration)getConfigurationHashMap().get(service);
            Class classController = Class.forName(config.getController().getClassName());
            Controller controller = (Controller) classController.newInstance();
            Method methodController = classController.getMethod(config.getController().getMethodName(),String.class,Object.class);
            Event event = (Event) methodController.invoke(controller,service, parameter);
            Class classModel = Class.forName(config.getModel().getClassName());
            VoteModel model = (VoteModel) classModel.newInstance();
            Method methodModel = classModel.getMethod(config.getModel().getMethodName(),Event.class,FileConection.class);
            methodModel.invoke(model,event,getArchivovotos());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void addConfig(String service, Configuration config){
        configurationHashMap.put(service,config);
    }

    public Map getConfigurationHashMap() {
        return configurationHashMap;
    }

    public FileConection getArchivovotos() {
        return archivovotos;
    }

    public void setArchivovotos(FileConection archivovotos) {
        this.archivovotos = archivovotos;
    }

    public static void main(String[] args) {
        Configurator config = new Configurator();
        config.loadConfigurations("src/kernel/source/config.xml","src/kernel/source/votes.txt");
        config.doSomething("addvote", "A" );
        config.doSomething("addvote","B");
        config.doSomething("addvote","C");
    }

}
