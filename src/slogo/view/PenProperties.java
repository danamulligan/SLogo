package slogo.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import slogo.model.Turtle;

import java.util.ResourceBundle;

public class PenProperties {

    public final String TITLE = "Pen Properties";
    public static final Paint BACKGROUND = Color.AZURE;
    private static final String RESOURCES = "resources";
    public static final String FORMAT_PACKAGE = RESOURCES + ".formats.";
    public static final int SIZE_WIDTH = 500;
    public static final int SIZE_HEIGHT = 400;
    public static final int COLORPICKER_HEIGHT = 30;


    private Group root;
    private Scene myScene;
    private Styler styler;
    private ResourceBundle myResources;
    private ColorPicker colorPicker;
    private Visualizer myVisualzer;

    public PenProperties(Visualizer visualizer){
        styler = new Styler();
        myVisualzer = visualizer;
        Stage stage = new Stage();
        stage.setScene(setScene());
        stage.setTitle(TITLE);
        stage.show();
    }

    private Scene setScene(){
        myResources = ResourceBundle.getBundle(FORMAT_PACKAGE + "English");
        root = new Group();
        root.getChildren().add(createGrid());
        return myScene = new Scene(root, SIZE_WIDTH, SIZE_HEIGHT, BACKGROUND);
    }

    private VBox createGrid(){
        VBox vbox = new VBox(styler.createLabel(myResources.getString("PenColor")),
                penColor(),
                styler.createButton(myResources.getString("ChangePenCommand"), e->myVisualzer.getCurrentTurtle().changePenStatus()),
                styler.createButton(myResources.getString("ChangePenWidthCommand"), e->myVisualzer.getCurrentTurtle().changePenWidth())
                );

        return vbox;
    }

    private ColorPicker penColor(){
        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        colorPicker.setMaxHeight(COLORPICKER_HEIGHT);
        colorPicker.setOnAction(e -> myVisualzer.getCurrentTurtle().updatePen(colorPicker.getValue()));
        return colorPicker;
    }

    public ColorPicker getColorPicker(){return colorPicker;}

}