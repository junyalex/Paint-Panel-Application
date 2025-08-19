package ca.utoronto.utm.assignment2.paint.strategy;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.panel.PaintPanel;
import ca.utoronto.utm.assignment2.paint.command.DrawTriangleCommand;
import ca.utoronto.utm.assignment2.paint.shape.Point;
import ca.utoronto.utm.assignment2.paint.shape.Shape;
import ca.utoronto.utm.assignment2.paint.shape.Triangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class TriangleDrawStrategy implements DrawStrategy {
    private Triangle triangle;
    private Point startPoint;
    private Point point2;
    private Point point3;

    public TriangleDrawStrategy() {
        super();
    }

    /**
     *
     * @param e the mouse event trigerred by a mouse press
     * @param model the Paintmodel containing the shape previews
     */
    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Triangle");
        startPoint = new Point(e.getX(), e.getY());
        point2 = startPoint;
        point3 =startPoint;
        triangle = new Triangle(startPoint, point2, point3);
        triangle.setColor(PaintPanel.color);
        this.triangle.setThickness(PaintPanel.thickness);
        model.addShapePreview(this.triangle);

    }

    /**
     * Updates the triangles points to the dragged position ad refreshes the
     * shape preview in the model
     * @param e the mouse vent triggered by dragging the mouse
     * @param model the PaintModel containing the shape previews
     */
    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        if (triangle != null) {

            point2 = new Point(e.getX(), e.getY());
            point3 = new Point((2 * startPoint.getX() - point2.getX()), point2.getY());

            triangle.setPoint2(point2);
            triangle.setPoint3(point3);

            model.addShapePreview(this.triangle);
        }

    }

    /**
     *
     Called when the mouse is released. Finalizes the triangle shape, commits it to the model,
     * and clears the temporary triangle object.
     *
     * @param e the mouse event triggered by releasing the mouse
     * @param model the PaintModel containing the shape previews
     */

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        System.out.println("Created Triangle");
        if (triangle != null) {

            Point point2 = new Point(e.getX(), e.getY());
            Point point3 = new Point((2 * startPoint.getX() - point2.getX()), point2.getY());

            triangle.setPoint2(point2);
            triangle.setPoint3(point3);

            model.executeCommand(new DrawTriangleCommand(model, triangle));
            triangle = null;
        }

    }
    /**
     * Draws the triangle on the canvas using the provided graphics context. It supports two styles:
     * "Outlined" and "Filled".
     *
     * @param shape the shape to draw (expected to be a Triangle)
     * @param g2d the graphics context used to draw the shape
     * @param currStyle the current style to use for drawing the triangle ("Outlined" or "Filled")
     */
    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle) {
        Triangle t = (Triangle) shape;

        double[] xPoints = { t.getPoint1().getX(), t.getPoint2().getX(), t.getPoint3().getX() };
        double[] yPoints = { t.getPoint1().getY(), t.getPoint2().getY(), t.getPoint3().getY() };

        if(currStyle.equals("Outlined")){
            if(t.isSelected()){g2d.setLineDashes(5, t.getThickness() * 2);}

            g2d.setStroke(t.getColor());
            g2d.setLineWidth(t.getThickness());
            g2d.strokePolygon(xPoints, yPoints, 3);
        }
        else if(currStyle.equals("Filled")) {
            g2d.setFill(t.getColor());
            g2d.fillPolygon(xPoints, yPoints, 3);

            if(t.isSelected()){
                g2d.setLineDashes(5, 5);
                g2d.setStroke(t.getColor().darker());
                g2d.setLineWidth(3);
                g2d.strokePolygon(xPoints, yPoints, 3);
            }
        }
        g2d.setLineDashes();
    }
}
