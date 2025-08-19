package ca.utoronto.utm.assignment2.paint.panel;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.ShapeFactory;
import ca.utoronto.utm.assignment2.paint.strategy.DrawStrategy;
import ca.utoronto.utm.assignment2.paint.strategy.SelectStrategy;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

/**
 * The PaintPanel class extends {@link Canvas} and implements the {@link EventHandler} interface
 * to handle mouse events for drawing on the canvas. It also implements {@link Observer} to update
 * the canvas whenever the model is changed. The panel allows the user to draw various shapes based
 * on the selected mode and supports shape selection, resizing, and drawing.
 */
public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private static String mode="Circle";
    private PaintModel model;
    private DrawStrategy strategy;
    private ShapeFactory shapeFactory;
    public static Color color = Color.RED;
    public static int thickness = 1;
    public static boolean shape_selected = false;
    public static boolean function_selected = false;

    /**
     * Constructs a PaintPanel and initializes event handlers for mouse interactions.
     * The panel is also subscribed to the model to receive updates whenever the model changes.
     *
     * @param model the PaintModel associated with this panel
     */
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
     * Sets the drawing mode (such as circle, rectangle, select, etc.) for the panel.
     * The mode determines the drawing strategy used for mouse interactions.
     *
     * @param mode the drawing mode to set
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

    /**
     * Returns the current drawing mode.
     *
     * @return the current drawing mode
     */
    public static String getMode(){
        return mode;
    }

    /**
     * Handles mouse events (pressed, released, dragged, etc.) based on the current drawing strategy.
     * The appropriate strategy method is invoked for each mouse event.
     *
     * @param mouseEvent the mouse event to handle
     */
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

    /**
     * Updates the canvas whenever the model changes. The canvas is cleared and redrawn with
     * the updated shapes from the model.
     *
     * @param o   the observable object (model)
     * @param arg an argument passed by the observable (not used here)
     */
    @Override
    public void update(Observable o, Object arg) {
        GraphicsContext g2d = this.getGraphicsContext2D();
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
        this.model.drawAllShapes(g2d);
        }

    /**
     * Resizes the canvas when its width or height changes. The canvas is cleared and redrawn
     * with the updated shapes from the model.
     */
    public void resizeCanvas(){
       GraphicsContext g2d = this.getGraphicsContext2D();
       g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
        this.model.drawAllShapes(g2d);
    }
}

