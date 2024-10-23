package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

public class Square extends Shape {
    private Point corner;
    private double dim;

    public Square(Point corner, int dim){
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


}
