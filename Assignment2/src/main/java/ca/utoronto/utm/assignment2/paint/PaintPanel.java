package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import ca.utoronto.utm.assignment2.scribble.ScribblePanel;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private String mode="Circle";
    private PaintModel model;
    private DrawStrategy strategy;
    private ShapeFactory shapeFactory;
    public static Color color = Color.RED;

    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model=model;
        this.model.addObserver(this);
        this.shapeFactory = new ShapeFactory();

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
    }
    /**
     *  Controller aspect of this
     */
    public void setMode(String mode){
        this.strategy = shapeFactory.makeStrategy(mode);
        this.mode = mode;
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
        //ArrayList<Shape> shapes = this.model.getShape();
        this.model.drawAllShapes(g2d);
        //this.strategy.draw(this.model, g2d);
        }
    }

