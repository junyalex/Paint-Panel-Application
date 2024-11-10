package ca.utoronto.utm.assignment2.paint;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import ca.utoronto.utm.assignment2.scribble.ScribblePanel;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private static String mode="Circle";
    private PaintModel model;
    private DrawStrategy strategy;
    private ShapeFactory shapeFactory;
    public static Color color = Color.RED;
    public static int thickness = 1;
    public static boolean shape_selected = false;
    public static boolean function_selected = false;

    public PaintPanel(PaintModel model) {
        super(500, 500);
        this.model=model;
        this.model.addObserver(this);
        this.shapeFactory = new ShapeFactory();


        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);

        this.widthProperty().addListener((observable, oldValue, newValue) -> resizeCanvas());
        this.heightProperty().addListener((observable, oldValue, newValue) -> resizeCanvas());

    }
    /**
     *  Controller aspect of this
     */
    public void setMode(String mode){
        if (mode.equals("select")) {
            this.mode = "select";
            this.strategy = new SelectStrategy(this.model);
            return;
        }
        this.strategy = shapeFactory.makeStrategy(mode);
        this.mode = mode;
    }

    public static String getMode(){
        return mode;
    }

    @Override
    public void handle(MouseEvent mouseEvent){
        if(this.strategy == null) return;

        if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
            this.strategy.onMousePressed(mouseEvent, this.model);
        }
        else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
            this.strategy.onMouseReleased(mouseEvent, this.model);
        }
        else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            this.strategy.onMouseDragged(mouseEvent, this.model);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        GraphicsContext g2d = this.getGraphicsContext2D();
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
        this.model.drawAllShapes(g2d);
        }

    public void resizeCanvas(){
       GraphicsContext g2d = this.getGraphicsContext2D();
       g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
        this.model.drawAllShapes(g2d);
    }
    }

