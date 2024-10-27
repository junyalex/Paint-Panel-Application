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
    }

    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        if(this.circle != null) {
            double deltaX = e.getX() - this.circle.getCentre().x;
            double deltaY = e.getY() - this.circle.getCentre().y;
            double radius = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
            this.circle.setRadius(radius);
            model.addShapeTemp(this.circle);

        }
    }

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        if(this.circle != null) {
            model.executeCommand(new DrawCircleCommand(model, circle));
            this.circle = null;
        }
    }

    @Override
    public void draw(Shape shape, GraphicsContext g2d) {
        Circle c = (Circle) shape;
        g2d.setFill(c.getColor());
        double x = c.getCentre().x;
        double y = c.getCentre().y;
        double radius = c.getRadius();
        g2d.fillOval(x-radius, y-radius, radius*2, radius*2);

    }
}
