package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class SquareDrawStrategy implements DrawStrategy {
    public SquareDrawStrategy() {
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
    public void draw(PaintModel model, GraphicsContext g2d) {}
}
