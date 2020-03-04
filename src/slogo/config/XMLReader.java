package slogo.config;

import java.io.File;
import java.io.IOException;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import slogo.view.Visualizer;

public class XMLReader {
  private File myFile;
  private Document myDoc;
  private Visualizer myVisualizer;

  public XMLReader(File file, Stage stage){
    myFile = file;
    setupDocument();
    readFile();
    myVisualizer = new Visualizer(stage);
  }

  private void setupDocument() {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;
    try {
      builder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
    }
    try {
      myDoc = builder.parse(myFile);
    } catch (SAXException | IOException e) {
    }
    myDoc.getDocumentElement().normalize();
  }

  private void readFile(){
    readPreferences();
    readTurtles();
  }

  private String getElementValue(Element element, String node){
    return element.getElementsByTagName(node).item(0).getTextContent();
  }

  private void readPreferences(){
    NodeList preferences = myDoc.getElementsByTagName("Preferences");
    Node prefNode = preferences.item(0);

    if(prefNode.getNodeType() == Node.ELEMENT_NODE){
      Element prefElement = (Element) prefNode;
      getLanguage(prefElement);
      getBackground(prefElement);
    }
  }

  private void getLanguage(Element prefElement) {
    myVisualizer.setLanguage(getElementValue(prefElement, "Language"));
  }

  private void getBackground(Element prefElement) {
    myVisualizer.setBackgroundColor(getElementValue(prefElement, "Background"));
  }

  private void readTurtles() {
    NodeList turtles = myDoc.getElementsByTagName("Preferences");
    Node turtleNode = turtles.item(0);

    if(turtleNode.getNodeType() == Node.ELEMENT_NODE){
      Element turtleElement = (Element) turtleNode;
      addTurtles(turtleElement);
    }
  }

  private void addTurtles(Element turtlesElement) {
    NodeList turtleList = turtlesElement.getElementsByTagName("Turtles");
    for(int i = 0; i < turtleList.getLength(); i++){
      Node turtle = turtleList.item(i);
      if(turtle.getNodeType() == Node.ELEMENT_NODE){
        Element turtleElement = (Element) turtle;
        myVisualizer.addTurtle(turtleElement.getAttribute("name"),
            Double.parseDouble(turtleElement.getAttribute("xpos")),
            Double.parseDouble(turtleElement.getAttribute("ypos")),
            Integer.parseInt(turtleElement.getAttribute("heading"))
            );
      }
    }
  }
}