package ca.utoronto.utm.assignment2.scribble;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ScribblePanel extends Canvas implements EventHandler<MouseEvent>{
    double startx ,starty;

    public ScribblePanel(){
        super(200,200);
        // when we draw, click, move and then
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
            startx = mouseEvent.getX();
            starty = mouseEvent.getY();
        }
        else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED){
            double curx = mouseEvent.getX();
            double cury = mouseEvent.getY();

            gc.strokeLine(startx, starty, curx, cury);
            startx = curx;
            starty = cury;
        }
    }
}
