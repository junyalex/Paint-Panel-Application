package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class TriangleDrawStrategy implements DrawStrategy {
    public TriangleDrawStrategy() {
        super();
    }

    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {

    }

    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {

    }

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {

    }
    @Override
    public void draw(Shape shape, GraphicsContext g2d) {}
}
