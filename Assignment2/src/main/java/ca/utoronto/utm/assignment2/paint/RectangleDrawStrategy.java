package ca.utoronto.utm.assignment2.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *The RectangleDrawStrategy class implements the DrawStrategy interface that
 * handles the drawing logic for the rectangle. It handles the user mouse presses, drags and releases
 * to create the rectangle.anc
 */
public class RectangleDrawStrategy implements DrawStrategy{

    public Rectangle rectangle;

    /**
     *  Implements the DrawStrategy interface to handle the drawing for the rectangle
     */
    public RectangleDrawStrategy() {
        super();
    }

    /**
     * Handles the mouse press event to initiate the drawing of a
     * rectangle
     * @param e the MouseEvent contains the coordinates of where the mouse was clicked
     * @param model the PaintModel to which the shape is added.
     */
    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Rectangle");
        Point corner1 = new Point(e.getX(), e.getY()); // left_top
        Point corner2 = new Point(0, 0);   // right_bot
        this.rectangle = new Rectangle(corner1, corner2);
        this.rectangle.setColor(PaintPanel.color);
        this.rectangle.setThickness(PaintPanel.thickness);
    }

    /**
     * Handles the mouse drag event to update the rectangle height and width
     * as the mouse is dragged.
     * @param e the MouseEvent containing the current mouse coordinates
     * @param model the PaintModel for updating the preview of the rectangle
     */
    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        this.rectangle.setCorner2(new Point(e.getX(), e.getY()));
        model.addShapePreview(this.rectangle);
    }

    /**
     * handles the mouse release event to finalize the drawing of the rectangle
     * to add it to the model
     * @param e the MouseEvent containing the coordinates where the mouse was released
     * @param model the PaintModel for adding the completed rectangle.
     */
    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        this.rectangle.setCorner2(new Point(e.getX(), e.getY()));
        model.executeCommand(new DrawRectangleCommand(model, rectangle));
        this.rectangle = null;
        System.out.println("Created Rectangle");
    }

    /**
     * Draws the rectangle on the provided GraphicsContext based on the
     * current drawing style.
     *
     * @param shape the shape to be drawn, which is a rectangle
     * @param g2d the GraphicsContext used for drawing
     * @param currStyle the current drawing style "outlined" or "Filled"
     */

    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle) {
        Rectangle r = (Rectangle) shape;

        double corner_x = Math.min(r.getCorner1().getX(), r.getCorner2().getX());
        double corner_y = Math.min(r.getCorner1().getY(), r.getCorner2().getY());
        double width = r.getWidth();
        double height = r.getHeight();

        if(r.getFillStyle().equals("Outlined")){
            if(r.isSelected()){g2d.setLineDashes(5, r.getThickness() * 2);}
            g2d.setStroke(r.getColor());
            g2d.setLineWidth(r.getThickness());
            g2d.strokeRect(corner_x, corner_y, width, height);
        }
        else if(r.getFillStyle().equals("Filled")) {
            g2d.setFill(r.getColor());
            g2d.fillRect(corner_x, corner_y, width, height);

            if(r.isSelected()){
                g2d.setLineDashes(5, 5);
                g2d.setStroke(r.getColor().darker());
                g2d.setLineWidth(3);
                g2d.strokeRect(corner_x, corner_y, width, height);
            }
        }
        g2d.setLineDashes();
    }

}
