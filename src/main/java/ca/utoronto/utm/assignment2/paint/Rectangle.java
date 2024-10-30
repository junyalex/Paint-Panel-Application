package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape{

    private Point corner1;
    private Point corner2;

    public Rectangle(Point corner1, Point corner2) {
        this.corner1 = corner1;
        this.corner2 = corner2;
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
    public DrawStrategy getDrawStrategy() {
        return new RectangleDrawStrategy();
    }
}
