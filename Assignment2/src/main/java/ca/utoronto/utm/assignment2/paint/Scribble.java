package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;

public class Scribble extends Shape {

    public ArrayList<Point> points;

    public Scribble(ArrayList<Point> points) {
        this.points = points;
    }
    public Point getLastPoint(){
        return this.points.getLast();
    }

}
