package ca.utoronto.utm.assignment2.paint;

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
    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle) {
        Triangle t = (Triangle) shape;

        double[] xPoints = { t.getPoint1().getX(), t.getPoint2().getX(), t.getPoint3().getX() };
        double[] yPoints = { t.getPoint1().getY(), t.getPoint2().getY(), t.getPoint3().getY() };


        if(currStyle.equals("Outlined")){
            g2d.setStroke(t.getColor());
            g2d.setLineWidth(t.getThickness());
            g2d.strokePolygon(xPoints, yPoints, 3);
        }
        else if(currStyle.equals("Filled")) {
            g2d.setFill(t.getColor());
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
    }
}
