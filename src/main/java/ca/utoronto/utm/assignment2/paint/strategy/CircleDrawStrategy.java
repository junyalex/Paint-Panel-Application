package ca.utoronto.utm.assignment2.paint.strategy;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.panel.PaintPanel;
import ca.utoronto.utm.assignment2.paint.command.DrawCircleCommand;
import ca.utoronto.utm.assignment2.paint.shape.Circle;
import ca.utoronto.utm.assignment2.paint.shape.Point;
import ca.utoronto.utm.assignment2.paint.shape.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 *The CircleDrawStrategy class implements the DrawStrategy interface that
 * handles the drawing logic for circle. It handles the user mouse presses, drags and releases
 * to create the circle.
 */
public class CircleDrawStrategy implements DrawStrategy{
    private Circle circle;

    /**
     * Handles the mouse press event to initiate the drawing of a
     * circle
     * @param e the MouseEvent contains the coordinates of where the mouse was clicked
     * @param model the PaintModel to which the shape is added.
     */
    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Circle");
        Point centre = new Point(e.getX(), e.getY());
        this.circle = new Circle(centre, 0);
        this.circle.setColor(PaintPanel.color);
        this.circle.setThickness(PaintPanel.thickness);
    }

    /**
     * Handles the mouse drag event to update the circle radius as the mouse
     * is dragged.
     * @param e the MouseEvent containing the current mouse coordinates
     * @param model the PaintModel for updating the preview of the circle
     */
    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        if(this.circle != null) {
            double deltaX = e.getX() - this.circle.getCentre().x;
            double deltaY = e.getY() - this.circle.getCentre().y;
            double radius = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
            this.circle.setRadius(radius);
            model.addShapePreview(this.circle);

        }
    }

    /**
     * handles the mouse release event to finalize the drawing of the circle
     * to add it to the model
     * @param e the MouseEvent containing the coordinates where the mouse was released
     * @param model the PaintModel for adding the completed circle.
     */
    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        if(this.circle != null) {
            System.out.println("Created Circle");
            model.executeCommand(new DrawCircleCommand(model, circle));
            this.circle = null;
        }
    }

    /**
     * Draws the circle on the provided GraphicsContext based on the
     * current drawing style.
     *
     * @param shape the shape to be drawn, which is a circle
     * @param g2d the GraphicsContext used for drawing
     * @param currStyle the current drawing style "outlined" or "Filled"
     */
    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle) {
        Circle c = (Circle) shape;

        double x = c.getCentre().x;
        double y = c.getCentre().y;
        double radius = c.getRadius();

        if(currStyle.equals("Outlined")){
            if(c.isSelected()){g2d.setLineDashes(5, c.getThickness() * 2);}
            g2d.setStroke(c.getColor());
            g2d.setLineWidth(c.getThickness());
            g2d.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
        }
        else if(currStyle.equals("Filled")) {
            g2d.setFill(c.getColor());
            g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);

            if(c.isSelected()){
                g2d.setLineDashes(5, 5);
                g2d.setStroke(c.getColor().darker());
                g2d.setLineWidth(3);
                g2d.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
            }
        }
        g2d.setLineDashes();
    }
}
