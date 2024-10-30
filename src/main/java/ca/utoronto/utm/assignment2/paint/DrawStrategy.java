package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.HashMap;

public interface DrawStrategy {

    void onMousePressed(MouseEvent e,PaintModel model);
    void onMouseDragged(MouseEvent e,PaintModel model);
    void onMouseReleased(MouseEvent e,PaintModel model);
    //changed
    //void draw(PaintModel model, GraphicsContext g2d);
    void draw(Shape shape, GraphicsContext g2d);
}
