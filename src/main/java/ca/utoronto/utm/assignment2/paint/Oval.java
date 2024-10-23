package ca.utoronto.utm.assignment2.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Oval extends Shape {
    private Point centre;
    private double radiusX;
    private double radiusY;
    private Color color;

    public Oval(Point centre, double radiusX, double radiusY) {
        this.centre = centre;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.color = Color.GREEN;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public DrawStrategy getDrawStrategy() {
        return new OvalDrawStrategy();
    }
}
