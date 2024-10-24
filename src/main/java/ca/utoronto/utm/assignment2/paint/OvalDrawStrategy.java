package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class OvalDrawStrategy implements DrawStrategy {
    private Oval oval;
    private Point startPoint;
    private Point endPoint;

    public OvalDrawStrategy() {
        super();
    }

    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Oval");
        startPoint = new Point(e.getX(), e.getY());
        oval = new Oval(startPoint, 0, 0);
    }

    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        if (oval != null) {
            double left = Math.min(startPoint.getX(), e.getX());
            double right = Math.max(startPoint.getX(), e.getX());
            double top = Math.min(startPoint.getY(), e.getY());
            double bottom = Math.max(startPoint.getY(), e.getY());

            oval.setCentre(new Point((left + right) / 2, (top + bottom) / 2));
            oval.setRadiusX((right - left) / 2);
            oval.setRadiusY((bottom - top) / 2);

            model.addShapeTemp(this.oval);
        }

    }

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        if (oval != null) {
            model.addShape(this.oval);
            oval = null;
        }
    }

    @Override
    public void draw(Shape shape, GraphicsContext g2d) {
        Oval o = (Oval) shape;
        g2d.setFill(o.getColor());
        g2d.fillOval(
                o.getCentre().getX() - o.getRadiusX(),
                o.getCentre().getY() - o.getRadiusY(),
                o.getRadiusX() * 2,
                o.getRadiusY() * 2
        );
    }
}
