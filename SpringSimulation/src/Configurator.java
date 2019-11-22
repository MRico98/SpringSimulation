import java.io.File;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Configurator {
    HashMap<String,Configuration> configurationHashMap = new HashMap<>();

    public void loadConfigurations(String fileRoute){
        try {
            File fXmlFile = new File(fileRoute);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("service");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String serviceName = eElement.getAttribute("name");//Se obtiene el nombre del servicio
                    NodeList classStructure = eElement.getElementsByTagName("class");
                    String className,methodName;
                    ClassObject[] objects = new ClassObject[2];
                    for(int i=0; i< classStructure.getLength();i++){
                        Element classElement = (Element) classStructure.item(i);
                        className = classElement.getAttribute("name");//Se obtiene el nombre de la clase
                        methodName = eElement.getElementsByTagName("method").item(i).getTextContent();//Se obtiene el nombre del metodo
                        objects[i] = new ClassObject(className,methodName);
                    }
                    addConfig(serviceName,new Configuration(objects[0],objects[1]));//Se agrega la configuracion al hashmap
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doSomething(String service, String parameters){

    }

    private void addConfig(String service, Configuration config){
        configurationHashMap.put(service,config);
    }

    public static void main(String[] args){
        Configurator config = new Configurator();
        config.loadConfigurations("C:\\Users\\diego\\Desktop\\Universidad\\5_Semestre\\Arquitectura De Software\\U2_T5\\MVCVotos\\src\\config.xml");
    }

}
