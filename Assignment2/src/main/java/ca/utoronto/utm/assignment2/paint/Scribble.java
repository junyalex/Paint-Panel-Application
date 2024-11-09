package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Scribble extends Shape {

    public ArrayList<Point> points;

    public Scribble(ArrayList<Point> points) {
        super();
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
        double offset = this.getThickness()/2;

        for (int i=0; i<this.points.size()-1; i++) {
            Point p1 = this.points.get(i);
            Point p2 = this.points.get(i+1);

            boolean isOnSegment = isSelectedPointOnSegment(p1, p2, selectPoint, offset);
            if(isOnSegment) {return true;}
        }
        return false;
    }


    private boolean isSelectedPointOnSegment(Point p1, Point p2, Point selectPoint, double offset) {
        // using projection formula...

        if (p1.x == p2.x && p1.y == p2.y) {
            return offset >= Math.sqrt(Math.pow(selectPoint.x - p1.x, 2) + Math.pow(selectPoint.y - p1.y, 2));
        }

        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;
        double dSquared = dx * dx + dy * dy;

        double t = ((selectPoint.x - p1.x) * dx + (selectPoint.y - p1.y) * dy) / dSquared;
        if(t < 0 || t > 1) {return false;}

        double closestX = p1.x + t * dx;
        double closestY = p1.y + t * dy;
        double closestMagnitude = Math.sqrt(Math.pow(selectPoint.x - closestX, 2) + Math.pow(selectPoint.y - closestY, 2));

        return offset >= closestMagnitude;
    }

    @Override
    public DrawStrategy getDrawStrategy() {
        return new ScribbleDrawStrategy();
    }

    @Override
    public void move(double x, double y) {
        for (Point point : points) {
            point.x += x;
            point.y += y;
        }
    }
}
