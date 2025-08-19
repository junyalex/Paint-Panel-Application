package ca.utoronto.utm.assignment2.paint.shape;

import ca.utoronto.utm.assignment2.paint.strategy.DrawStrategy;
import ca.utoronto.utm.assignment2.paint.strategy.RectangleDrawStrategy;

/**
 * Represents a rectangle shape defined by two corner points .
 * It extends the abstract Shape class.
 *
 */
public class Rectangle extends Shape{

    private Point corner1;
    private Point corner2;

    /**
     * Constructs a rectangle object with two specified corner points
     * and the selector is set to false.
     *
     * @param corner1 the first corner point of the rectangle
     * @param corner2 the second corner point of the rectangle
     */
    public Rectangle(Point corner1, Point corner2) {
        super();
        this.corner1 = corner1;
        this.corner2 = corner2;
        this.setSelected(false);
    }

    /**
     * Returns the first corner point of the rectangle
     *
     * @return the first corner point
     */
    public Point getCorner1() {
        return corner1;
    }

    /**
     * Sets the first corner point of the rectangle
     *
     * @param corner1 the new first corner point
     */
    public void setCorner1(Point corner1) {
        this.corner1 = corner1;
    }

    /**
     * Set the second corner point
     *
     * @return the second corner point
     */
    public Point getCorner2() {
        return corner2;
    }

    /**
     * Sets the second corner point of the rectangle
     *
     * @param corner2 the second corner point
     */
    public void setCorner2(Point corner2) {
        this.corner2 = corner2;
    }

    /**
     * Calculates and returns the width of the rectangle
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return Math.abs(corner2.getX() - corner1.getX());
    }

    /**
     * Calculates and returns the height of the rectangle
     * @return the height of the rectangle
     */
    public double getHeight() {
        return Math.abs(corner2.getY() - corner1.getY());
    }

    /**
     * Checks if a given point is contained within the bound of the rectangle.
     * @param selectPoint the point to check
     * @return true if the point is within the rectangle false otherwise
     */
    @Override
    public boolean contains(Point selectPoint) {
        boolean X = corner1.x < selectPoint.x && selectPoint.x < corner2.x;
        boolean Y = corner1.y < selectPoint.y && selectPoint.y < corner2.y;
        return (X && Y);
    }

    /**
     * Returns the drawing strategy for the rectangle
     *
     * @return a DrawStrategy object of the rectangle
     */
    @Override
    public DrawStrategy getDrawStrategy() {
        return new RectangleDrawStrategy();
    }

    /**
     * Moves the rectangle by specified amounts in the x
     * and y directions.
     * @param x the amount to move in the x direction
     * @param y the amount to move in the y direction
     */
    @Override
    public void move(double x, double y ){
        corner1.setX(corner1.getX() + x);
        corner1.setY(corner1.getY() + y);
        corner2.setX(corner2.getX() + x);
        corner2.setY(corner2.getY() + y);
    }

    /**
     * Creates and returns a copy of this rectangle
     *
     * @return a  new rectangle object that is a copy of this rectangle
     */
    @Override
    public Shape clone(){
        Shape clone = new Rectangle(new Point(this.getCorner1().getX(), this.getCorner1().getY()),
                new Point(this.getCorner2().getX(), this.getCorner2().getY()));
        clone.setColor(this.getColor());
        clone.setThickness(this.getThickness());
        clone.setFillStyle(this.getFillStyle());
        return clone;
    }

}
