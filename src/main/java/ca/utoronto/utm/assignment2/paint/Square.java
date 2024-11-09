package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

public class Square extends Shape {
    private Point corner;
    private double dim;

    public Square(Point corner, int dim){
        super();
        this.corner = corner;
        this.dim = dim;
    }

    public Point getCorner() {
        return corner;
    }

    public void setCorner(Point corner) {
        this.corner = corner;
    }

    public double getDim() {
        return dim;
    }

    public void setDim(double dim) {
        this.dim = dim;
    }

    @Override
    public boolean contains(Point selectPoint) {
        boolean X = corner.x < selectPoint.x && selectPoint.x < corner.x + dim;
        boolean Y = corner.y < selectPoint.y && selectPoint.y < corner.y + dim;
        return (X && Y);
    }

    @Override
    public DrawStrategy getDrawStrategy() {
        return new SquareDrawStrategy();
    }

    @Override
    public void move(double x , double y){
        corner.x += x;
        corner.y += y;
    }
}
