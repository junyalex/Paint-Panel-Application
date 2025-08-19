package ca.utoronto.utm.assignment2.paint.strategy;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.panel.PaintPanel;
import ca.utoronto.utm.assignment2.paint.command.DrawPolylineCommand;
import ca.utoronto.utm.assignment2.paint.shape.Point;
import ca.utoronto.utm.assignment2.paint.shape.Polyline;
import ca.utoronto.utm.assignment2.paint.shape.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

/**
 * This class implements the {@link DrawStrategy} interface to handle drawing a polyline
 * (a connected series of line segments) on the canvas. It provides behavior for mouse events
 * such as pressing, dragging, and releasing to create and draw a polyline.
 */
public class PolylineDrawStrategy implements DrawStrategy {
    private Polyline polyline;


    public PolylineDrawStrategy(){
        super();
    }

    /**
     * Called when the mouse is pressed. Initializes the polyline and prepares for drawing by
     * adding the first point at the mouse location. It also adds the last point from a previous
     * polyline if any exists.
     *
     * @param e the mouse event triggered by a mouse press
     * @param model the PaintModel containing the shape previews
     */
    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Polyline");
        if(Polyline.newPoly) Polyline.last_points.add(null);
        Polyline.newPoly = false;

        this.polyline = new Polyline();
        this.polyline.setColor(PaintPanel.color);
        this.polyline.setThickness(PaintPanel.thickness);
        this.polyline.getPoints().add(new Point(e.getX(), e.getY()));
        if (!Polyline.last_points.isEmpty()) {
            this.polyline.getPoints().add(Polyline.last_points.get(Polyline.last_points.size() - 1));
        }
        this.polyline.setColor(PaintPanel.color);
    }

    /**
     * Called when the mouse is dragged. Updates the polyline's points by removing the last
     * point and adding a new point at the current mouse location. The shape preview is then
     * refreshed in the model.
     *
     * @param e the mouse event triggered by dragging the mouse
     * @param model the PaintModel containing the shape previews
     */
    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        if (this.polyline != null) {
            this.polyline.getPoints().removeLast();
            this.polyline.getPoints().add(new Point(e.getX(), e.getY()));
            model.addShapePreview(this.polyline);
        }
    }

    /**
     * Called when the mouse is released. Finalizes the polyline by adding the final point,
     * saves the polyline's last point, and commits it to the model.
     *
     * @param e the mouse event triggered by releasing the mouse
     * @param model the PaintModel containing the shape previews
     */
    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        if (this.polyline != null) {
            System.out.println("Created Polyline");
            this.polyline.getPoints().add(new Point(e.getX(), e.getY()));
            Polyline.last_points.add(new Point(e.getX(), e.getY()));
            model.executeCommand(new DrawPolylineCommand(model, polyline));

        }
    }

    /**
     * Draws the polyline on the canvas using the provided graphics context. It draws the polyline
     * using the specified color and thickness. If the polyline is selected, it draws a thicker,
     * darker line.
     *
     * @param shape the shape to draw (expected to be a Polyline)
     * @param g2d the graphics context used to draw the shape
     * @param currStyle the current style to use for drawing the polyline
     */
    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle){
        Polyline p = (Polyline) shape;
        ArrayList<Point> points = p.getPoints();

        if(p.isSelected()){
            g2d.setLineWidth(p.getThickness() + 5);
            g2d.setStroke(p.getColor().darker());

            for (int i = 0; i < points.size() - 1; i++) {
                Point p1 = points.get(i);
                Point p2 = points.get(i + 1);
                g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

        g2d.setStroke(p.getColor());
        g2d.setLineWidth(p.getThickness());

        for(int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            if(p1 == null) continue;
            Point p2 = points.get(i + 1);
            if(p2 == null) continue;
            g2d.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    }
}
