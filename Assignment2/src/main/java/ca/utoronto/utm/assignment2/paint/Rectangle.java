package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape{

    private Point corner1;
    private Point corner2;

    public Rectangle(Point corner1, Point corner2) {
        super();
        this.corner1 = corner1;
        this.corner2 = corner2;
        this.setSelected(false);
    }

    public Point getCorner1() {
        return corner1;
    }

    public void setCorner1(Point corner1) {
        this.corner1 = corner1;
    }

    public Point getCorner2() {
        return corner2;
    }

    public void setCorner2(Point corner2) {
        this.corner2 = corner2;
    }

    public double getWidth() {
        return Math.abs(corner2.getX() - corner1.getX());
    }

    public double getHeight() {
        return Math.abs(corner2.getY() - corner1.getY());
    }

    @Override
    public boolean contains(Point selectPoint) {
        boolean X = corner1.x < selectPoint.x && selectPoint.x < corner2.x;
        boolean Y = corner1.y < selectPoint.y && selectPoint.y < corner2.y;
        return (X && Y);
    }


    @Override
    public DrawStrategy getDrawStrategy() {
        return new RectangleDrawStrategy();
    }

    @Override
    public void move(double x, double y ){
        corner1.setX(corner1.getX() + x);
        corner1.setY(corner1.getY() + y);
        corner2.setX(corner2.getX() + x);
        corner2.setY(corner2.getY() + y);
    }

    @Override
    public Shape clone(){
        Shape clone = new Rectangle(this.corner1, this.corner2);
        clone.setColor(this.getColor());
        clone.setThickness(this.getThickness());
        clone.setFillStyle(this.getFillStyle());
        return clone;
    }

}
