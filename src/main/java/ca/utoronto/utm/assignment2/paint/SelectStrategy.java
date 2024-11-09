package ca.utoronto.utm.assignment2.paint;

import javafx.beans.Observable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class SelectStrategy implements DrawStrategy {
    private PaintModel model;
    private ArrayList<Shape> shapes;
    private boolean dragging = false;
    private Shape selectedShape = null;
    private SelectMode mode;
    private Point currPoint;
    private Point initialPoint;
    private Point originalPoint;

    public SelectStrategy(PaintModel model) {
        super();
        this.model = model;
        shapes = model.getShapes();
        this.mode = SelectMode.getInstance();
    }

    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        this.selectedShape = SelectMode.getSelectedShape();
        initialPoint = new Point(e.getX(), e.getY());
        currPoint = new Point(e.getX(), e.getY());
        boolean contains = false;

        // if selectedShape & and clicked above the shape -> dragging mode
        if (this.selectedShape != null && this.selectedShape.contains(currPoint)) {
            dragging = true;
            return;
        }

        // if there is no selectedShape, then select it
        for (int index = shapes.size() - 1; index >= 0 && !contains; index--) {
            Shape shape = shapes.get(index);
            if (shape.contains(currPoint)) {
                SelectMode.setSelectedShape(shape, model);
                this.selectedShape = shape;
                System.out.println("Shape selected: " + shape);
                contains = true;
                break;
            }
        }
        if (!contains){
            System.out.println("No valid shape selected");
            SelectMode.clearSelection(model);
        }
    }

        @Override
        public void onMouseDragged (MouseEvent e, PaintModel model) {
            if (dragging && this.selectedShape != null) {
                double deltaX = e.getX() - currPoint.x;
                double deltaY = e.getY() - currPoint.y;

                this.selectedShape.move(deltaX, deltaY);

                model.addShapePreview(this.selectedShape);

                currPoint = new Point(e.getX(), e.getY());

            }
        }

            @Override
            public void onMouseReleased (MouseEvent e, PaintModel model){
                if (selectedShape != null && dragging) {

                    dragging = false;
                }
                dragging = false;
                currPoint = null;
                initialPoint = null;

            }

            @Override
            public void draw (Shape shape, GraphicsContext g2d, String currStyle){
            }
        }
