package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape{
    private Point point1;
    private Point point2;
    private Point point3;
    private Color color;

    public Triangle(Point point1, Point point2, Point point3) {
        super();
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.color = Color.PURPLE;
    }


    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public void setPoint3(Point point3) {
        this.point3 = point3;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    //using area approach
    @Override
    public boolean contains(Point selectPoint) {
        double area = area(point1, point2, point3);
        double area1 = area(selectPoint, point2, point3);
        double area2 = area(point1, selectPoint, point3);
        double area3 = area(point1, point2, selectPoint);
        return Math.abs(area - (area1 + area2 + area3)) < 0.1;
    }

    private double area(Point p1, Point p2, Point p3) {
        double A1 = p1.getX() * (p2.getY() - p3.getY());
        double A2 = p2.getX() * (p3.getY() - p1.getY());
        double A3 = p3.getX() * (p1.getY() - p2.getY());
        return Math.abs((A1 + A2 + A3) / 2.0);
    }

    @Override
    public DrawStrategy getDrawStrategy() {
        return new TriangleDrawStrategy();
    }

    @Override
    public void move(double x , double y){
        this.point1.setX(this.point1.getX() +x);
        this.point1.setY(this.point1.getY() +y);
        this.point2.setX(this.point2.getX() +x);
        this.point2.setY(this.point2.getY() +y);
        this.point3.setX(this.point3.getX() +x);
        this.point3.setY(this.point3.getY() +y);
    }
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

