package ca.utoronto.utm.assignment2.paint.strategy;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.panel.PaintPanel;
import ca.utoronto.utm.assignment2.paint.command.DrawScribbleCommand;
import ca.utoronto.utm.assignment2.paint.shape.Point;
import ca.utoronto.utm.assignment2.paint.shape.Scribble;
import ca.utoronto.utm.assignment2.paint.shape.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * This class implements the {@link DrawStrategy} interface to handle drawing a scribble
 * (a series of connected points or freehand drawing) on the canvas. It provides behavior
 * for mouse events such as pressing, dragging, and releasing to create and draw a scribble.
 */
public class ScribbleDrawStrategy implements DrawStrategy {

    public Scribble scribble;

    public ScribbleDrawStrategy() {
        super();
    }

    /**
     * Called when the mouse is pressed. Initializes the scribble and starts a new line by
     * adding the first point at the mouse location.
     *
     * @param e the mouse event triggered by a mouse press
     * @param model the PaintModel containing the shape previews
     */
    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        Point startPoint = new Point(e.getX(), e.getY());
        ArrayList<Point> point_list = new ArrayList<>();
        point_list.add(startPoint);
        this.scribble = new Scribble(point_list);
        this.scribble.setColor(PaintPanel.color);
        this.scribble.setThickness(PaintPanel.thickness);
    }

    /**
     * Called when the mouse is dragged. Updates the scribble by adding the new mouse position
     * as a point and refreshes the shape preview in the model.
     *
     * @param e the mouse event triggered by dragging the mouse
     * @param model the PaintModel containing the shape previews
     */
    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        ArrayList<Point> points = this.scribble.points;
        double curx = e.getX();
        double cury = e.getY();

        points.add(new Point(curx, cury));
        model.addShapePreview(this.scribble);
    }

    /**
     * Called when the mouse is released. Finalizes the scribble by adding the final point
     * and commits the scribble to the model.
     *
     * @param e the mouse event triggered by releasing the mouse
     * @param model the PaintModel containing the shape previews
     */
    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        double currx = e.getX();
        double curry = e.getY();

        this.scribble.points.add(new Point(currx, curry));
        model.executeCommand(new DrawScribbleCommand(model, scribble));
        this.scribble = null;
        System.out.println("Created Scribble");
    }
    /**
     * Draws the scribble on the canvas using the provided graphics context. It draws a
     * series of connected lines between the points in the scribble. If the scribble is selected,
     * a thicker, darker line is drawn.
     *
     * @param shape the shape to draw (expected to be a Scribble)
     * @param g2d the graphics context used to draw the shape
     * @param currStyle the current style to use for drawing the scribble
     */
    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle) {
        Scribble scribble = (Scribble) shape;
        ArrayList<Point> points = scribble.getPoints();

        if(scribble.isSelected()){
            g2d.setLineWidth(scribble.getThickness() + 5);
            g2d.setStroke(scribble.getColor().darker());

            for (int i = 0; i < points.size() - 1; i++) {
                Point p1 = points.get(i);
                Point p2 = points.get(i + 1);
                g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

        g2d.setLineWidth(scribble.getThickness());
        g2d.setStroke(scribble.getColor());

        for (int i = 0; i < points.size() - 1 ; i++){
            Point p1 = points.get(i);
            Point p2 = points.get(i+1);

            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

}

