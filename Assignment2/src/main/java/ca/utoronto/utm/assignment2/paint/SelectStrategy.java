package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class SelectStrategy implements DrawStrategy {
    private PaintModel model;
    private Point SelectPoint;
    private ArrayList<Shape> shapes;

    public SelectStrategy(PaintModel model) {
        super();
        this.model = model;
        shapes = model.getShapes();
    }

    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        SelectPoint = new Point(e.getX(), e.getY());
        boolean contains = false;
        for (int index = 0; index < shapes.size() && !contains; index++) {
            Shape shape = shapes.get(index);
            if (shape.contains(SelectPoint)) {
                new SelectMode(shape);
                System.out.println("Shape selected: " + shape);
                contains = true;
                break;
            }
        }
        if (!contains) {
            System.out.println("No valid shape selected");
            SelectMode.clearSelection();
        }
    }

    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
    }

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
    }

    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle) {
    }
}
