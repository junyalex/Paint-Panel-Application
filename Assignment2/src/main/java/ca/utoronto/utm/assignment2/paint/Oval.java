package ca.utoronto.utm.assignment2.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Oval extends Shape {
    private Point centre;
    private double radiusX;
    private double radiusY;

    public Oval(Point centre, double radiusX, double radiusY) {
        this.centre = centre;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    public Point getCentre() {
        return centre;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public double getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }

    public double getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }

    //based on ellipse equation
    @Override
    public boolean contains(Point point){
        double X = (point.x - centre.x) / radiusX;
        double Y = (point.y - centre.y) / radiusY;
        return (Math.pow(X, 2) + Math.pow(Y, 2)) <= 1;
    }

    @Override
    public DrawStrategy getDrawStrategy() {
        return new OvalDrawStrategy();
    }
}
