package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquareDrawStrategy implements DrawStrategy {
    private Point centerPoint;
    public Square square;

    public SquareDrawStrategy() {
        super();
    }

    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Square");
        this.centerPoint = new Point(e.getX(), e.getY());
        this.square = new Square(centerPoint, 0);
        this.square.setColor(PaintPanel.color);
    }

    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        double width = Math.abs(e.getX() - centerPoint.getX());
        double height = Math.abs(e.getY() - centerPoint.getY());
        double dim = Math.max(width, height);
        this.square.setCorner(determineCenter(dim, e));
        this.square.setDim(dim);
        model.addShapePreview(this.square);
    }

    private Point determineCenter(double dim, MouseEvent e) {
        double x;
        double y;
        if (centerPoint.getX() <= e.getX()) {
            x = centerPoint.getX();
        } else {
            x = centerPoint.getX() - dim;
        }
        if (centerPoint.getY() <= e.getY()) {
            y = centerPoint.getY();
        } else {
            y = centerPoint.getY() - dim;
        }
        return new Point(x, y);
    }

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        if(this.square != null) {
            System.out.println("Created Square");
            model.executeCommand(new DrawSquareCommand(model, square));
            this.square = null;
        }
    }
    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle) {
        //Rectangle r = (Rectangle )model.getShape().getLast();
        Square s = (Square) shape;
        //added
        g2d.setFill(s.getColor());
        double x = s.getCorner().x;
        double y = s.getCorner().y;
        double dim = s.getDim();
        if(currStyle.equals("Outlined")) {
            g2d.strokeRect(x, y, dim, dim);
        }
        else if(currStyle.equals("Filled")) {
            g2d.fillRect(x, y, dim, dim);
        }
    }
}
