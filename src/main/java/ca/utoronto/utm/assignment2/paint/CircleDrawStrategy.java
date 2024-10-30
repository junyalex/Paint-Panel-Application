package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class CircleDrawStrategy implements DrawStrategy{
    private Circle circle;

    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Circle");
        Point centre = new Point(e.getX(), e.getY());
        this.circle = new Circle(centre, 0);
        this.circle.setColor(PaintPanel.color);
    }

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

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        if(this.circle != null) {
            System.out.println("Created Circle");
            model.executeCommand(new DrawCircleCommand(model, circle));
            this.circle = null;
        }
    }

    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle) {
        Circle c = (Circle) shape;
        g2d.setFill(c.getColor());
        double x = c.getCentre().x;
        double y = c.getCentre().y;
        double radius = c.getRadius();
        if(currStyle.equals("Outlined")){
            g2d.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
        }
        else if(currStyle.equals("Filled")) {
            g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }
    }
}
