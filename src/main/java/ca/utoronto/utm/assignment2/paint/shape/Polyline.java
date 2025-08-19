package ca.utoronto.utm.assignment2.paint.shape;
import ca.utoronto.utm.assignment2.paint.strategy.DrawStrategy;
import ca.utoronto.utm.assignment2.paint.strategy.PolylineDrawStrategy;

import java.util.ArrayList;

public class Polyline extends Shape{

    private ArrayList<Point> points;
    public static ArrayList<Point> last_points = new ArrayList<>();
    public static Boolean newPoly = true;

    public Polyline(){
        super();
        this.points = new ArrayList<>();
    }

    public ArrayList<Point> getPoints(){
        return this.points;
    }

    public void addPoint(Point point){
        this.points.add(point);
    }

    @Override
    public boolean contains(Point selectPoint) {
        double offset = this.getThickness()/2;

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);

            if (p1 == null || p2 == null) {
                continue;
            }

            if (isPointNearSegment(selectPoint, p1, p2, offset)) {
                return true;
            }
        }
        return false;
    }


    private boolean isPointNearSegment(Point selectPoint, Point p1, Point p2, double offset) {
        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;
        double X = selectPoint.x - p1.x;
        double Y = selectPoint.y - p1.y;

        if (dx == 0 && dy == 0) {
            return distance(selectPoint, p1) <= offset;
        }

        double dotproduct = X * dx + Y * dy;
        double len = dx * dx + dy * dy;
        double t = (dotproduct) / (len);

        Point nearestPointOnLine;
        if (t < 0) {
            nearestPointOnLine = p1;
        } else if (t > 1) {
            nearestPointOnLine = p2;
        } else {
            nearestPointOnLine = new Point(p1.x + t * dx, p1.y + t * dy);
        }

        return distance(selectPoint, nearestPointOnLine) <= offset;
    }

    private double distance(Point p1, Point p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public DrawStrategy getDrawStrategy() {
        return new PolylineDrawStrategy();
    }

    @Override
    public void move(double x, double y){
        for (Point point : points) {
            point.x += x;
            point.y += y;
        }

    }
    @Override
    public Shape clone(){
        Polyline clone = new Polyline();
        ArrayList<Point> newPoints = new ArrayList<>();
        for (Point p : this.points) {
            newPoints.add(new Point(p.getX(), p.getY()));
        }
        clone.points = newPoints;
        clone.setColor(this.getColor());
        clone.setThickness(this.getThickness());
        clone.setSelected(this.isSelected());
        return clone;
    }
}
