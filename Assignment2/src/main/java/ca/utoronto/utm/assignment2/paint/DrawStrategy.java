package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 *This defines the methods required for handling user interactions
 * and creating shapes on the canvas in the paint application.
 */
public interface DrawStrategy {

    void onMousePressed(MouseEvent e,PaintModel model);
    void onMouseDragged(MouseEvent e,PaintModel model);
    void onMouseReleased(MouseEvent e,PaintModel model);
    //changed
    //void draw(PaintModel model, GraphicsContext g2d);

    void draw(Shape shape, GraphicsContext g2d, String currStyle);
}
