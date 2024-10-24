package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquareDrawStrategy implements DrawStrategy {

    public Square square;

    public SquareDrawStrategy() {
        super();
    }

    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Square");
        Point corner1 = new Point(e.getX(), e.getY());
        this.square = new Square(corner1, 0);

    }

    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        double deltaX = e.getX() - this.square.getCorner().x;
        double deltaY = e.getY() - this.square.getCorner().y;
        double dim = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        this.square.setDim(dim);
        model.addShape(this.square);
        model.addSquare(this.square);
    }

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        double deltaX = e.getX() - this.square.getCorner().x;
        double deltaY = e.getY() - this.square.getCorner().y;
        double dim = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        this.square.setDim(dim);
        model.addShape(this.square);
        this.square = null;

    }
    @Override
    public void draw(Shape shape, GraphicsContext g2d) {
        //Rectangle r = (Rectangle )model.getShape().getLast();
        Square s = (Square) shape;
        //added
        g2d.setFill(Color.GREEN);
        double x = s.getCorner().x;
        double y = s.getCorner().y;
        double dim = s.getDim();
        g2d.fillRect(x, y, dim, dim);
    }
}
