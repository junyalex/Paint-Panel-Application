package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a square shape defined by a corner point
 * and a dimension, it extends the shape class.
 */
public class Square extends Shape {
    private Point corner;
    private double dim;

    /**
     * Constructs a square with a specified corner point and dimension
     *
     * @param corner the top left corner point of the square
     * @param dim the length of the sides of the square
     */
    public Square(Point corner, double dim){
        super();
        this.corner = corner;
        this.dim = dim;
    }

    /**
     * Constructs a Square object with a specified top left corner point.
     *
     *
     * @return the corner point of the square
     */
    public Point getCorner() {
        return corner;
    }

    /**
     * Sets the top-left corner point of the square
     *
     * @param corner the new corner point to set
     */
    public void setCorner(Point corner) {
        this.corner = corner;
    }

    /**
     * Returns the side length of the square
     * @return the set dimension of the square
     */
    public double getDim() {
        return dim;
    }

    /**
     * Sets the side dimension of the square
     *
     *
     * @param dim the new dimension to set
     */
    public void setDim(double dim) {
        this.dim = dim;
    }

    /**
     * Checks if the given point is contained within the
     * bounds of the square
     *
     *
     * @param selectPoint the point to check
     * @return true if the point is within the square, false otherwise
     */
    @Override
    public boolean contains(Point selectPoint) {
        boolean X = corner.x < selectPoint.x && selectPoint.x < corner.x + dim;
        boolean Y = corner.y < selectPoint.y && selectPoint.y < corner.y + dim;
        return (X && Y);
    }

    /**
     * Returns the drawing strategy associated with the square
     * @return a DrawStrategy object used to draw the square
     */
    @Override
    public DrawStrategy getDrawStrategy() {
        return new SquareDrawStrategy();
    }

    /**
     * Moves the square by the specified amounts in the x and y directions
     *
     * @param x the amount to move in the x direction
     * @param y the amount to move in the y direction
     */
    @Override
    public void move(double x , double y){
        corner.x += x;
        corner.y += y;
    }

    /**
     *
     * Creates and return a copy of this Square object
     * @return a new square object that is a copy of this square
     */
    @Override
    public Shape clone(){
        Shape clone = new Square(new Point(this.corner.getX(), this.corner.getY()), this.dim);
        clone.setColor(this.getColor());
        clone.setThickness(this.getThickness());
        clone.setFillStyle(this.getFillStyle());
        return clone;
    }
}
