package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Scribble extends Shape {

    public ArrayList<Point> points;

    public Scribble(ArrayList<Point> points) {
        this.points = points;
    }
    public Point getLastPoint(){
        return this.points.getLast();
    }
    public ArrayList<Point> getPoints(){
        return this.points;
    }

    @Override
    public boolean contains(Point selectPoint) {
        double offset = 3;

        for (Point point : points) {
            double X = point.x - selectPoint.x;
            double Y = point.y - selectPoint.y;
            double distance = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2));

            if (distance <= offset) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DrawStrategy getDrawStrategy() {
        return new ScribbleDrawStrategy();
    }
}
