package slogo.view;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UserInterface {
  public static final int VBOX_SPACING = 10;
  public static final int COLORPICKER_HEIGHT = 30;
  public static final int LISTVIEW_WIDTH = 100;
  public static final int LISTVIEW_HEIGHT  = 250;
  public static final String RESOURCES = "resources";
  public static final String FORMAT_PACKAGE = RESOURCES + ".formats.";
  public static final String DEFAULT_COLOR_RESOURCE_PACKAGE = FORMAT_PACKAGE + ".Colors";
  public static final int HBOX_SPACING = 10;

  private Styler styler;
  private ResourceBundle myResources;
  private Visualizer myVisualizer;
  private ColorPicker backgroundColorPicker;
  private ComboBox<String> turtleBox;
  private ListView<String> myList;

  public UserInterface(Visualizer visualizer, ResourceBundle resources){
    styler = new Styler(resources);
    myResources = resources;
    myVisualizer = visualizer;
    myList = new ListView<>();
  }

  public Node createTotalUI(){
    HBox hbox = new HBox();
    hbox.setSpacing(HBOX_SPACING);
    hbox.getChildren().addAll(createTurtleUI(), createSettingsUI());
    return hbox;
  }

  private Node createTurtleUI() {
    VBox ui = new VBox();
    ui.setSpacing(VBOX_SPACING);
    ui.getChildren().addAll(styler.createButton("AddTurtle", e-> myVisualizer.addTurtle()),
        makeTurtleSelector(),
        styler.createButton("ChooseTurtle", e-> myVisualizer.getCurrentTurtle().chooseTurtle(myVisualizer.getCurrentTurtle().getTurtleImage(new Stage()))),
        styler.createButton("ResetCommand", e-> myVisualizer.reset()),
        styler.createButton("MoveTurtle", e-> myVisualizer.createMoveWindow()),
        addTurtleInfo());
    return ui;
  }

  private Node createSettingsUI() {
    VBox ui = new VBox();
    ui.setSpacing(VBOX_SPACING);
    ui.getChildren().addAll(styler.createLabel("BackgroundColor"),
        backgroundColor(),
        styler.createLabel("ChooseLanguage"),
        languageSelect(),
        styler.createButton("PenProperties", myVisualizer.createPenProperties()),
        styler.createButton("ColorPalette", myVisualizer.showColorPalette()),
        styler.createButton("ShapePalette", myVisualizer.showShapePalette()),
        styler.createButton("HelpCommand", e-> new HelpWindow(myVisualizer.getLanguage())));
    return ui;
  }

  private ColorPicker backgroundColor(){
    backgroundColorPicker = new ColorPicker();
    backgroundColorPicker.setMaxHeight(COLORPICKER_HEIGHT);
    backgroundColorPicker.setValue(myVisualizer.getBackground());
    backgroundColorPicker.setOnAction(e -> {
      myVisualizer.getUserDefined().setFill(backgroundColorPicker.getValue());
    });
    return backgroundColorPicker;
  }

  private HBox addTurtleInfo(){
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    vbox.setSpacing(VBOX_SPACING);
    vbox.getChildren().addAll(styler.createLabel("ID"),
        styler.createLabel("XCord"),
        styler.createLabel("YCord"),
        styler.createLabel("Angle"),
        styler.createLabel("PenColor"),
        styler.createLabel("PenWidth"),
        styler.createLabel("PenDownLabel"));
    myList.setPrefSize(LISTVIEW_WIDTH, LISTVIEW_HEIGHT);
    hbox.getChildren().addAll(vbox, myList);
    return hbox;
  }

  private ComboBox<String> makeTurtleSelector(){
    turtleBox = new ComboBox();
    turtleBox.setPromptText("Pick Turtle");
    turtleBox.valueProperty().addListener((o, old, neww) ->{
      myVisualizer.setTurtle(neww);
      turtleBox.getSelectionModel().select(neww);
    });
    turtleBox.itemsProperty().bind(myVisualizer.getTurtlesProperty());
    turtleBox.getSelectionModel().selectFirst();
    return turtleBox;
  }

  private ComboBox languageSelect(){
    String languages[] = { styler.getResourceText("English"),
        styler.getResourceText("Chinese"),
        styler.getResourceText("French"),
        styler.getResourceText("German"),
        styler.getResourceText("Italian"),
        styler.getResourceText("Portuguese"),
        styler.getResourceText("Spanish"),
        styler.getResourceText("Russian"),
        styler.getResourceText("Urdu")
    };
    ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(languages));
    comboBox.setValue(styler.getResourceText(myVisualizer.getLanguage()));
    comboBox.setOnAction(event -> {
      for(String key : myResources.keySet()){
        if(comboBox.getValue().toString().equals(myResources.getObject(key))){
          myVisualizer.setLanguage(key);
        }
      }
    });
    return comboBox;
  }

  public void setBackgroundPicker(String hexColor){
    backgroundColorPicker.setValue(Color.web(hexColor));
  }

  public ListView<String> getList(){return myList;}
}
