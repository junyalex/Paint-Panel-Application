package ca.utoronto.utm.assignment2.paint.strategy;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.panel.PaintPanel;
import ca.utoronto.utm.assignment2.paint.command.DrawSquareCommand;
import ca.utoronto.utm.assignment2.paint.shape.Point;
import ca.utoronto.utm.assignment2.paint.shape.Shape;
import ca.utoronto.utm.assignment2.paint.shape.Square;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * This class implements the DrawStrategy interface to handle drawing a square shape
 * on the canvas. It defines behavior for mouse events such as pressing, dragging, and releasing
 * to create and draw a square.
 */
public class SquareDrawStrategy implements DrawStrategy {
    private Point centerPoint;
    public Square square;

    public SquareDrawStrategy() {
        super();
    }

    /**
     * Called when the mouse is pressed. Initializes the center point of the square and prepares
     * for drawing by creating a square object with an initial dimension of 0.
     *
     * @param e the mouse event triggered by a mouse press
     * @param model the PaintModel containing the shape previews
     */
    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Square");
        this.centerPoint = new Point(e.getX(), e.getY());
        this.square = new Square(centerPoint, 0);
        this.square.setColor(PaintPanel.color);
        this.square.setThickness(PaintPanel.thickness);
    }

    /**
     * Called when the mouse is dragged. Updates the square's dimensions based on the mouse drag
     * position and refreshes the shape preview in the model.
     *
     * @param e the mouse event triggered by dragging the mouse
     * @param model the PaintModel containing the shape previews
     */
    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        double width = Math.abs(e.getX() - centerPoint.getX());
        double height = Math.abs(e.getY() - centerPoint.getY());
        double dim = Math.max(width, height);
        this.square.setCorner(determineCenter(dim, e));
        this.square.setDim(dim);
        model.addShapePreview(this.square);
    }

    /**
     * Determines the top-left corner point of the square based on the current mouse position.
     * The square's size is adjusted to maintain a square shape.
     *
     * @param dim the dimension of the square (side length)
     * @param e the mouse event triggered by dragging the mouse
     * @return Point representing the top-left corner of the square
     */
    private Point determineCenter(double dim, MouseEvent e) {
        double x;
        double y;
        if (centerPoint.getX() <= e.getX()) {
            x = centerPoint.getX();
        } else {
            x = centerPoint.getX() - dim;
        }
        if (centerPoint.getY() <= e.getY()) {
            y = centerPoint.getY();
        } else {
            y = centerPoint.getY() - dim;
        }
        return new Point(x, y);
    }

    /**
     * Called when the mouse is released. Finalizes the square shape, commits it to the model,
     * and clears the temporary square object.
     *
     * @param e the mouse event triggered by releasing the mouse
     * @param model the PaintModel containing the shape previews
     */
    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        if(this.square != null) {
            System.out.println("Created Square");
            model.executeCommand(new DrawSquareCommand(model, square));
            this.square = null;
        }
    }

    /**
     * Draws the square on the canvas using the provided graphics context. It supports two styles:
     * "Outlined" and "Filled".
     *
     * @param shape the shape to draw (expected to be a Square)
     * @param g2d the graphics context used to draw the shape
     * @param currStyle the current style to use for drawing the square ("Outlined" or "Filled")
     */

    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle) {
        Square s = (Square) shape;
        double x = s.getCorner().x;
        double y = s.getCorner().y;
        double dim = s.getDim();
        if(currStyle.equals("Outlined")) {
            if(s.isSelected()){g2d.setLineDashes(5, s.getThickness() * 2);}

            g2d.setStroke(s.getColor());
            g2d.setLineWidth(s.getThickness());
            g2d.strokeRect(x, y, dim, dim);
        }
        else if(currStyle.equals("Filled")) {
            g2d.setFill(s.getColor());
            g2d.fillRect(x, y, dim, dim);

            if(s.isSelected()){
                g2d.setLineDashes(5, 5);
                g2d.setStroke(s.getColor().darker());
                g2d.setLineWidth(3);
                g2d.strokeRect(x, y, dim, dim);
            }
        }
        g2d.setLineDashes();
    }
}
