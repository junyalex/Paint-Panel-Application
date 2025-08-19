package ca.utoronto.utm.assignment2.paint.shape;

import ca.utoronto.utm.assignment2.paint.strategy.DrawStrategy;
import ca.utoronto.utm.assignment2.paint.strategy.TriangleDrawStrategy;
import javafx.scene.paint.Color;

/**
 *Represents the triangle shape with three points. It extends
 * the abstract Shape class.
 */
public class Triangle extends Shape{
    private Point point1;
    private Point point2;
    private Point point3;
    private Color color;

    /**
     *
     * @param point1 the first point of the triangle
     * @param point2 the second point of the triangle
     * @param point3 the third point of the triangle
     */
    public Triangle(Point point1, Point point2, Point point3) {
        super();
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.color = Color.PURPLE;
    }

    /**
     * Returns the first Point of the traingle
     *
     * @return the new first point
     */
    public Point getPoint1() {
        return point1;
    }

    /**
     *Sets the first Point of the triangle
     * @param point1
     */
    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    /**
     * Returns the second point of the triangle
     * @return the second point
     */
    public Point getPoint2() {
        return point2;
    }

    /**
     * Sets the second point of the triangle
     * @param point2 the new second point of the triangle
     */
    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    /**
     * Returns the third point of the triangle
     * @return the third point
     */
    public Point getPoint3() {
        return point3;
    }

    /**
     * Sets the third point of the triangle
     *
     * @param point3 the new third point
     */
    public void setPoint3(Point point3) {
        this.point3 = point3;
    }

    /**
     * Returns the colour of the triangle
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the triangle
     * @param color the color to set the shape
     */
    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * Checks if a given point is contained within the triangle
     *
     * @param selectPoint
     * @return true if the point is inside the triangle, false otherwise
     */
    @Override
    public boolean contains(Point selectPoint) {
        double area = area(point1, point2, point3);
        double area1 = area(selectPoint, point2, point3);
        double area2 = area(point1, selectPoint, point3);
        double area3 = area(point1, point2, selectPoint);
        return Math.abs(area - (area1 + area2 + area3)) < 0.1;
    }

    /**
     * Calculates the area of the triangle that will be formed
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     * @return the area of the triangle
     */
    private double area(Point p1, Point p2, Point p3) {
        double A1 = p1.getX() * (p2.getY() - p3.getY());
        double A2 = p2.getX() * (p3.getY() - p1.getY());
        double A3 = p3.getX() * (p1.getY() - p2.getY());
        return Math.abs((A1 + A2 + A3) / 2.0);
    }

    /**
     * Returns the Triangle Draw Strategy
     * @return a triangle which is a DrawStrategy object
     */
    @Override
    public DrawStrategy getDrawStrategy() {
        return new TriangleDrawStrategy();
    }

    /**
     * Moves the triangle by the specifies amounts in the x and y directions.
     * @param x the amount to move in the x direction
     * @param y the amount to move in the y direction
     */
    @Override
    public void move(double x , double y){
        this.point1.setX(this.point1.getX() +x);
        this.point1.setY(this.point1.getY() +y);
        this.point2.setX(this.point2.getX() +x);
        this.point2.setY(this.point2.getY() +y);
        this.point3.setX(this.point3.getX() +x);
        this.point3.setY(this.point3.getY() +y);
    }

    /**
     * Creates a copy and returns a copy of this Triangle object
     *
     * @return a new Triangle object that is a copy of this triangle
     */
    @Override
    public Shape clone(){
        Shape clone = new Triangle(new Point(this.point1.getX(), this.point1.getY()),
                new Point(this.point2.getX(), this.point2.getY()),
                new Point(this.point3.getX(), this.point3.getY()));
        clone.setColor(this.getColor());
        clone.setThickness(this.getThickness());
        clone.setFillStyle(this.getFillStyle());
        return clone;
    }
}

