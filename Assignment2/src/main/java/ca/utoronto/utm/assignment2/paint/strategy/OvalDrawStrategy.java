package ca.utoronto.utm.assignment2.paint.strategy;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.panel.PaintPanel;
import ca.utoronto.utm.assignment2.paint.command.DrawOvalCommand;
import ca.utoronto.utm.assignment2.paint.shape.Oval;
import ca.utoronto.utm.assignment2.paint.shape.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 *  The OvalDrawStrategy class implements the Draw Strategy interface
 *      * to draw an oval shape on the canvas.
 */
public class OvalDrawStrategy implements DrawStrategy {
    private Oval oval;
    private ca.utoronto.utm.assignment2.paint.shape.Point startPoint;

    /**
     * Handles the mouse press events to initialize an oval drawing.
      * @param e the mousevent contains the coordinates of where the mouse was pressed
     * @param model this is used to update the state of the model
     */
    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Oval");
        startPoint = new ca.utoronto.utm.assignment2.paint.shape.Point(e.getX(), e.getY());
        this.oval = new Oval(startPoint, 0, 0);
        this.oval.setColor(PaintPanel.color);
        this.oval.setThickness(PaintPanel.thickness);
        this.oval.setThickness(PaintPanel.thickness);
    }

    /**
     * Handles the drag events to update the radii of the oval.
     * @param e The MouseEvent contains the coordinates of where the mouse was clicked.
     * @param model This is used to update the preview of the shape.
     */
    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        if (this.oval != null) {
            double left = Math.min(startPoint.getX(), e.getX());
            double right = Math.max(startPoint.getX(), e.getX());
            double top = Math.min(startPoint.getY(), e.getY());
            double bottom = Math.max(startPoint.getY(), e.getY());

            this.oval.setCentre(new ca.utoronto.utm.assignment2.paint.shape.Point((left + right) / 2, (top + bottom) / 2));
            this.oval.setRadiusX((right - left) / 2);
            this.oval.setRadiusY((bottom - top) / 2);
            model.addShapePreview(this.oval);


        }
    }

    /**
     * Handles the mouse drag event to update the size and position of
     * the oval.
     * @param e The Mouse event containing the current coordinates
     * @param model The Paintmodel that is used to update the preview of the shape.
     */
    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        if (this.oval != null) {
            model.executeCommand(new DrawOvalCommand(model, oval));
            this.oval = null;
            System.out.println("Created Oval");
        }
    }

    /**
     * Draws the oval on the provided GraphicsContext based on the
     * current drawing style
     * @param shape the shape to be drawn
     * @param g2d the GraphicsContext used for drawing
     * @param currStyle the current drawing style "outlined" or "Filled"
     */
    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle) {
        Oval o = (Oval) shape;

        if(o.getFillStyle().equals("Outlined")) {
            if(o.isSelected()){g2d.setLineDashes(5, o.getThickness() * 2);}

            g2d.setStroke(o.getColor());
            g2d.setLineWidth(o.getThickness());
            g2d.strokeOval(
                    o.getCentre().getX() - o.getRadiusX(),
                    o.getCentre().getY() - o.getRadiusY(),
                    o.getRadiusX() * 2,
                    o.getRadiusY() * 2
            );
        }
        else if (o.getFillStyle().equals("Filled")) {
            g2d.setFill(o.getColor());
            g2d.fillOval(
                    o.getCentre().getX() - o.getRadiusX(),
                    o.getCentre().getY() - o.getRadiusY(),
                    o.getRadiusX() * 2,
                    o.getRadiusY() * 2
            );
            if(o.isSelected()){
                g2d.setLineDashes(5, 5);
                g2d.setStroke(o.getColor().darker());
                g2d.setLineWidth(3);
                g2d.strokeOval(
                        o.getCentre().getX() - o.getRadiusX(),
                        o.getCentre().getY() - o.getRadiusY(),
                        o.getRadiusX() * 2,
                        o.getRadiusY() * 2
                );
            }
        }
        g2d.setLineDashes();
    }
}
