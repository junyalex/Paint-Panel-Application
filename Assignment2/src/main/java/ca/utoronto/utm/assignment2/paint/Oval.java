package ca.utoronto.utm.assignment2.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Oval extends Shape {
    private Point centre;
    private double radiusX;
    private double radiusY;

    public Oval(Point centre, double radiusX, double radiusY) {
        super();
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

    @Override
    public boolean contains(Point point){
        double X = (point.x - centre.x) / radiusX;
        double Y = (point.y - centre.y) / radiusY;
        double distanceSquared = Math.pow(X, 2) + Math.pow(Y, 2);

//        if (this.getFillStyle().equals("Outlined")){
//            double outerBoundary = Math.pow(1 + (this.getThickness()*3.5) / (2 * radiusX), 2);
//            double innerBoundary = Math.pow(1 - (this.getThickness()*3.5) / (2 * radiusX), 2);
//
//            return distanceSquared <= outerBoundary && distanceSquared >= innerBoundary;
//        }
//        else{
//            return  distanceSquared <= 1;
//        }
        return distanceSquared <= 1;
    }

    @Override
    public DrawStrategy getDrawStrategy() {
        return new OvalDrawStrategy();
    }
}
